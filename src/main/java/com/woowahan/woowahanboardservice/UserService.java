package com.woowahan.woowahanuserservice;

import com.woowahan.woowahanuserservice.dao.UserRepository;
import com.woowahan.woowahanuserservice.dto.request.UserJoinRequestBody;
import com.woowahan.woowahanuserservice.dto.request.LogInRequestBody;
import com.woowahan.woowahanuserservice.dto.view.LogInResponse;
import com.woowahan.woowahanuserservice.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
