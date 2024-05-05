package com.dev.CRUDDemo.dao;

import com.dev.CRUDDemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private EntityManager entityManager;

    public StudentDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName asc",Student.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        //System.out.println(this.getClass()+" findByLastName "+ lastName);
        TypedQuery<Student>theQuery = entityManager
                .createQuery("FROM Student WHERE lastName=:theData",Student.class);
        theQuery.setParameter("theData",lastName);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(int idToDelete){
        Student foundStudent = findById(idToDelete);
        entityManager.remove(foundStudent);
    }


    //this doesnt work
    @Transactional
    public void delete2(Student theStudent){
        entityManager.remove(theStudent);
    }
    @Transactional
    public int deleteAll(){
        int numRowsDelete = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDelete;
    }
}
