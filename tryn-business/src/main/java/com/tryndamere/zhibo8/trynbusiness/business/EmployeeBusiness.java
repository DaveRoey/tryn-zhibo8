package com.tryndamere.zhibo8.trynbusiness.business;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tryndamere.zhibo8.trynmodel.pojo.Employee;

import java.util.Date;
import java.util.List;

/**
 * Created by Dave on 2018/12/20
 * Describes
 */
public interface EmployeeBusiness {

    void addEmployee(Employee employee);

    void batchSave(List<Employee> employees);

    Employee findEmployeeById(Long empId);

    Employee findEmployeeByIdAndCode(long id, String code);

    Employee findEmpByCode(String code);

    IPage<Employee> selectPageByVo(Page<Employee> page, Date createTime);

    List<Employee> getEmpsFromEsByCode(String code);
}
