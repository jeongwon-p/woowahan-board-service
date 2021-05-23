package com.woowahan.woowahanuserservice.controller;

import com.woowahan.woowahanuserservice.UserService;
import com.woowahan.woowahanuserservice.dto.request.UserJoinRequestBody;
import com.woowahan.woowahanuserservice.dto.request.LogInRequestBody;
import com.woowahan.woowahanuserservice.dto.view.LogInResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/join")
    public void join(@RequestBody UserJoinRequestBody request) {
        userService.join(request);
    }

    @GetMapping(value = "/member")
    public String searchMember(@RequestParam String id) {
        return userService.searchMember(id);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LogInResponse> logIn(@RequestBody LogInRequestBody request) {
        return ResponseEntity.ok().body(userService.logIn(request));
    }

    // TODO: logout

    // TODO : Oauth2.0 (여유)

    // TODO : 비밀번호 초기화 (여유)


}
