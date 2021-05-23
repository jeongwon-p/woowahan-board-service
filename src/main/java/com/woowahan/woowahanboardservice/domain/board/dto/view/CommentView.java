package com.woowahan.woowahanboardservice.domain.board.dto.view;

import com.woowahan.woowahanboardservice.domain.board.entity.Comment;

import java.time.LocalDateTime;

public class CommentView {

    private String articleId;

    private String commentId;

    private String content;

    private LocalDateTime createDateTime;

    private boolean hidden;

    private LocalDateTime modifyDateTime;

    private String userId;

    public CommentView(Comment entity) {
        this.articleId = entity.getArticleId();
        this.commentId = entity.getId();
        this.content = entity.getContent();
        this.createDateTime = entity.getCreateDateTime();
        this.hidden = entity.isHidden();
        this.modifyDateTime = entity.getModifyDateTime();
        this.userId = entity.getUserId();
    }

    public String getArticleId() {
        return articleId;
    }

    public String getCommentId() {
        return commentId;
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

    public String getUserId() {
        return userId;
    }
}
