package com.woowahan.woowahanboardservice.domain.board.entity;

import com.woowahan.woowahanboardservice.common.BooleanToYnConverter;
import com.woowahan.woowahanboardservice.domain.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(
        name = "comment"
)
public class Comment {

    @Id
    @Column(name = "comment_id", length = 36)
    private String id;

    //relation
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "none"), name = "article_id", nullable = false, insertable = false, updatable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "none"), name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    //field
    @Column(name = "article_id", length = 36)
    private String articleId;

    @Column(name = "content", length = 2000)
    private String content;

    @Column(name = "crt_tm", length = 45)
    private LocalDateTime createDateTime;

    @Column(name = "hide_yn", columnDefinition = "varchar(1) default 'N'")
    @Convert(converter = BooleanToYnConverter.class)
    private boolean hidden;

    @Column(name = "chg_tm", length = 45)
    private LocalDateTime modifyDateTime;

    @Column(name = "user_id", length = 200)
    private String userId;

    public Comment() {

    }

    private Comment(
            String id,
            Article article,
            User user,
            String articleId,
            String content,
            LocalDateTime createDateTime,
            boolean hidden,
            LocalDateTime modifyDateTime,
            String userId
    ) {
        this.id = id;
        this.article = article;
        this.user = user;
        this.articleId = articleId;
        this.content = content;
        this.createDateTime = createDateTime;
        this.hidden = hidden;
        this.modifyDateTime = modifyDateTime;
        this.userId = userId;
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

    public Comment hideOrCancel() {
        return new Comment(
                this.id,
                this.article,
                this.user,
                this.articleId,
                this.content,
                this.createDateTime,
                !this.hidden,
                LocalDateTime.now(),
                this.userId
        );
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
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
