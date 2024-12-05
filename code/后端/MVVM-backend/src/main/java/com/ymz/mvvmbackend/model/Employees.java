package com.ymz.mvvmbackend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName employees
 */
@TableName(value ="employees")
@Data
@Builder
public class Employees implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer empId;

    /**
     * 
     */
    private String firstName;

    /**
     * 
     */
    private String lastName;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String phoneNumber;

    /**
     * 
     */
    private Date hireDate;

    /**
     * 
     */
    private String jobTitle;

    /**
     * 
     */
    private BigDecimal salary;

    /**
     * 
     */
    private Integer deptId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}