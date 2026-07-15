package com.yeonwoo.self_introduce.controller;

import com.yeonwoo.self_introduce.dto.ProjectCreateRequest;
import com.yeonwoo.self_introduce.service.SelfIntroduceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor

public class SelfIntroduceController {
    private final SelfIntroduceService selfIntroduceService;

    @GetMapping(value = "/", name = "main")
    public ResponseEntity<String> main() {
        return ResponseEntity.ok(selfIntroduceService.home());
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> registerUser(@RequestBody ProjectCreateRequest createRequest) {
        return ResponseEntity.ok(selfIntroduceService.home());
    }

    @PutMapping("/main")
    public User updateUser(@RequestBody User user) {
        return user;
    }

    @DeleteMapping("/main")
    public User deleteUser(@RequestBody User user) {
        return user;
    }

}
