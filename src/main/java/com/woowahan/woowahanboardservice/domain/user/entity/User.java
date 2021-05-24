package com.woowahan.woowahanboardservice.domain.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(
        name = "user"
)
public class User {

    @Id
    @Column(name = "email_id")
    private String emailId;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "rank")
    private int rank;

    public User() {
    }

    public User(
            String emailId,
            String name,
            String password,
            int rank
    ) {
        this.emailId = emailId;
        this.name = name;
        this.password = password;
        this.rank = rank;
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

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getRank() {
        return rank;
    }
}
