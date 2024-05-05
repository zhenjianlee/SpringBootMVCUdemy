package com.dev.CRUDDemo.dao;

import com.dev.CRUDDemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student student);

    Student findById(int id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    void update(Student theStudent);

    void delete(int idToDelete);


    //this doesnt work
    void delete2(Student theStudent);

    int deleteAll();
}
