package com.woowahan.woowahanboardservice.domain.user.entity;

import com.woowahan.woowahanboardservice.common.BooleanToYnConverter;
import com.woowahan.woowahanboardservice.domain.user.type.Role;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Objects;

@Immutable
@Entity
@Table(
        name = "user"
)
public class User {

    @Id
    @Column(name = "email_id")
    private String emailId;

    @Column(name = "hide_yn", columnDefinition = "varchar(1) default 'N'")
    @Convert(converter = BooleanToYnConverter.class)
    private boolean hidden;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "ranking")
    private int rank;

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "score")
    private int score;

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return emailId.equals(user.emailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId);
    }

    public String getEmailId() {
        return emailId;
    }

    public boolean isHidden() {
        return hidden;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getRank() {
        return rank;
    }

    public Role getRole() {
        return role;
    }

    public int getScore() {
        return score;
    }
}
