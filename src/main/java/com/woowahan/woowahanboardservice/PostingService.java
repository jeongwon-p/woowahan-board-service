package com.woowahan.woowahanboardservice;

import com.woowahan.woowahanboardservice.domain.board.dao.ArticleRepository;
import com.woowahan.woowahanboardservice.domain.board.dao.BoardRepository;
import com.woowahan.woowahanboardservice.domain.board.dao.CommentRepository;
import com.woowahan.woowahanboardservice.domain.board.dto.request.*;
import com.woowahan.woowahanboardservice.domain.board.dto.view.ArticleView;
import com.woowahan.woowahanboardservice.domain.board.dto.view.BoardView;
import com.woowahan.woowahanboardservice.domain.board.dto.view.CommentView;
import com.woowahan.woowahanboardservice.domain.board.entity.Article;
import com.woowahan.woowahanboardservice.domain.board.entity.Board;
import com.woowahan.woowahanboardservice.domain.board.entity.Comment;
import com.woowahan.woowahanboardservice.domain.board.exception.ArticleIllegalException;
import com.woowahan.woowahanboardservice.domain.hackernews.dto.view.HackerNewsStoryView;
import com.woowahan.woowahanboardservice.domain.hackernews.helper.HackerNewsHelper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class PostingService {

    private final List<String> prohibitedWords = List.of("비속어", "나쁜놈", "배고파", "키보드", "마우스");
    private final ArticleRepository articleDao;
    private final BoardRepository boardDao;
    private final CommentRepository commentDao;
    private final MailService mailService;

    public PostingService(
            ArticleRepository articleDao,
            BoardRepository boardDao,
            CommentRepository commentDao,
            MailService mailService
    ) {
        this.articleDao = articleDao;
        this.boardDao = boardDao;
        this.commentDao = commentDao;
        this.mailService = mailService;
    }

    @Transactional
    public void hideOrCancelArticle(ArticleHideRequestBody request) {
        // Todo : 권한 체크
        Article article = articleDao.findById(request.getArticleId())
                .orElseThrow(EntityNotFoundException::new)
                .hideOrCancel();
        if (!article.isHidden()) {
            this.checkExcess5Limit(article);
        }

        articleDao.save(article);
    }

    @Transactional
    public void hideOrCancelBoard(BoardHideRequestBody request) {
        // Todo : 권한 체크
        Board board = boardDao.findById(request.getBoardId())
                .orElseThrow(EntityNotFoundException::new)
                .hideOrCancel();
        boardDao.save(board);
    }

    @Transactional
    public void hideOrCancelComment(CommentHideRequestBody request) {
        // Todo : 권한 체크
        Comment comment = commentDao.findById(request.getCommentId())
                .orElseThrow(EntityNotFoundException::new)
                .hideOrCancel();
        commentDao.save(comment);
    }

    @Transactional
    public void saveArticle(ArticleEditRequestBody request) {
        // Todo : 권한 체크
        Article article = request.toArticle();
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
            //throw new ArticleIllegalException("You cannot register more than 5 posts including hidden posts.");
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
        // Todo : 권한 체크
        boardDao.save(request.toBoard());
    }

    @Transactional
    public void saveComment(CommentEditRequestBody request) {
        // Todo : 권한 체크
        Article article = articleDao.findById(request.getArticleId())
                .orElseThrow(EntityNotFoundException::new);
        Comment comment = request.toComment();

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
        Page<Article> pagedArticles = articleDao.findAllByBoardId(boardId, pageable);
        return pagedArticles.stream()
                .map(article -> new ArticleView(
                        article.getId(),
                        article.getBoardId(),
                        article.getComments().stream().map(CommentView::new).collect(Collectors.toList()),
                        article.getContents(),
                        article.getCreateDateTime(),
                        article.isHidden(),
                        pagedArticles.isFirst(),
                        pagedArticles.isLast(),
                        article.getModifyDateTime(),
                        pagedArticles.getPageable().getPageNumber(),
                        article.getTitle(),
                        article.getUserId()))
                .collect(Collectors.toList());
    }

    public List<BoardView> searchBoards() {
        return boardDao.findAll().stream()
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
