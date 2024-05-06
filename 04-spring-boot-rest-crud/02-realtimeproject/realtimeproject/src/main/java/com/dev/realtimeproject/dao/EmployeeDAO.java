package com.dev.realtimeproject.dao;

import com.dev.realtimeproject.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    public Employee getEmployee(int id);

    public List<Employee> getEmployees();

    public List<Employee> getEmployeesByLastName(String lastName);

    public List<Employee> getEmployeesByFirstName(String firstName);

    public void addEmployee(Employee employee);

    public void updateEmployee(Employee employee);

    public void deleteEmployee(int idToDelete);

    public int deleteAllEmployees();
}
