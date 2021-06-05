package com.woowahan.woowahanboardservice.domain.board.entity;

import com.woowahan.woowahanboardservice.common.BooleanToYnConverter;
import com.woowahan.woowahanboardservice.domain.user.entity.User;

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
    @Column(name = "article_id", length = 36)
    private String id;

    // relation
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "none"), name = "board_id", nullable = false, insertable = false, updatable = false)
    private Board board;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "none"), name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    // field
    @Column(name = "board_id", length = 36)
    private String boardId;

    @Lob
    @Column(name = "content")
    private String contents;

    @Column(name = "crt_tm", length = 45)
    private LocalDateTime createDateTime;

    @Column(name = "hide_yn", columnDefinition = "varchar(1) default 'N'")
    @Convert(converter = BooleanToYnConverter.class)
    private boolean hidden;

    @Column(name = "chg_tm", length = 45)
    private LocalDateTime modifyDateTime;

    @Column(name = "title", length = 200)
    private String title;

    @Column(name = "user_id", length = 200)
    private String userId;

    public Article() {

    }

    private Article(
            String id,
            Board board,
            List<Comment> comments,
            User user,
            String boardId,
            String contents,
            LocalDateTime createDateTime,
            boolean hidden,
            LocalDateTime modifyDateTime,
            String title,
            String userId
    ) {
        this.id = id;
        this.board = board;
        this.comments = comments;
        this.user = user;
        this.boardId = boardId;
        this.contents = contents;
        this.createDateTime = createDateTime;
        this.hidden = hidden;
        this.modifyDateTime = modifyDateTime;
        this.title = title;
        this.userId = userId;
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

    public Article updateArticle(Article article) {
        return new Article(
                this.id,
                this.board,
                this.comments,
                this.user,
                this.boardId,
                article.contents,
                this.createDateTime,
                this.hidden,
                LocalDateTime.now(),
                article.title,
                this.userId
        );
    }

    public Article hideOrCancel() {
        return new Article(
                this.id,
                this.board,
                this.comments,
                this.user,
                this.boardId,
                this.contents,
                this.createDateTime,
                !this.hidden,
                LocalDateTime.now(),
                this.title,
                this.userId
        );
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

    public User getUser() {
        return user;
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
