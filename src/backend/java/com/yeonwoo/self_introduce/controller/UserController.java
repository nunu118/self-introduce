package com.yeonwoo.self_introduce.controller;

import com.yeonwoo.self_introduce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    // 생성자 주입
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 개인정보 안전 저장 API
    // 클라이언트에게는 암호화 되지 않은 데이터를 받으며, 암호화 및 유효성 검사는 UserService에서 처리
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestParam String email,
                                       @RequestParam String phone,
                                       @RequestParam String encryptedEmail,
                                       @RequestParam String encryptedPhone) {
        userService.registerUser(email, phone, encryptedEmail, encryptedPhone);
        return ResponseEntity.ok("회원 정보가 안전하게 저장되었습니다.");
    }
}

