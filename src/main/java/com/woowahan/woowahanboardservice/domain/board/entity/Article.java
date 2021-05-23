package com.woowahan.woowahanboardservice.domain.board.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(
        name = "article"
)
public class Article {

    @Id
    @Column(name = "article_id")
    private String id;

    // relation
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    // field
    @Column(name = "board_id")
    private String boardId;

    @Column(name = "content")
    private String contents;

    @Column(name = "crt_tm")
    private LocalDateTime createDateTime;

    @Column(name = "hide_yn")
    private boolean hidden;

    @Column(name = "chg_tm")
    private LocalDateTime modifyDateTime;

    @Column(name = "title")
    private String title;

    @Column(name = "user_id")
    private String userId;

    public Article() {

    }

    public Article(
            String id,
            String boardId,
            String contents,
            LocalDateTime createDateTime,
            boolean hidden,
            LocalDateTime modifyDateTime,
            String title,
            String userId
    ) {
        this.id = id;
        this.boardId = boardId;
        this.contents = contents;
        this.createDateTime = createDateTime;
        this.hidden = hidden;
        this.modifyDateTime = modifyDateTime;
        this.title = title;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getBoardId() {
        return boardId;
    }

    public String getContents() {
        return contents;
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
