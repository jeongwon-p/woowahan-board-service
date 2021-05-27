package com.woowahan.woowahanboardservice.controller;

import com.woowahan.woowahanboardservice.UserService;
import com.woowahan.woowahanboardservice.domain.user.dto.request.LogInRequestBody;
import com.woowahan.woowahanboardservice.domain.user.dto.request.UserHideRequestBody;
import com.woowahan.woowahanboardservice.domain.user.dto.request.UserJoinRequestBody;
import com.woowahan.woowahanboardservice.domain.user.dto.view.LogInResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("로그인")
    @PostMapping(value = "/login")
    public ResponseEntity<LogInResponse> logIn(@RequestBody LogInRequestBody request) {
        return ResponseEntity.ok().body(userService.logIn(request));
    }

    @ApiOperation("사용자 조회")
    @GetMapping(value = "/member")
    public String searchMember(@RequestParam String id) {
        return userService.searchMember(id);
    }

    // TODO: logout

    // TODO : Oauth2.0 (여유)
}
