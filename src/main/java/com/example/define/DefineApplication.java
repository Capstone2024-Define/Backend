package com.example.define;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@MapperScan("com.example.define.mapper") // (MyBatis) 매퍼 위해
//@EnableJpaRepositories(basePackages = "com.example.define.repo") // (JPA) 레포 위해
@SpringBootApplication
public class DefineApplication {

	public static void main(String[] args) {
		SpringApplication.run(DefineApplication.class, args);
	}

}
