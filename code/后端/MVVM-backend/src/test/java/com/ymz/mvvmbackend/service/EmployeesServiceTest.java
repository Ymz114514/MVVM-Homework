package com.ymz.mvvmbackend.service;

import com.ymz.mvvmbackend.mapper.EmployeesMapper;
import com.ymz.mvvmbackend.model.Employees;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class EmployeesServiceTest {
    @Autowired
    EmployeesMapper employeesMapper;
    @Test
    void testInsert(){
        Employees build = Employees.builder()
                .phoneNumber("123456")
                .build();
        int insert = 0;
        try {
            insert = employeesMapper.insert(build);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("影响的行数为：{}",insert);
    }
}