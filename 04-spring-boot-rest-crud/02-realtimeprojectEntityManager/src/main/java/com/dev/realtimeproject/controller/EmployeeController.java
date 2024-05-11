package com.dev.realtimeproject.controller;

import com.dev.realtimeproject.dao.EmployeeDAO;
import com.dev.realtimeproject.dto.EmployeeDTO;
import com.dev.realtimeproject.entity.Employee;
import com.dev.realtimeproject.exception.EmployeeNotFoundException;
import com.dev.realtimeproject.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employee/")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public List<Employee> getEmployee(){
        return employeeService.getEmployees();
    }

    @GetMapping("{id}")
    public Employee getEmployee(@PathVariable int id){
        Employee foundEmployee = employeeService.getEmployee(id);
        if (foundEmployee==null){
            throw new EmployeeNotFoundException("Cannot find Employee "+id);
        }
        return foundEmployee;
    }

    @GetMapping("getLastName/{lastName}")
    public List<Employee>getEmployeeLastName(@PathVariable String lastName){
        return employeeService.getEmployeesByLastName(lastName);
    }

    @GetMapping("getFirstName/{firstName}")
    public List<Employee>getEmployeeFirstName(@PathVariable String firstName){
        return employeeService.getEmployeesByLastName(firstName);
    }

    @PostMapping("addEmployee")
    public void addEmployee(@RequestBody EmployeeDTO employeeDTO){
        Employee newEmployee = new Employee(employeeDTO.getFirstName(),
                employeeDTO.getLastName(),employeeDTO.getEmail());
        employeeService.addEmployee(newEmployee);
    }

    @PutMapping("updateEmployee/{id}")
    public void updateEmployee(@PathVariable int id,@RequestBody EmployeeDTO employeeDTO){
        employeeService.updateEmployee(id,new Employee(employeeDTO.getFirstName(),
                employeeDTO.getLastName(),employeeDTO.getEmail()));
    }

    @DeleteMapping("delEmployee/{id}")
    public void delEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
    }

    @DeleteMapping("delAllEmployee")
    public void delAllEmployee(){
        System.out.println("!!! ATTEMPTING TO DELETE ALL EMPLOYEES !!!");
        int deleted =employeeService.deleteAllEmployees();
        System.out.println("!!!! Deleted ALL EMPLOYEES!!! TOTAL="+deleted);
    }

}
