package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.resp.ResultData;
import org.example.resp.ReturnCodeEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResultData<String> exception(Exception e){
        log.error(e.getMessage());
        System.out.println("进入全局异常处理------");
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());

    }
}
