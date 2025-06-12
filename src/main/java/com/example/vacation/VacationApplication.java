package com.example.vacation;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.example.vacation.mvc.mapper")
@SpringBootApplication
public class VacationApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacationApplication.class, args);
	}

}
