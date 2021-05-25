package com.woowahan.woowahanboardservice;

public class MailParam {
    private final String address;

    private final String title;

    private final String message;

    protected static class Builder {
        private String address;

        private String title;

        private String message;

        public Builder address(String value) {
            address = value;
            return this;
        }

        public Builder title(String value) {
            title = value;
            return this;
        }

        public Builder message(String value) {
            message = value;
            return this;
        }

        public MailParam build() {
            return new MailParam(this);
        }
    }

    private MailParam(Builder builder) {
        address = builder.address;
        title = builder.title;
        message = builder.message;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getAddress() {
        return address;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}
