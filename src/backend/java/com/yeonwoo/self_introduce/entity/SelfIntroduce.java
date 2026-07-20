package com.yeonwoo.self_introduce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SelfIntroduce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
