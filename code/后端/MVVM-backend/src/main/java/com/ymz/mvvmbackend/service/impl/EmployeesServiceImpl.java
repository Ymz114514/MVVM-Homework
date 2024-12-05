package com.ymz.mvvmbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ymz.mvvmbackend.model.Employees;
import com.ymz.mvvmbackend.service.EmployeesService;
import com.ymz.mvvmbackend.mapper.EmployeesMapper;
import org.springframework.stereotype.Service;

/**
* @author ymz
* @description 针对表【employees】的数据库操作Service实现
* @createDate 2024-11-24 09:36:08
*/
@Service
public class EmployeesServiceImpl extends ServiceImpl<EmployeesMapper, Employees>
    implements EmployeesService{

}




