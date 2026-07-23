package com.yeonwoo.self_introduce.service;

import com.yeonwoo.self_introduce.exception.BusinessException;
import com.yeonwoo.self_introduce.exception.ErrorCode;
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
    public void registerUser(String name, String password, String email, String phone) {

        // 유효성 검사
        validateRegisterInput(name, email);
        String encryptedEmail;
        String encryptedPhone;

        // 암호화 예외 처리
        try {
            encryptedEmail = AESUtil.encrypt(email);
            encryptedPhone = AESUtil.encrypt(phone);
        } catch (Exception e) {
            log.error("암호화 실패 - email: {}, phone: {}", email, e);
            throw new BusinessException(ErrorCode.ENCRYPTION_ERROR);
        }

        // Builder 객체 생성, 값 세팅
        User user = User.builder()
                .name(name)
                .password(password)
                .email(email)
                .encryptedEmail(encryptedEmail)
                .encryptedPhone(encryptedPhone)
                .role(User.UserRole.USER)
                .build();

        userRepository.save(user);
    }

    // 마이페이지 등 정보 조회 시 호출
    public String getUserEmail(String encryptedEmail) {
        // DB에서 꺼내온 암호문 복호화
        try {
            return AESUtil.decrypt(encryptedEmail);
        } catch (Exception e) {
            log.error("복호화 실패", e);
            throw new BusinessException(ErrorCode.DECRYPTION_ERROR);
        }
    }

    // 회원가입 검증
    private void validateRegisterInput(String name, String email) {
        if (name == null || name.isEmpty() || email == null || email.isEmpty()) {
            throw new BusinessException(ErrorCode.INVALID_INPUT);
        }
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }
    }
}
