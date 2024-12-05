package com.ymz.mvvmbackend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName employeevo
 */
@TableName(value ="employeevo")
@Data
public class Employeevo implements Serializable {
    /**
     * 
     */
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

    /**
     * 
     */
    private String deptName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}