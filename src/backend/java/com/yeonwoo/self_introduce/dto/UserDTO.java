package com.yeonwoo.self_introduce.dto;

import java.util.UUID;

public class UserDTO {
    private String id;
    private String password;
    private String role;

    public UserDTO() {
        //
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.password = UUID.randomUUID().toString().replace("-", "");
    }
}
