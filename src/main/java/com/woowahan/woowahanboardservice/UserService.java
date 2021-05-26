package com.woowahan.woowahanboardservice;

import com.woowahan.woowahanboardservice.domain.board.entity.Board;
import com.woowahan.woowahanboardservice.domain.user.dao.UserRepository;
import com.woowahan.woowahanboardservice.domain.user.dto.request.LogInRequestBody;
import com.woowahan.woowahanboardservice.domain.user.dto.request.UserHideRequestBody;
import com.woowahan.woowahanboardservice.domain.user.dto.request.UserJoinRequestBody;
import com.woowahan.woowahanboardservice.domain.user.dto.view.LogInResponse;
import com.woowahan.woowahanboardservice.domain.user.entity.User;
import com.woowahan.woowahanboardservice.domain.user.util.JwtTokenProvider;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userDao;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userDao, JwtTokenProvider jwtTokenProvider) {
        this.userDao = userDao;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public void hideOrCancelArticle(UserHideRequestBody request) {
        User user = userDao.findById(request.getUserId())
                .orElseThrow(EntityNotFoundException::new)
                .hideOrCancel();
        userDao.save(user);
    }

    @Transactional
    public void join(UserJoinRequestBody request) {
        userDao.save(request.toEntity());
    }

    @Transactional
    public LogInResponse logIn(LogInRequestBody request) {
        User user = userDao.findById(request.getEmailId())
                .orElseThrow(IllegalAccessError::new);

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
