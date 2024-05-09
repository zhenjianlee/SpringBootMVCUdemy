package com.dev.realtimeproject.controller;

import com.dev.realtimeproject.dao.EmployeeDAO;
import com.dev.realtimeproject.dto.EmployeeDTO;
import com.dev.realtimeproject.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employee/")
public class EmployeeController {

    private EmployeeDAO employeeDAO;

    public EmployeeController(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    @GetMapping("")
    public List<Employee> getEmployee(){
        return employeeDAO.getEmployees();
    }

    @GetMapping("{id}")
    public Employee getEmployee(@PathVariable int id){
            return employeeDAO.getEmployee(id);
    }

    @GetMapping("getLastName/{lastName}")
    public List<Employee>getEmployeeLastName(@PathVariable String lastName){
        return employeeDAO.getEmployeesByLastName(lastName);
    }

    @GetMapping("getFirstName/{firstName}")
    public List<Employee>getEmployeeFirstName(@PathVariable String firstName){
        return employeeDAO.getEmployeesByLastName(firstName);
    }

    @PostMapping("addEmployee")
    public void addEmployee(@RequestBody EmployeeDTO employeeDTO){
        Employee newEmployee = new Employee(employeeDTO.getFirstName(),
                employeeDTO.getLastName(),employeeDTO.getEmail());
        employeeDAO.addEmployee(newEmployee);
    }

    @PutMapping("updateEmployee/{id}")
    public void updateEmployee(@PathVariable int id,@RequestBody EmployeeDTO employeeDTO){
        employeeDAO.updateEmployee(id,new Employee(employeeDTO.getFirstName(),
                employeeDTO.getLastName(),employeeDTO.getEmail()));
    }

    @DeleteMapping("delEmployee/{id}")
    public void delEmployee(@PathVariable int id){
        employeeDAO.deleteEmployee(id);
    }

    @DeleteMapping("delAllEmployee")
    public void delAllEmployee(){
        System.out.println("!!! ATTEMPTING TO DELETE ALL EMPLOYEES !!!");
        int deleted =employeeDAO.deleteAllEmployees();
        System.out.println("!!!! Deleted ALL EMPLOYEES!!! TOTAL="+deleted);
    }

}
