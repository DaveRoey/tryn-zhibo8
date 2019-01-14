package com.tryndamere.zhibo8.trynapi.action;

import brave.Tracer;
import com.tryndamere.zhibo8.trynbusiness.business.EmployeeBusiness;
import com.tryndamere.zhibo8.trynmodel.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Dave on 2018/12/20
 * Describes
 */
@RestController
@RequestMapping(value = "/emp/*")
public class EmployeeAction extends BaseAction {

    @Autowired
    private EmployeeBusiness employeeBusiness;
    @Autowired
    private Tracer tracer;


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addEmp(@RequestBody Employee employee) {
        HashMap<String, Object> result = new HashMap<>();
        employeeBusiness.addEmployee(employee);
        result.put("result", "success");

        return getSuccessMap(result, HttpStatus.OK);
    }

    @RequestMapping("/hi2")
    public String hi2() throws InterruptedException {
        int millis = new Random().nextInt(1000);
        Thread.sleep(millis);
        this.tracer.currentSpan().tag("random-sleep-millis", String.valueOf(millis));
        return "hi2";
    }



    @RequestMapping(value = "find", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> find(@RequestBody Employee employee) {
        HashMap<String, Object> result = new HashMap<>();
        Employee emp = employeeBusiness.findEmployeeByIdAndCode(employee.getId(), employee.getCode());
        result.put("result", emp);

        return getSuccessMap(result, HttpStatus.OK);
    }


    @RequestMapping(value = "findById", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> fifindByIdnd(@RequestBody Employee employee) {
        HashMap<String, Object> result = new HashMap<>();
        Employee emp = employeeBusiness.findEmployeeById(employee.getId());
        result.put("result", emp);
        return getSuccessMap(result, HttpStatus.OK);
    }


    @RequestMapping(value = "findByCode", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findByCode(@RequestBody Employee employee) {
        HashMap<String, Object> result = new HashMap<>();
        Employee emp = employeeBusiness.findEmpByCode(employee.getCode());
        result.put("result", emp);
        return getSuccessMap(result, HttpStatus.OK);
    }

}
