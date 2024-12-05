package com.ymz.mvvmbackend.mapper;

import com.ymz.mvvmbackend.model.Employees;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author ymz
* @description 针对表【employees】的数据库操作Mapper
* @createDate 2024-11-24 09:36:08
* @Entity com.ymz.mvvmbackend.model.Employees
*/
//没有Mapper注解可以么？我看官方文档都没有用，试试吧
public interface EmployeesMapper extends BaseMapper<Employees> {
}




