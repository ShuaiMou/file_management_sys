package com.practice.file_management_sys.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: Saul
 * @Date: 2/3/20 9:44 下午
 * @Description:业务异常类
 */

@Setter
@Getter
public class BusinessException extends RuntimeException{
    private int code;
    private String msg;

    public BusinessException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public  BusinessException(){}
}
