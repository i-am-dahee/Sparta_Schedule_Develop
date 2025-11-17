package com.example.schedule_develop.user.controller;

import com.example.schedule_develop.user.dto.*;
import com.example.schedule_develop.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 유저 생성 (회원가입)
    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(
            @RequestBody UserRequest request
    ) {
        UserResponse result = userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 유저 전체 조회
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> result = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 유저 개별 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUser(
            @PathVariable Long userId
    ) {
        UserResponse result = userService.getOne(userId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 유저 수정
    @PatchMapping("/users/{userId}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long userId,
            @RequestBody UserRequest request
    ) {
        UserResponse result = userService.update(userId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 유저 삭제
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<UserResponse> deleteUser(
            @PathVariable Long userId
    ) {
        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 로그인
    @PostMapping("/users/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request,
            HttpSession session
    ) {
        LoginResponse result = userService.login(request);
        SessionUser sessionUser = new SessionUser(result.getId(), result.getEmail());
        session.setAttribute("loginUser", sessionUser);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 로그아웃
    @PostMapping("/users/logout")
    public ResponseEntity<Void> logout(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            HttpSession session
    ) {
        if (sessionUser == null) {
            return ResponseEntity.badRequest().build();
        }
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 테스트
    @GetMapping("/users/test")
    public ResponseEntity<String> test(@SessionAttribute(name = "loginUser") SessionUser sessionUser) {
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
