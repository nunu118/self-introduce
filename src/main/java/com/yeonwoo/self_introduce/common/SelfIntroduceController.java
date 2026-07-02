package com.yeonwoo.self_introduce.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class SelfIntroduceController {
    @GetMapping("/api/selfintro")
    public String home() {
        return "안녕하세요";
    }

}
