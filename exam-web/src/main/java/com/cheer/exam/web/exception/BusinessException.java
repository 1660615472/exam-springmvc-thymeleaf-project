package com.cheer.exam.web.exception;


/*
* 自定义异常类（业务异常）
* */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super (message);
    }
}
