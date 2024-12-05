package com.ymz.mvvmbackend.exception;

//全局异常处理器

import com.ymz.mvvmbackend.model.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MyException.class)
    public Result handleException(Exception e){
        //处理异常
        return Result.error(e.getMessage());
    }
}
