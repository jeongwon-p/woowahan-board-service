package com.woowahan.woowahanboardservice.domain.board.dto.request;

public class BoardHideRequestBody {

    private String boardId;

    private String userId;

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
