package com.yeonwoo.self_introduce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.yeonwoo.self_introduce.entity.SelfIntroduce;

@Repository
public interface SelfIntroduceRepository extends JpaRepository<SelfIntroduce, Long> {

}
