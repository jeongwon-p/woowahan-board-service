package com.woowahan.woowahanboardservice;

import com.woowahan.woowahanboardservice.domain.user.dao.UserRepository;
import com.woowahan.woowahanboardservice.domain.user.dto.request.LogInRequestBody;
import com.woowahan.woowahanboardservice.domain.user.dto.request.UserHideRequestBody;
import com.woowahan.woowahanboardservice.domain.user.dto.request.UserJoinRequestBody;
import com.woowahan.woowahanboardservice.domain.user.dto.view.LogInResponse;
import com.woowahan.woowahanboardservice.domain.user.entity.User;
import com.woowahan.woowahanboardservice.domain.user.util.JwtTokenProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class UserService {

    private final BCryptPasswordEncoder encoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userDao;

    public UserService(
            BCryptPasswordEncoder encoder,
            JwtTokenProvider jwtTokenProvider,
            UserRepository userDao
    ) {
        this.encoder = encoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDao = userDao;
    }

    @Transactional
    public LogInResponse logIn(LogInRequestBody request) {
        User user = userDao.findById(request.getEmailId())
                .filter(info -> encoder.matches(request.getPassword(), info.getPassword())).stream()
                .findAny()
                .orElseThrow(() -> new IllegalAccessError("Email or password is wrong"));

        return new LogInResponse(
                user.getName(),
                this.createToken(user.getEmailId()),
                user.getEmailId()
        );
    }

    private String createToken(String userId) {
        return jwtTokenProvider.createToken(userId);
    }

    public String searchMember(String id) {
        return userDao.findById(id)
                .orElseThrow(RuntimeException::new)
                .getName();
    }
}
