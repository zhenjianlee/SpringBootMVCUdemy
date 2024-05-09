package com.dev.realtimeproject;

import com.dev.realtimeproject.dao.EmployeeDAO;
import com.dev.realtimeproject.entity.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class EmployeeLoader {

    private EmployeeDAO employeeDAO;

    public EmployeeLoader(EmployeeDAO employeeDAO){
        this.employeeDAO=employeeDAO;
    }

    @PostConstruct
    public void generateRandomEmployees(){
        System.out.println("!!! Generating new random employees !!!");
        for (int i=0; i< 15; i++){
            employeeDAO.addEmployee(Employee.generateRandomEmployee());
        }
        
    }
}
