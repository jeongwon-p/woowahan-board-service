package com.woowahan.woowahanuserservice.dao;

import com.woowahan.woowahanuserservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
