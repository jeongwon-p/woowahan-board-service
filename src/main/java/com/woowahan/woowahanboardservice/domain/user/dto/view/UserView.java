package com.woowahan.woowahanboardservice.domain.user.dto.view;

import com.woowahan.woowahanboardservice.domain.user.entity.User;
import com.woowahan.woowahanboardservice.domain.user.type.Role;

public class UserView {

    private final String userId;

    private final String name;

    private final int ranking;

    private final Role role;

    public UserView(User entity) {
        this.userId = entity.getId();
        this.name = entity.getName();
        this.ranking = entity.getRank();
        this.role = entity.getRole();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getRanking() {
        return ranking;
    }

    public Role getRole() {
        return role;
    }
}
