package com.yeonwoo.self_introduce.service;

import com.yeonwoo.self_introduce.util.AESUtil;
import com.yeonwoo.self_introduce.entity.User;
import com.yeonwoo.self_introduce.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입 또는 개인정보 저장 시 호출
    public void registerUser(String email, String phone) throws Exception {
        // 암호화 적용
        String encryptedEmail = AESUtil.encrypt(email);
        String encryptedPhone = AESUtil.encrypt(phone);

        // User Entity 객체 생성, 값 세팅
        User user = new User();
        user.setEmail(encryptedEmail);
        user.setPhone(encryptedPhone);

        userRepository.save(user);
    }

    // 마이페이지 등 정보 조회 시 호출
    public String getUserEmail(String encryptedEmail) throws Exception {
        // DB에서 꺼내온 암호문 복호화
        return AESUtil.decrypt(encryptedEmail);
    }
}
