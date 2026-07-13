package com.yeonwoo.self_introduce.controller;

import com.yeonwoo.self_introduce.entity.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @PostMapping("/signup")
    public String signupPost(@ModelAttribute User user,
                             @RequestParam("value = "isAdmin", required = false)")) boolean isAdmin {
        // @RequestParam required = false 는 해당 요청 매개 변수(parameter)가 반드시 필수가 아니게 하는 것.
        // 매개 변수가 누락되더라도 예외가 발생하지 않도록 설정
        String userPwd = user.getPwd();
        String encodedPwd = passwordEncoder.endode(userPwd);
        user.setPwd(encodePwd);
        user.setCreate(new date());

        // 사용자 역할 설정
        User.UserRole userRole = isAdmin ? User.UserRole.ADMIN : User.UserRole.USER;

        // 사용자 정보 저장
        user.setRole(userRole);

        userRepository.save(user);

    }
    public void user() {
    }

}
