package com.tryndamere.zhibo8.trynapi.action;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dave on 2018/12/20
 * Describes
 */
public class BaseAction {

    ResponseEntity<Map<String,Object>> getSuccessMap(Map<String,Object> result,HttpStatus httpStatus){
        return new ResponseEntity<Map<String, Object>>(result,httpStatus);
    }

    ResponseEntity<Map<String,Object>> getExceptionMap(String message,HttpStatus httpStatus){
        Map<String,Object> result=new HashMap<>(2);
        result.put("case",message);
        return new ResponseEntity<Map<String, Object>>(result,httpStatus);
    }


}
