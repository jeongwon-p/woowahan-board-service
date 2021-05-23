package com.woowahan.woowahanboardservice.domain.board.dto.view;

import com.woowahan.woowahanboardservice.domain.board.entity.Article;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleView {

    private String articleId;

    private String boardId;

    private List<CommentView> commentList;

    private String content;

    private LocalDateTime createDateTime;

    private boolean hidden;

    private LocalDateTime modifyDateTime;

    private String title;

    private String userId;

    public ArticleView(Article entity) {
        this.articleId = entity.getId();
        this.boardId = entity.getBoardId();
        this.commentList = entity.getComments().stream().map(CommentView::new).collect(Collectors.toList());
        this.content = entity.getContents();
        this.createDateTime = entity.getCreateDateTime();
        this.hidden = entity.isHidden();
        this.modifyDateTime = entity.getModifyDateTime();
        this.title = entity.getTitle();
        this.userId = entity.getUserId();
    }

    public String getArticleId() {
        return articleId;
    }

    public String getBoardId() {
        return boardId;
    }

    public List<CommentView> getCommentList() {
        return commentList;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public boolean isHidden() {
        return hidden;
    }

    public LocalDateTime getModifyDateTime() {
        return modifyDateTime;
    }

    public String getTitle() {
        return title;
    }

    public String getUserId() {
        return userId;
    }
}
