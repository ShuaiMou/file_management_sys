package com.practice.file_management_sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.practice.file_management_sys.mapper")
public class FileManagementSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileManagementSysApplication.class, args);
	}

}
