package com.woowahan.woowahanboardservice.domain.board.dto.view;

import com.woowahan.woowahanboardservice.domain.board.entity.Comment;
import com.woowahan.woowahanboardservice.domain.user.dto.view.UserView;

import java.time.LocalDateTime;

public class CommentView {

    private final String articleId;

    private final String commentId;

    private final String content;

    private final LocalDateTime createDateTime;

    private final boolean hidden;

    private final LocalDateTime modifyDateTime;

    private final String userId;

    private final UserView userView;

    public CommentView(Comment entity) {
        this.articleId = entity.getArticleId();
        this.commentId = entity.getId();
        this.content = entity.getContent();
        this.createDateTime = entity.getCreateDateTime();
        this.hidden = entity.isHidden();
        this.modifyDateTime = entity.getModifyDateTime();
        this.userId = entity.getUserId();
        this.userView = new UserView(entity.getUser());
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

    public UserView getUserView() {
        return userView;
    }
}
