package com.woowahan.woowahanboardservice.domain.board.dto.request;

import java.time.LocalDate;

public class ArticleReportRequest {

    private LocalDate beginDate;

    private LocalDate endDate;

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
