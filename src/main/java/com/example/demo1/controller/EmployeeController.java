package com.example.demo1.controller;

import com.example.demo1.dao.DepartmentDao;
import com.example.demo1.dao.EmployeeDao;
import com.example.demo1.pojo.Department;
import com.example.demo1.pojo.Employee;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String toCustomer(Model model) {
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps", all);
        return "list";
    }

    @GetMapping("/emp")
    public String toAddEmp(Model model) {
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "empadd";
    }

    @PostMapping("/emp")
    public String saveEmp(Employee employee){
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //从前端获取想要修改员工的id，并进入修改页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id")Integer id,Model model){
        Employee employeeById = employeeDao.getEmployeeById(id);
        Collection<Department> departments = departmentDao.getDepartments();

        model.addAttribute("emp",employeeById);
        model.addAttribute("departments",departments);
        return "empupdate";
    }

    //修改员工
    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employeeDao.updateEmployee(employee);
        return "redirect:/emps";
    }

    //删除员工
    @GetMapping("/deleteEmp/{id}")
    public String deleteEmp(@PathVariable("id")Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
