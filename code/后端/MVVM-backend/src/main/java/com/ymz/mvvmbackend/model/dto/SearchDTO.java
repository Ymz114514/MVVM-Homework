package com.ymz.mvvmbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
@Data
@AllArgsConstructor
public class SearchDTO {
    // 查询参数
    private Integer deptId;//根据部门查询
    private Date[] hireDate;

    // 分页参数
    private Integer currentPage;
    private Integer pageSize;
}
