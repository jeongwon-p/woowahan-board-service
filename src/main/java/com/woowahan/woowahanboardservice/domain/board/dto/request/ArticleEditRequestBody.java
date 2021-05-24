package com.woowahan.woowahanboardservice.domain.board.dto.request;

import com.woowahan.woowahanboardservice.domain.board.entity.Article;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.UUID;


public class ArticleEditRequestBody {

    private String articleId;

    private String boardId;

    private String content;

    private LocalDateTime createDateTime;

    private boolean hidden;

    private String title;

    private String userId;

    public Article toArticle() {
        return new Article(
                StringUtils.hasText(articleId)
                        ? articleId
                        : UUID.randomUUID().toString(),
                boardId,
                content,
                StringUtils.hasText(articleId)
                ? createDateTime
                : LocalDateTime.now(),
                hidden,
                LocalDateTime.now(),
                title,
                userId
        );
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
