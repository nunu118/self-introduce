package com.yeonwoo.self_introduce.common;

import org.springframework.stereotype.Repository;

@Repository
public interface SelfIntroduceRepository {
    public void save();
    public void delete();
    public void update();
    public void find();
    public void findAll();
}
