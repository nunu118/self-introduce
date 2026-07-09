package com.yeonwoo.self_introduce.controller;

import com.yeonwoo.self_introduce.service.SelfIntroduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:35729")

public class SelfIntroduceController {
    @Autowired
    SelfIntroduceService selfIntroduceService;

    @GetMapping("/api/main")
    public String home() {

        return "안녕하세요";
    }

    @PostMapping("/api/main")
    public String create() {
        return "등록이 완료되었습니다";
    }

    @PutMapping("/api/main")
    public String update() {
        return "수정이 완료되었습니다";
    }

    @DeleteMapping("/api/main")
    public String delete() {
        return "삭제가 완료되었습니다";
    }

    @PatchMapping("/api/main")
    public String patch() {
        return "변경이 완료되었습니다";
    }
}
