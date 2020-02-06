package com.example.demo1.dao;

import com.example.demo1.pojo.Department;
import com.example.demo1.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//员工Dao
@Repository
public class EmployeeDao {

    //模拟数据库
    private static Map<Integer, Employee> employees ;

    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<>();

        employees.put(1001,new Employee(1001,"A","11@qq.com",0,new Department(101,"研发部"),new Date()));
        employees.put(1002,new Employee(1002,"B","22@qq.com",0,new Department(101,"研发部"),new Date()));
        employees.put(1003,new Employee(1003,"C","33@qq.com",0,new Department(103,"市场部"),new Date()));

    }

    //模拟主键自增
    private static Integer initId= 1004;

    //增加一个员工
    public void save(Employee employee){
        if (employee.getId()==null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));

        employees.put(employee.getId(),employee);
    }

    //查询所有员工
    public Collection<Employee> getAll(){
        return employees.values();
    }

    //通过id查询员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    //修改员工
    public void updateEmployee(Employee employee){
        employees.put(employee.getId(),employee);
    }

    //删除员工
    public void delete(Integer id){
        employees.remove(id);
    }




}
