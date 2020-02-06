package com.example.demo1.dao;

import com.example.demo1.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDao {

    //模拟数据库
    private static Map<Integer, Department> departments ;

    static {
        departments = new HashMap<>();

        departments.put(101,new Department(101,"研发部"));
        departments.put(102,new Department(102,"后勤部"));
        departments.put(103,new Department(103,"市场部"));
        departments.put(104,new Department(104,"运维部"));
    }
    //获得所有部门的信息
    public Collection<Department> getDepartments(){
        return departments.values();
    }

    //通过id得到某个部门
    public Department getDepartmentById(Integer id){
        return departments.get(id);
    }

}
