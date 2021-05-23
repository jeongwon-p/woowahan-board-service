package com.woowahan.woowahanboardservice;

import com.woowahan.woowahanboardservice.domain.board.dao.ArticleRepository;
import com.woowahan.woowahanboardservice.domain.board.dao.BoardRepository;
import com.woowahan.woowahanboardservice.domain.board.dao.CommentRepository;
import com.woowahan.woowahanboardservice.domain.board.dto.request.BoardEditRequestBody;
import com.woowahan.woowahanboardservice.domain.board.dto.view.ArticleView;
import com.woowahan.woowahanboardservice.domain.board.dto.view.BoardView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final ArticleRepository articleDao;
    private final BoardRepository boardDao;
    private final CommentRepository commentDao;

    public BoardService(
            ArticleRepository articleDao,
            BoardRepository boardDao,
            CommentRepository commentDao
    ) {
        this.articleDao = articleDao;
        this.boardDao = boardDao;
        this.commentDao = commentDao;
    }

    @Transactional
    public void saveBoard(BoardEditRequestBody request) {
        boardDao.save(request.toBoard());
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
}
