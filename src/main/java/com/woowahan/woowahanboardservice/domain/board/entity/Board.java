package com.woowahan.woowahanboardservice.domain.board.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(
        name = "board"
)
public class Board {

    @Id
    @Column(name = "board_id")
    private String id;

    // relation
    @OneToMany(mappedBy = "board")
    private List<Article> articles;

    // field
    @Column(name = "board_desc")
    private String description;

    @Column(name = "hide_yn")
    private boolean hidden;

    @Column(name = "board_name")
    private String name;

    public Board() {

    }

    public Board(
            String id,
            String description,
            boolean hidden,
            String name
    ) {
        this.id = id;
        this.description = description;
        this.hidden = hidden;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return id.equals(board.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getId() {
        return id;
    }

    public List<Article> getArticles() {
        return articles;
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
