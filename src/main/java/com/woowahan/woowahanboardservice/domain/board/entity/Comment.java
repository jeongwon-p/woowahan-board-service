package com.woowahan.woowahanboardservice.domain.board.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(
        name = "comment"
)
public class Comment {

    @Id
    @Column(name = "comment_id")
    private String id;

    //relation
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    //field
    @Column(name = "article_id")
    private String articleId;

    @Column(name = "content")
    private String content;

    @Column(name = "crt_tm")
    private LocalDateTime createDateTime;

    @Column(name = "hide_yn")
    private boolean hidden;

    @Column(name = "chg_tm")
    private LocalDateTime modifyDateTime;

    @Column(name = "user_id")
    private String userId;

    public Comment() {

    }

    public Comment(
            String id,
            String articleId,
            String content,
            LocalDateTime createDateTime,
            boolean hidden,
            LocalDateTime modifyDateTime,
            String userId
    ) {
        this.id = id;
        this.articleId = articleId;
        this.content = content;
        this.createDateTime = createDateTime;
        this.hidden = hidden;
        this.modifyDateTime = modifyDateTime;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id.equals(comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getId() {
        return id;
    }

    public Article getArticle() {
        return article;
    }

    public String getArticleId() {
        return articleId;
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
