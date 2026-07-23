package com.yeonwoo.self_introduce.repository;

import com.yeonwoo.self_introduce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 중복 가입 체크를 위한 존재 확인
    boolean existsByEmail(String email);

    // Optional로 리턴 타입 지정
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    Optional<User> findByEncryptedEmail(String encryptedEmail);
    Optional<User> findByEncryptedPhone(String encryptedPhone);
}
