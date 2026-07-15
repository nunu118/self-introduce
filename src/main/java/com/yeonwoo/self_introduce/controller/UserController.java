package com.yeonwoo.self_introduce.controller;

import com.yeonwoo.self_introduce.util.AESUtil;
import com.yeonwoo.self_introduce.entity.User;
import com.yeonwoo.self_introduce.repository.UserRepository;
import com.yeonwoo.self_introduce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    // 생성자 주입
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 개인정보 안전 저장 API
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestParam String email,
                                       @RequestParam String phone,
                                       @RequestParam String encryptedEmail,
                                       @RequestParam String encryptedPhone) {
        try {
            userService.registerUser(email, phone);
            AESUtil.encrypt(encryptedEmail);
            AESUtil.encrypt(encryptedPhone);
            return ResponseEntity.ok("회원 정보가 안전하게 저장되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("보안 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}


//    @Autowired
//    private UserRepository userRepository;
//
//
//    @PostMapping("/signup")
//    public String signupPost(@ModelAttribute User user,
//                             @RequestParam("value = "isAdmin", required = false)")) boolean isAdmin {
//        // @RequestParam required = false 는 해당 요청 매개 변수(parameter)가 반드시 필수가 아니게 하는 것.
//        // 매개 변수가 누락되더라도 예외가 발생하지 않도록 설정
//        String userPwd = User.getPwd();
//        String encodedPwd = passwordEncoder.endode(userPwd);
//        User.setPwd(encodePwd);
//        User.setCreate(new date());
//
//        // 사용자 역할 설정
//        User.UserRole userRole = isAdmin ? User.UserRole.ADMIN : User.UserRole.USER;
//
//        // 사용자 정보 저장
//        User.setRole(User.UserRole);
//
//        UserRepository.save(User);
//
//    }
//    public void user() {
//    }
