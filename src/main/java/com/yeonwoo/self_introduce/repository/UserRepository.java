package com.yeonwoo.self_introduce.repository;

import com.yeonwoo.self_introduce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findAll();
    public List<User> findByPhone(Integer phone);
    public List<User> findByEmail(String email);
    public List<User> findByEncryptedEmail(String encryptedEmail);
    public List<User> findByEncryptedPhone(String encryptedphone);
}
