package com.tryndamere.zhibo8.trynapi.action;

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

/**
 * Created by Dave on 2018/12/20
 * Describes
 */
@RestController
@RequestMapping(value = "/emp/*")
public class EmployeeAction extends BaseAction {

    @Autowired
    private EmployeeBusiness employeeBusiness;


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addEmp(@RequestBody Employee employee) {
        HashMap<String, Object> result = new HashMap<>();
        employeeBusiness.addEmployee(employee);
        result.put("result", "success");

        return getSuccessMap(result, HttpStatus.OK);
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
