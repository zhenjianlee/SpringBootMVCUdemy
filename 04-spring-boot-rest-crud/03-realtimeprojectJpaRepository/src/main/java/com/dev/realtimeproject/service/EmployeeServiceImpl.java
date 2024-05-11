package com.dev.realtimeproject.service;

import com.dev.realtimeproject.repository.EmployeeRepository;
import com.dev.realtimeproject.entity.Employee;
import com.dev.realtimeproject.exception.EmployeeNotFoundException;
import com.dev.realtimeproject.exception.EmployeesNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/*
Important NOTE : @TRANSACTIONAL SHOULD BE AT SERVICE LAYER, NOT AT DAO LAYER!
THIS IS A BEST PRACTISE!
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    @Override
    public Employee getEmployee(int id){
        Optional<Employee>foundEmployee = employeeRepository.findById(id);
        if (foundEmployee.isEmpty()){
            throw new EmployeeNotFoundException(id);
        }
        return foundEmployee.get();
    }

    @Override
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getEmployeesByFirstName(String firstName){
        List<Employee> employeeList = employeeRepository.findByfirstName(firstName);
        if (employeeList.size()==0){
            throw new EmployeesNotFoundException(firstName);
        }
        return employeeList;
    }
    @Override
    public List<Employee> getEmployeesByLastName(String lastName){
        List<Employee> employeeList = employeeRepository.findBylastName(lastName);
        if (employeeList.size()==0){
            throw new EmployeesNotFoundException(lastName);
        }
        return employeeList;
    }

    @Override
    public void addEmployee(Employee employee){
        employeeRepository.saveAndFlush(employee);
    }

    @Override
    public void updateEmployee(int id,Employee employee){
        Employee foundEmployee = getEmployee(id);
        foundEmployee.setEmail(employee.getEmail());
        foundEmployee.setFirstName(employee.getFirstName());
        foundEmployee.setLastName(employee.getLastName());
        employeeRepository.saveAndFlush(foundEmployee);
    }


    @Override
    public void deleteEmployee(int idToDelete){
        Employee foundEmployee = getEmployee(idToDelete);
        employeeRepository.delete(foundEmployee);
    }

    @Override
    public void deleteAllEmployees(){
       employeeRepository.deleteAll();
    }


}
