package com.woowahan.woowahanboardservice.domain.board.dto.request;

public class BoardHideRequestBody {

    private String boardId;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public String getBoardId() {
        return boardId;
    }
}
