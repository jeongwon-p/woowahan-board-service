package com.woowahan.woowahanboardservice.domain.board.dto.request;

public class CommentHideRequestBody {

    private String commentId;

    private String userId;

    public String getCommentId() {
        return commentId;
    }

    public String getUserId() {
        return userId;
    }
}
