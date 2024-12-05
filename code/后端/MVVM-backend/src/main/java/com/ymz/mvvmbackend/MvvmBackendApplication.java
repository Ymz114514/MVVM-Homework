package com.ymz.mvvmbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ymz.mvvmbackend.mapper")
public class MvvmBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvvmBackendApplication.class, args);
    }

}
