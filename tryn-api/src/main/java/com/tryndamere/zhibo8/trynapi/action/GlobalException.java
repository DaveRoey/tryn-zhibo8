package com.tryndamere.zhibo8.trynapi.action;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * @Author Dave
 * @Date 2018/12/23
 * @Description 全局异常处理
 */
@RestControllerAdvice
public class GlobalException extends BaseAction {

    /**
     * 通用异常
     *
     * @param throwable
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, Object>> catchAll(Throwable throwable) {
        return getExceptionMap(throwable.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }




}
