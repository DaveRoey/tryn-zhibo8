package com.tryndamere.zhibo8.trynmodel.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

/**
 * Created by Dave on 2018/12/24
 * Describes
 */
@Data
@AllArgsConstructor
public class Employee {

    private Long id;
    private String code;
    private Date createTime;
}
