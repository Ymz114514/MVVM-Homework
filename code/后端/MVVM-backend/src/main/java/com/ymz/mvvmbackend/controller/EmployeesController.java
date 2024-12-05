package com.ymz.mvvmbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ymz.mvvmbackend.model.Employees;
import com.ymz.mvvmbackend.model.Employeevo;
import com.ymz.mvvmbackend.model.dto.SearchDTO;
import com.ymz.mvvmbackend.model.result.Result;
import com.ymz.mvvmbackend.service.EmployeesService;
import com.ymz.mvvmbackend.service.EmployeevoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ymz")
public class EmployeesController {

    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private EmployeevoService employeevoService;

    //批量删除员工
    //问题：前端给这个接口传递数组，但是后端不知为什么收集不到。
    @DeleteMapping("/employees")
    public Result delete(@RequestBody List<Integer> ids){
        employeesService.removeBatchByIds(ids);
        return Result.ok();
    }
    //添加员工
    @PostMapping("/employees")
    public Result save(@RequestBody Employees employees){
        employeesService.save(employees);
        return Result.ok();
    }

    //搜索员工
    @PostMapping("/search")
    public Result search(@RequestBody SearchDTO searchDTO){
        Page<Employeevo> page = new Page<>(searchDTO.getCurrentPage(), searchDTO.getPageSize());
        QueryWrapper<Employeevo> queryWrapper = new QueryWrapper<>();
        if(searchDTO.getDeptId() != null){
            queryWrapper.eq("dept_id", searchDTO.getDeptId());
        }
        if (searchDTO.getHireDate() != null && searchDTO.getHireDate().length == 2) {
            queryWrapper.between("hire_date", searchDTO.getHireDate()[0], searchDTO.getHireDate()[1]);
        }
        Page<Employeevo> paged = employeevoService.page(page, queryWrapper);
        return Result.ok(paged);
    }
    //修改指定id的员工
    @PutMapping("/employees/{id}")
    public Result update(@PathVariable("id") Integer id, @RequestBody Employees employees){
        employees.setEmpId(id);
        employeesService.updateById(employees);
        return Result.ok();
    }

}
