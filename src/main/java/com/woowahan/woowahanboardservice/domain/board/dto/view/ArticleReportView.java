package com.woowahan.woowahanboardservice.domain.board.dto.view;

import java.time.LocalDate;
import java.util.Map;

public class ArticleReportView {

    private Map<LocalDate, Long> article;

    private Map<LocalDate, Long> comment;

    public ArticleReportView(Map<LocalDate, Long> articles, Map<LocalDate, Long> comments) {
        this.article = articles;
        this.comment = comments;
    }

    public Map<LocalDate, Long> getArticle() {
        return article;
    }

    public Map<LocalDate, Long> getComment() {
        return comment;
    }
}
