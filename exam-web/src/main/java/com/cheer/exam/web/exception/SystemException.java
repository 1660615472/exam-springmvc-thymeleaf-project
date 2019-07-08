package com.cheer.exam.web.exception;
/*
* 自定义系统异常类
* */
public class SystemException extends RuntimeException {
    public SystemException(String message) {
        super (message);
    }
}
