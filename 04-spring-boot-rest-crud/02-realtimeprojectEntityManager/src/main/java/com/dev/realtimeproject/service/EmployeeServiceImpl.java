package com.dev.realtimeproject.service;

import com.dev.realtimeproject.dao.EmployeeDAO;
import com.dev.realtimeproject.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


/*
Important NOTE : @TRANSACTIONAL SHOULD BE AT SERVICE LAYER, NOT AT DAO LAYER!
THIS IS A BEST PRACTISE!
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }
    @Override
    public Employee getEmployee(int id){
        return employeeDAO.getEmployee(id);
    }

    @Override
    public List<Employee> getEmployees(){
        return employeeDAO.getEmployees();
    }
    @Override
    public List<Employee> getEmployeesByLastName(String lastName){
        return employeeDAO.getEmployeesByLastName(lastName);
    }
    @Override
    public List<Employee> getEmployeesByFirstName(String firstName){
        return employeeDAO.getEmployeesByFirstName(firstName);
    }

    @Transactional
    @Override
    public void addEmployee(Employee employee){
        employeeDAO.addEmployee(employee);
    }
    @Transactional
    @Override
    public void updateEmployee(int id,Employee employee){
        employeeDAO.updateEmployee(id,employee);
    }

    @Transactional
    @Override
    public void deleteEmployee(int idToDelete){
        employeeDAO.deleteEmployee(idToDelete);
    }
    @Transactional
    @Override
    public int deleteAllEmployees(){
       return employeeDAO.deleteAllEmployees();
    }


}
