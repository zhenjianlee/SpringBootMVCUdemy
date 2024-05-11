package com.dev.realtimeproject.dao;

import com.dev.realtimeproject.entity.Employee;
import com.dev.realtimeproject.exception.EmployeeNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Important NOTE : @TRANSACTIONAL SHOULD BE AT SERVICE LAYER, NOT AT DAO LAYER!
THIS IS A BEST PRACTISE!
 */

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private EntityManager entityManager;

    public EmployeeDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Employee getEmployee(int id){
        Employee foundEmployee = entityManager.find(Employee.class,id);
        if (foundEmployee == null){
            throw new EmployeeNotFoundException(id);
        }
        return entityManager.find(Employee.class, id);
    }

    public List<Employee> getEmployees(){
        TypedQuery<Employee> findAll = entityManager.createQuery("FROM Employee order by lastName asc",Employee.class);
        return findAll.getResultList();
    }

    public List<Employee>getEmployeesByLastName(String lastName){
        TypedQuery<Employee> lastNameList = entityManager
                .createQuery("FROM Employee WHERE lastName=:theData",Employee.class);
        lastNameList.setParameter("theData",lastName);
        return lastNameList.getResultList();
    }

    public List<Employee>getEmployeesByFirstName(String firstName){
        TypedQuery<Employee> firstNameList = entityManager
                .createQuery("FROM Employee WHERE firstName=:theData",Employee.class);
        firstNameList.setParameter("theData",firstName);
        return firstNameList.getResultList();
    }


    public void addEmployee(Employee employee){
        entityManager.persist(employee);
    }


    public void updateEmployee(int id,Employee employee){
        Employee foundEmployee = getEmployee(id);
        if (foundEmployee == null){
            throw new EmployeeNotFoundException(id);
        }
        foundEmployee.setFirstName(employee.getFirstName());
        foundEmployee.setLastName(employee.getLastName());
        foundEmployee.setEmail(employee.getEmail());
        entityManager.merge(foundEmployee);
    }

    public void deleteEmployee(int idToDelete){
        Employee employeeToDelete = entityManager.find(Employee.class,idToDelete);
        if (employeeToDelete == null){
            throw new EmployeeNotFoundException(idToDelete);
        }
        entityManager.remove(employeeToDelete);
    }

    public int deleteAllEmployees(){
        int rowsDeleted = entityManager.createQuery("DELETE FROM Employee").executeUpdate();
        return rowsDeleted;
    }
}
