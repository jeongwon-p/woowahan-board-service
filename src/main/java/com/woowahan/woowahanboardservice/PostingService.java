package com.woowahan.woowahanboardservice;

import com.woowahan.woowahanboardservice.domain.board.dao.ArticleRepository;
import com.woowahan.woowahanboardservice.domain.board.dao.BoardRepository;
import com.woowahan.woowahanboardservice.domain.board.dao.CommentRepository;
import com.woowahan.woowahanboardservice.domain.board.dto.request.*;
import com.woowahan.woowahanboardservice.domain.board.dto.view.ArticleView;
import com.woowahan.woowahanboardservice.domain.board.dto.view.BoardView;
import com.woowahan.woowahanboardservice.domain.board.entity.Article;
import com.woowahan.woowahanboardservice.domain.board.entity.Comment;
import com.woowahan.woowahanboardservice.domain.hackernews.dto.view.HackerNewsStoryView;
import com.woowahan.woowahanboardservice.domain.hackernews.helper.HackerNewsHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostingService {

    private final ArticleRepository articleDao;
    private final BoardRepository boardDao;
    private final CommentRepository commentDao;

    public PostingService(
            ArticleRepository articleDao,
            BoardRepository boardDao,
            CommentRepository commentDao
    ) {
        this.articleDao = articleDao;
        this.boardDao = boardDao;
        this.commentDao = commentDao;
    }

    @Transactional
    public void hideOrCancelArticle(ArticleHideRequestBody request) {
        // Todo : 권한 체크
        Article article = articleDao.findById(request.getArticleId())
                .orElseThrow(EntityNotFoundException::new)
                .hideOrCancel();
        articleDao.save(article);
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
        articleDao.save(request.toArticle());
    }

    @Transactional
    public void saveBoard(BoardEditRequestBody request) {
        // Todo : 권한 체크
        boardDao.save(request.toBoard());
    }

    @Transactional
    public void saveComment(CommentEditRequestBody request) {
        // Todo : 권한 체크
        commentDao.save(request.toComment());
    }

    public ArticleView searchArticle(String articleId) {
        return articleDao.findById(articleId)
                .map(ArticleView::new)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<ArticleView> searchArticles(String boardId) {
        return articleDao.findAllByBoardId(boardId).stream()
                .map(ArticleView::new)
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
