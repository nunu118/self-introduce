package com.yeonwoo.self_introduce.repository;

import com.yeonwoo.self_introduce.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepository {
    public User findById();
    public User findByEmail();
    public User findById(long id);
    List<User> findByEmailAndNameAndPhone(String email, String name, Integer phone);
}
