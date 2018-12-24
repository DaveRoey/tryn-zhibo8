package com.tryndamere.zhibo8.trynbusiness.business.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tryndamere.zhibo8.trynbusiness.business.EmployeeBusiness;
import com.tryndamere.zhibo8.trynmodel.pojo.Employee;
import com.tryndamere.zhibo8.trynpersistent.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Dave on 2018/12/20
 * Describes
 */
@Service
public class EmployeeBusinessImpl implements EmployeeBusiness {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public void addEmployee(Employee employee) {
        empMapper.insert(employee);
    }

    @Override
    public void batchSave(List<Employee> employees) {
        empMapper.batchSaveEmp(employees);
    }

    @Override
    public Employee findEmployeeById(Long empId) {
        return empMapper.selectById(empId);
    }

    @Override
    public Employee findEmployeeByIdAndCode(long id, String code) {

       /* String key = String.format("shard:%s:%s", id, code);
        Employee value = (Employee) redisTemplate.opsForValue().get(key);
        if (null == value) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("code", code);
            value = empMapper.selectOne(new QueryWrapper<Employee>().allEq(map));
            if (value != null) {
                redisTemplate.opsForValue().set(key, value);
            }
        }
        return value;*/
       return null;


    }

    @Override
    public Employee findEmpByCode(String code) {
        return empMapper.selectOne(new LambdaQueryWrapper<Employee>().eq(Employee::getCode, code));
    }

    @Override
    public IPage<Employee> selectPageByVo(Page<Employee> page, Date createTime) {
        return empMapper.selectPageVo(page, createTime);
    }

    @Override
    public List<Employee> getEmpsFromEsByCode(String code) {

       /* return empRepository.findEmployeesByCode(code);*/

       return null;
    }
}
