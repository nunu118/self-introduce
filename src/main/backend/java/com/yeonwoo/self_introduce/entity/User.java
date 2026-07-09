package com.yeonwoo.self_introduce.entity;

import lombok.Builder;

@Builder

public class User {
    private Long id;
    private String password;
    private String role;
    private String name;
    private String image;
    private String email;
    private String phone;
    private String address;
}
