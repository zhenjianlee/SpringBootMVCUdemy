package com.dev.realtimeproject.repository;

import com.dev.realtimeproject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {


    // Note :- The query is determined by the method name findBy<entity property>
    // https://docs.spring.io/spring-data/commons/reference/repositories/query-methods.html
//    List<Employee> findByfirstName(String firstName);
//    List<Employee> findBylastName(String lastName);


}
