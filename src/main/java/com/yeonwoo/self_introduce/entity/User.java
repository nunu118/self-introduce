package com.yeonwoo.self_introduce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter

// Serializable 인터페이스를 사용 시
// 기존 Springboot 서버 재구동시 별도로 작성한 클래스는 자동으로 제거되나
// Serializable 인터페이스 구현 시 자동으로 제거되지 않음

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String role;
    private String name;
    private String image;
    private String address;
    private String email;
    private String phone;
    private String encryptedEmail;
    private String encryptedPhone;



    public enum UserRole{
        ADMIN, USER
    }

    public String getRole() {
        return role.trim().toUpperCase();
    }
    public String setRole(String role) {
        return role.trim().toUpperCase();
    }

}