package com.woowahan.woowahanboardservice;

import com.woowahan.woowahanboardservice.common.AuthorityException;
import com.woowahan.woowahanboardservice.domain.board.dao.ArticleRepository;
import com.woowahan.woowahanboardservice.domain.board.dao.BoardRepository;
import com.woowahan.woowahanboardservice.domain.board.dao.CommentRepository;
import com.woowahan.woowahanboardservice.domain.board.dto.request.*;
import com.woowahan.woowahanboardservice.domain.board.dto.view.ArticleReportView;
import com.woowahan.woowahanboardservice.domain.board.dto.view.ArticleView;
import com.woowahan.woowahanboardservice.domain.board.dto.view.BoardView;
import com.woowahan.woowahanboardservice.domain.board.dto.view.CommentView;
import com.woowahan.woowahanboardservice.domain.board.entity.Article;
import com.woowahan.woowahanboardservice.domain.board.entity.Board;
import com.woowahan.woowahanboardservice.domain.board.entity.Comment;
import com.woowahan.woowahanboardservice.domain.board.exception.ArticleIllegalException;
import com.woowahan.woowahanboardservice.domain.hackernews.dto.view.HackerNewsStoryView;
import com.woowahan.woowahanboardservice.domain.hackernews.helper.HackerNewsHelper;
import com.woowahan.woowahanboardservice.domain.user.UserRepository;
import com.woowahan.woowahanboardservice.domain.user.dto.view.UserView;
import com.woowahan.woowahanboardservice.domain.user.entity.User;
import com.woowahan.woowahanboardservice.domain.user.type.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class PostingService {

    private final List<String> prohibitedWords = List.of("비속어", "나쁜놈", "배고파", "키보드", "마우스");
    private final ArticleRepository articleDao;
    private final BoardRepository boardDao;
    private final CommentRepository commentDao;
    private final UserRepository userDao;
    private final MailService mailService;

    public PostingService(
            ArticleRepository articleDao,
            BoardRepository boardDao,
            CommentRepository commentDao,
            UserRepository userDao,
            MailService mailService
    ) {
        this.articleDao = articleDao;
        this.boardDao = boardDao;
        this.commentDao = commentDao;
        this.userDao = userDao;
        this.mailService = mailService;
    }

    @Transactional
    public void hideOrCancelArticle(ArticleHideRequestBody request) {
        Article article = articleDao.findById(request.getArticleId())
                .orElseThrow(EntityNotFoundException::new)
                .hideOrCancel();
        User user = userDao.findById(request.getUserId())
                .orElseThrow(EntityNotFoundException::new);

        if (!request.getUserId().equals(article.getUserId()) && user.getRole() != Role.ADMIN) {
            throw new AuthorityException("Authority Error: You do not have permission");
        }

        if (!article.isHidden()) {
            this.checkExcess5Limit(article);
        }

        articleDao.save(article);
    }

    @Transactional
    public void hideOrCancelBoard(BoardHideRequestBody request) {
        Board board = boardDao.findById(request.getBoardId())
                .orElseThrow(EntityNotFoundException::new)
                .hideOrCancel();
        User user = userDao.findById(request.getUserId())
                .orElseThrow(EntityNotFoundException::new);

        if (user.getRole() != Role.ADMIN) {
            throw new AuthorityException("Authority Error: You do not have permission");
        }

        boardDao.save(board);
    }

    @Transactional
    public void hideOrCancelComment(CommentHideRequestBody request) {
        Comment comment = commentDao.findById(request.getCommentId())
                .orElseThrow(EntityNotFoundException::new)
                .hideOrCancel();
        User user = userDao.findById(request.getUserId())
                .orElseThrow(EntityNotFoundException::new);

        if (!request.getUserId().equals(comment.getUserId()) && user.getRole() != Role.ADMIN) {
            throw new AuthorityException("Authority Error: You do not have permission");
        }

        commentDao.save(comment);
    }

    @Transactional(readOnly = true)
    public ArticleReportView reportArticle(ArticleReportRequest request) {
        Map<LocalDate, Long> articles = articleDao.findAllByCreateDateTimeBetween(request.getBeginDate().atStartOfDay(), request.getEndDate().atStartOfDay().plusDays(1)).stream()
                .sorted(Comparator.comparing(Article::getCreateDateTime))
                .collect(Collectors.groupingBy(article -> article.getCreateDateTime().toLocalDate(), Collectors.counting()));
        Map<LocalDate, Long> comments = commentDao.findAllByCreateDateTimeBetween(request.getBeginDate().atStartOfDay(), request.getEndDate().atStartOfDay().plusDays(1)).stream()
                .sorted(Comparator.comparing(Comment::getCreateDateTime))
                .collect(Collectors.groupingBy(comment -> comment.getCreateDateTime().toLocalDate(), Collectors.counting()));

        return new ArticleReportView(articles, comments);
    }

    @Transactional
    public void saveArticle(ArticleEditRequestBody request) {
        Article article = Optional.ofNullable(request.getArticleId())
                .map(articleId -> articleDao.findById(articleId)
                        .orElseThrow(EntityNotFoundException::new).updateArticle(request.toArticle()))
                .orElse(request.toArticle());

        if (!article.getUserId().equals(request.getUserId())) {
            throw new AuthorityException("Authority Error: You do not have permission");
        }

        this.checkExcess5Limit(article);
        this.checkProhibitionWords(article.getContents());
        this.checkIncludedLink(article.getContents());

        articleDao.save(article);
    }

    private void checkExcess5Limit(Article article) {
        LocalDateTime startDateTime = article.getCreateDateTime().toLocalDate().atStartOfDay();
        LocalDateTime endDateTime = article.getCreateDateTime().toLocalDate().atStartOfDay().plusDays(1);

        List<Article> articles = articleDao.findAllByUserIdAndCreateDateTimeBetween(article.getUserId(), startDateTime, endDateTime).stream()
                .filter(target -> !target.getId().equals(article.getId()))
                .filter(target -> !target.isHidden())
                .collect(Collectors.toList());

        if (articles.size() > 4) {
            throw new ArticleIllegalException("You cannot register more than 5 posts including hidden posts.");
        }
    }

    private void checkProhibitionWords(String contents) {
        String withoutSpecialCharacter = contents.replaceAll("[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]", "");

        if (prohibitedWords.stream().anyMatch(withoutSpecialCharacter::contains)) {
            throw new ArticleIllegalException("The content cannot contain prohibited words");
        }
    }

    private void checkIncludedLink(String contents) {
        Pattern pattern = Pattern.compile("(https?://)([^/]*)(.)?(naver.com|daum.com|)(/)?");
        Matcher matcher = pattern.matcher(contents);

        if (matcher.find()) {
            throw new ArticleIllegalException("The content cannot contain naver, daum link");
        }
    }

    @Transactional
    public void saveBoard(BoardEditRequestBody request) {
        User user = userDao.findById(request.getUserId())
                .orElseThrow(EntityNotFoundException::new);
        if (user.getRole() != Role.ADMIN) {
            throw new AuthorityException("Authority Error: You do not have permission");
        }
        boardDao.save(request.toBoard());
    }

    @Transactional
    public void saveComment(CommentEditRequestBody request) {
        Article article = articleDao.findById(request.getArticleId())
                .orElseThrow(EntityNotFoundException::new);
        Comment comment = Optional.ofNullable(request.getCommentId())
                .map(commentId -> commentDao.findById(commentId)
                        .orElseThrow(EntityNotFoundException::new).updateComment(request.toComment()))
                .orElse(request.toComment());

        if (!comment.getUserId().equals(request.getUserId())) {
            throw new AuthorityException("Authority Error: You do not have permission");
        }

        // send mail when a comment is first posted
        if (!StringUtils.hasText(request.getCommentId())) {
            mailService.sendMail(MailParam.builder()
                    .address(article.getUserId())
                    .title("댓글 알림")
                    .message("'" + article.getTitle() + "' 게시글에 댓글이 작성되었습니다.")
                    .build());
        }
        commentDao.save(comment);
    }

    public ArticleView searchArticle(String articleId) {
        return articleDao.findById(articleId)
                .map(ArticleView::new)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<ArticleView> searchArticles(String boardId, Pageable pageable) {
        Page<Article> pagedArticles = articleDao.findAllByBoardIdAndHiddenFalse(boardId, pageable);
        return pagedArticles.stream()
                .sorted(Comparator.comparing(Article::getCreateDateTime))
                .map(article -> new ArticleView(
                        article.getId(),
                        article.getBoardId(),
                        article.getComments().stream()
                                .filter(comment -> !comment.isHidden())
                                .sorted(Comparator.comparing(Comment::getCreateDateTime))
                                .map(CommentView::new).collect(Collectors.toList()),
                        article.getContents(),
                        article.getCreateDateTime(),
                        article.isHidden(),
                        pagedArticles.isFirst(),
                        pagedArticles.isLast(),
                        article.getModifyDateTime(),
                        pagedArticles.getPageable().getPageNumber(),
                        article.getTitle(),
                        article.getUserId(),
                        new UserView(article.getUser())))
                .collect(Collectors.toList());
    }

    public List<BoardView> searchBoards() {
        return boardDao.findAllByHiddenFalse().stream()
                .map(BoardView::new)
                .collect(Collectors.toList());
    }

    public List<HackerNewsStoryView> searchLately10HackerNews() {
        return HackerNewsHelper.getLatelyNewStoryIdsByLimit(10).stream()
                .map(HackerNewsHelper::getStory)
                .map(HackerNewsStoryView::new)
                .collect(Collectors.toList());
    }
}
