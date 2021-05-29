package com.woowahan.woowahanboardservice.domain.board.exception;

public class ArticleIllegalException extends RuntimeException{
    public ArticleIllegalException() {
        super();
    }

    public ArticleIllegalException(String message) {
        super(message);
    }

    public ArticleIllegalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArticleIllegalException(Throwable cause) {
        super(cause);
    }

    protected ArticleIllegalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
