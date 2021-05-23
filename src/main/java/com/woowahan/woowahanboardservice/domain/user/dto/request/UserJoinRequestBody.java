package com.woowahan.woowahanboardservice.domain.user.dto.request;

import com.woowahan.woowahanboardservice.domain.user.entity.User;

public class UserJoinRequestBody {

    private String emailId;

    private String name;

    private String password;

    public User toEntity() {
        return new User(emailId, name, password, 0);
    }

    public String getEmailId() {
        return emailId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
