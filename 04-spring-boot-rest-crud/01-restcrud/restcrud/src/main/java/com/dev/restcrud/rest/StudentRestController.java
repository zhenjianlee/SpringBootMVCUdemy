package com.dev.restcrud.rest;

import com.dev.restcrud.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    List<Student> studentList = new ArrayList<>();
    @PostConstruct
    public void LoadData(){
        int size = 20;
        for (int i=0; i< size; i++){
            studentList.add(Student.generateRandomStudent());
        }
        System.out.println(studentList.toString());
    }
    @GetMapping("/students")
    public List<Student> getStudents(){
        System.out.println("Fetching studentList "+ studentList.toString());
        return studentList;
    }

    @GetMapping("student/{studentID}")
    public Student getStudent(@PathVariable int studentID){
        if (studentID>= studentList.size()|| studentID<0){
            throw new StudentNotFoundException("Student id not found -"+ studentID);
        }
        return studentList.get(studentID);
    }

    //catch specific student not found exception
    /*
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse>handleException(StudentNotFoundException exc){
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    */

    //catch all exceptions
    /*
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    */

}
