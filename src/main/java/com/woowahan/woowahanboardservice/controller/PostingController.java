package com.woowahan.woowahanboardservice.controller;

import com.woowahan.woowahanboardservice.PostingService;
import com.woowahan.woowahanboardservice.domain.board.dto.request.*;
import com.woowahan.woowahanboardservice.domain.board.dto.view.ArticleReportView;
import com.woowahan.woowahanboardservice.domain.board.dto.view.ArticleView;
import com.woowahan.woowahanboardservice.domain.board.dto.view.BoardView;
import com.woowahan.woowahanboardservice.domain.hackernews.dto.view.HackerNewsStoryView;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // TODO: Gateway 적용 후 CrossOrigin 어노테이션 지우기 (보안 위험)
@RestController
@RequestMapping("/post/")
public class PostingController {

    private final PostingService postingService;

    public PostingController(PostingService postingService) {
        this.postingService = postingService;
    }

    @ApiOperation("게시글 등록 및 수정")
    @PostMapping("/article")
    public void saveArticle(ArticleEditRequestBody request) {
        postingService.saveArticle(request);
    }

    @ApiOperation("게시판 등록 및 수정")
    @PostMapping("/board")
    public void saveBoard(BoardEditRequestBody request) {
        postingService.saveBoard(request);
    }

    @ApiOperation("댓글 등록 및 수정")
    @PostMapping("/comment")
    public void saveComment(CommentEditRequestBody request) {
        postingService.saveComment(request);
    }

    @ApiOperation("게시판 목록 불러오기")
    @GetMapping("/board/list")
    public ResponseEntity<List<BoardView>> searchBoards() {
        return ResponseEntity.ok(postingService.searchBoards());
    }

    @ApiOperation("해커 뉴스 최신 10개 불러 오기")
    @GetMapping("/hackernews/lately")
    public ResponseEntity<List<HackerNewsStoryView>> searchLately10HackerNews() {
        return ResponseEntity.ok(postingService.searchLately10HackerNews());
    }

    @ApiOperation("게시글 목록 페이징 조회")
    @GetMapping("/article/list")
    public ResponseEntity<List<ArticleView>> searchArticles(@RequestParam String boardId, Pageable pageable) {
        return ResponseEntity.ok(postingService.searchArticles(boardId, pageable));
    }

    @ApiOperation("게시글 조회")
    @GetMapping("/article")
    public ResponseEntity<ArticleView> searchArticle(@RequestParam String articleId) {
        return ResponseEntity.ok(postingService.searchArticle(articleId));
    }

    @ApiOperation("게시글 숨기기 및 숨기기 취소")
    @PostMapping("/article/hide")
    public void hideOrCancelArticle(ArticleHideRequestBody request) {
        postingService.hideOrCancelArticle(request);
    }

    @ApiOperation("게시판 숨기기 및 숨기기 취소")
    @PostMapping("/board/hide")
    public void hideOrCancelBoard(BoardHideRequestBody request) {
        postingService.hideOrCancelBoard(request);
    }

    @ApiOperation("댓글 숨기기 및 숨기기 취소")
    @PostMapping("/comment/hide")
    public void hideOrCancelComment(CommentHideRequestBody request) {
        postingService.hideOrCancelComment(request);
    }

    @ApiOperation("일별 게시글 및 댓글 통계")
    @GetMapping("/article/report")
    public ResponseEntity<ArticleReportView> reportArticle(@ModelAttribute ArticleReportRequest request) {
        return ResponseEntity.ok(postingService.reportArticle(request));
    }
}
