package com.dev.realtimeproject;

import com.dev.realtimeproject.entity.Employee;
import com.dev.realtimeproject.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class EmployeeLoader {

    private EmployeeService employeeService;

    public EmployeeLoader(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    //@PostConstruct
    public void generateRandomEmployees(){
        System.out.println("!!! Generating new random employees !!!");
        for (int i=0; i< 15; i++){
            employeeService.addEmployee(Employee.generateRandomEmployee());
        }
        
    }
}
