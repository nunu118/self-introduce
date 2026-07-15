package com.yeonwoo.self_introduce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.yeonwoo.self_introduce")
public class SelfIntroduceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelfIntroduceApplication.class, args);
	}

}