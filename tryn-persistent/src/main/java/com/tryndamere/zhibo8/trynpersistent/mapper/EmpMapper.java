package com.tryndamere.zhibo8.trynpersistent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tryndamere.zhibo8.trynmodel.pojo.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author Dave
 * @Date 2018/12/23
 * @Description
 */
@Repository
public interface EmpMapper extends BaseMapper<Employee> {

    /**
     * 批量插入
     *
     * @param emps
     */
    void batchSaveEmp(@Param("emps") List<Employee> emps);


    /**
     * 分页查询
     *
     * @param page
     * @param state
     * @return
     */
    IPage<Employee> selectPageVo(Page page, @Param("createDate") Date createDate);

}
