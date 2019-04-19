package com.tryndamere.zhibo8.trynapi.action;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dave on 2018/12/20
 * Describes
 */
@RestController
@RequestMapping(value = "/api/*")
public class EmployeeAction extends BaseAction {

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }


    public static void main(String[] args) {
        String a="processdata\n" +
                "quest_sl_temp_explain1\n" +
                "task\n" +
                "temp_relation\n" +
                "tlevel\n" +
                "unit\n" +
                "useroperation\n" +
                "vendor\n" +
                "wf_h_processinst\n" +
                "wf_roletemp\n" +
                "wfactivityinst\n" +
                "wfauditlog\n" +
                "wfprocessdefine\n" +
                "wfprocessinst\n" +
                "wftransctrl\n" +
                "wftransition";

        for (String s : a.split("\n")) {
            System.out.println("drop table if EXISTS "+s+";");
        }
    }
}
