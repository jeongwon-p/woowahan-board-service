package com.woowahan.woowahanboardservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Comment {

    @Id
    private String id;

    private String articleId;

    private String comment;

    private LocalDateTime createDateTime;

    private LocalDateTime modifyDateTime;

    private String userId;

    public Comment() {

    }

    public Comment(
            String id,
            String articleId,
            String comment,
            LocalDateTime createDateTime,
            LocalDateTime modifyDateTime,
            String userId
    ) {
        this.id = id;
        this.articleId = articleId;
        this.comment = comment;
        this.createDateTime = createDateTime;
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

    public String getArticleId() {
        return articleId;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public LocalDateTime getModifyDateTime() {
        return modifyDateTime;
    }

    public String getUserId() {
        return userId;
    }
}
