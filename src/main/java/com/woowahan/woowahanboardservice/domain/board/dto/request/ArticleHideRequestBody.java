package com.woowahan.woowahanboardservice.domain.board.dto.request;

public class ArticleHideRequestBody {

    private String articleId;

    private String userId;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
