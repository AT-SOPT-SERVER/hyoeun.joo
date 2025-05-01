package org.sopt.controller;

import org.sopt.dto.request.UserCreate;
import org.sopt.service.UserService;
import org.sopt.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserCreate userCreate) {
        userService.createUser(userCreate);
        return ResponseEntity.ok(ResponseUtil.success("유저 생성 성공", null));
    }
}
