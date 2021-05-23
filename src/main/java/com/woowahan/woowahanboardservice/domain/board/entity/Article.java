package com.woowahan.woowahanboardservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Article {

    @Id
    private String id;

    private String boardId;

    private String contents;

    private LocalDateTime createDateTime;

    private LocalDateTime modifyDateTime;

    private String title;

    private String userId;

    public Article() {

    }

    public Article(
            String id,
            String boardId,
            String contents,
            LocalDateTime createDateTime,
            LocalDateTime modifyDateTime,
            String title,
            String userId
    ) {
        this.id = id;
        this.boardId = boardId;
        this.contents = contents;
        this.createDateTime = createDateTime;
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

    public String getBoardId() {
        return boardId;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
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
