package com.woowahan.woowahanboardservice.domain.user.dao;

import com.woowahan.woowahanboardservice.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
