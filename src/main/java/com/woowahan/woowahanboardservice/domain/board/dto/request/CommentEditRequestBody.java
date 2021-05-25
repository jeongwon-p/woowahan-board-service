package com.woowahan.woowahanboardservice.domain.board.dto.request;

import com.woowahan.woowahanboardservice.common.BooleanToYnConverter;
import com.woowahan.woowahanboardservice.domain.board.entity.Article;
import com.woowahan.woowahanboardservice.domain.board.entity.Comment;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

public class CommentEditRequestBody {

    @ApiModelProperty(value = "첫 등록시 미입력, UUID 생성")
    private String commentId;

    private String articleId;

    private String content;

    private LocalDateTime createDateTime;

    private boolean hidden;

    private String userId;

    public Comment toComment() {
        return new Comment(
                StringUtils.hasText(commentId)
                        ? commentId
                        : UUID.randomUUID().toString(),
                articleId,
                content,
                StringUtils.hasText(commentId)
                        ? createDateTime
                        : LocalDateTime.now(),
                hidden,
                LocalDateTime.now(),
                userId
        );
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
