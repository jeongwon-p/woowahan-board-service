package com.woowahan.woowahanboardservice.controller;

import com.woowahan.woowahanboardservice.BoardService;
import com.woowahan.woowahanboardservice.domain.board.dto.request.BoardEditRequestBody;
import com.woowahan.woowahanboardservice.domain.board.dto.view.ArticleView;
import com.woowahan.woowahanboardservice.domain.board.dto.view.BoardView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board/")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/board")
    public void saveBoard(BoardEditRequestBody request){
        boardService.saveBoard(request);
    }

    // 게시판 목록 불러오기
    @GetMapping("/board/list")
    public ResponseEntity<List<BoardView>> searchBoards() {
        return ResponseEntity.ok(boardService.searchBoards());
    }

    // TODO : 해커뉴스 최신 10개 불러오기

    // 게시글 목록 조회
    @GetMapping("/article/list")
    public ResponseEntity<List<ArticleView>> searchArticles(@RequestParam String boardId) {
        return ResponseEntity.ok(boardService.searchArticles(boardId));
    }

    // TODO : 게시글쓰기/수정

    // TODO : 댓글 쓰기/수정

    // 게시글조회
    @GetMapping("/article")
    public ResponseEntity<ArticleView> searchArticle(@RequestParam String articleId) {
        return ResponseEntity.ok(boardService.searchArticle(articleId));
    }

    // TODO : 게시글숨기기/취소

    // TODO : 댓글 숨기기/취소

    // TODO : 일별 게시글, 댓글 통계(여유)
}
