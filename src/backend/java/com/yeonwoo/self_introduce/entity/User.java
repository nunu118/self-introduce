package com.yeonwoo.self_introduce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 접근 제어


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    private String image;
    private String address;
    private String email;
    private String phone;
    private String encryptedEmail;
    private String encryptedPhone;

    public enum UserRole{
        ADMIN, USER
    }

    @Builder
    public User(String name, String password, String email, String encryptedEmail, String encryptedPhone, UserRole role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.encryptedEmail = encryptedEmail;
        this.encryptedPhone = encryptedPhone;
        this.role = role != null ? role : UserRole.USER;
    }

    // 프로필 정보 수정 메서드
    public void updateProfile(String name, String email, String phone, String image, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.address = address;
    }
}