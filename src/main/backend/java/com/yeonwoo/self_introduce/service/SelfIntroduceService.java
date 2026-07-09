package com.yeonwoo.self_introduce.service;

import com.yeonwoo.self_introduce.repository.SelfIntroduceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SelfIntroduceService {
    private final SelfIntroduceRepository selfIntroduceRepository;

    @Autowired
    public SelfIntroduceService(SelfIntroduceRepository selfIntroduceRepository) {
        this.selfIntroduceRepository = selfIntroduceRepository;
    }

    public String home() {
        return "성장하는 프로그래머, 복연우입니다.";
    }
    public void save() {

    }
    public void delete() {

    }
    public void update() {

    }
    public void find() {

    }
    public void findAll() {

    }
}
