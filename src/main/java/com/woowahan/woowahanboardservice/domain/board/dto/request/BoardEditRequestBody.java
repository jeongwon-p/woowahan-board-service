package com.woowahan.woowahanboardservice.domain.board.dto.request;

import com.woowahan.woowahanboardservice.domain.board.entity.Board;
import org.springframework.util.StringUtils;

import java.util.UUID;

public class BoardEditRequestBody {

    private String boardId;

    private String description;

    private boolean hidden;

    private String name;

    public Board toBoard() {
        return new Board(
                StringUtils.hasText(boardId)
                        ? boardId
                        : UUID.randomUUID().toString(),
                description,
                hidden,
                name
        );
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}