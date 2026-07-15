package com.yeonwoo.self_introduce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SelfIntroduce {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
