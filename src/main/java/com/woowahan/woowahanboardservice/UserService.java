package com.woowahan.woowahanboardservice;

import com.woowahan.woowahanboardservice.domain.user.dao.UserRepository;
import com.woowahan.woowahanboardservice.domain.user.dto.request.LogInRequestBody;
import com.woowahan.woowahanboardservice.domain.user.dto.request.UserJoinRequestBody;
import com.woowahan.woowahanboardservice.domain.user.dto.view.LogInResponse;
import com.woowahan.woowahanboardservice.domain.user.entity.User;
import com.woowahan.woowahanboardservice.domain.user.util.JwtTokenProvider;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public void join(UserJoinRequestBody request) {
        userRepository.save(request.toEntity());
    }

    public String searchMember(String id) {
        return userRepository.findById(id)
                .orElseThrow(RuntimeException::new)
                .getName();
    }

    @Transactional
    public LogInResponse logIn(LogInRequestBody request) {
        User user = userRepository.findById(request.getEmailId())
                .orElseThrow(IllegalAccessError::new);

        return new LogInResponse(
                user.getName(),
                this.createToken(user.getEmailId()),
                user.getEmailId()
        );
    }

    public String createToken(String userId) {
        return jwtTokenProvider.createToken(userId);
    }
}
