package com.woowahan.woowahanboardservice.domain.board.dto.view;

import com.woowahan.woowahanboardservice.domain.board.entity.Board;

public class BoardView {

    private final String boardId;

    private final String description;

    private final boolean hidden;

    private final String name;

    public BoardView(Board entity) {
        this.boardId = entity.getId();
        this.description = entity.getDescription();
        this.hidden = entity.isHidden();
        this.name = entity.getName();
    }

    public String getBoardId() {
        return boardId;
    }

    public String getDescription() {
        return description;
    }

    public boolean isHidden() {
        return hidden;
    }

    public String getName() {
        return name;
    }
}
