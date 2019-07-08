package com.cheer.exam.web.controller;



import org.omg.CORBA.SystemException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

//全局异常处理类
@ControllerAdvice
public class GlobalExceptionController {

    //异常默认处理
    @ExceptionHandler
    public String handle(Exception e, Model model){
        model.addAttribute ( "message",e.getMessage () );
        model.addAttribute ( "cause",e.getCause () );
        return "error/error";
    }

    /*//业务异常
    @ExceptionHandler(BusinessException.class)
    public String handleBusinessException(BusinessException e,Model model){
        model.addAttribute ( "message",e.getMessage () );
        model.addAttribute ( "cause",e.getCause () );
        return "error/businessError";
    }*/

    //系统异常
    @ExceptionHandler(SystemException.class)
    public String hanleSystemException(SystemException e,Model model){
        model.addAttribute ( "message",e.getMessage () );
        model.addAttribute ( "cause",e.getMessage () );
        return "error/systemError";
    }

    //404异常
    @ExceptionHandler({
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            MissingServletRequestParameterException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestPartException.class,
            BindException.class,
            NoHandlerFoundException.class})
    public String handlePageNotFoundException(Exception e, Model model) {
        model.addAttribute("message", e.getMessage());
        model.addAttribute("cause", e.getCause());
        return "error/404";
    }
}
