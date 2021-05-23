package com.woowahan.woowahanuserservice.entity;

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
    private String emailId;

    private String name;

    private String password;

    private long rank;

    public User() {
    }

    public User(
            String emailId,
            String name,
            String password,
            long rank
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

    public long getRank() {
        return rank;
    }
}
