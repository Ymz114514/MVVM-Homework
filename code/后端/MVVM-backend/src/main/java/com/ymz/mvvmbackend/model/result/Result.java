package com.ymz.mvvmbackend.model.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private Integer code;
    private String message;
    private Object data;

    //快速获取Result对象的静态方法
    public static Result ok(Object data){
        return new Result(1,"success",data);
    }
    public static Result ok(){
        return ok(null);
    }
    public static Result error(String message){
        return new Result(0,message,null);
    }

}
