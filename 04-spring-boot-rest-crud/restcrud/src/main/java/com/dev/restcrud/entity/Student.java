package com.dev.restcrud.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student {

    public static int prevId =0;
    private int id;
    private String firstName;
    private String lastName;

    private String email;

    public Student(){
        prevId++;
    }
    public Student(String firstName, String lastName, String email) {
        this();
        this.id=prevId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static Student generateRandomStudent(){
        Random random = new Random();
        List<String> firstNames = new ArrayList<>(List.of("Adam","Amber","Carly","Kellie","Jorge" ,"Dominic ", "Margarito", "Demarcus", "Winford", "Paige"));
        List<String> lastNames = new ArrayList<>(List.of("Davis","Brandt", "Hamilton", "Benjamin", "Rocha", "Mcconnell", "Vega", "Herring", "Lee", "Chong"));
        List<String> domains = new ArrayList<>(List.of("gmail","hotmail","yahoo","outlook", "facebook"));
        String randFirst = firstNames.get(random.nextInt(0,10));
        String randLast = lastNames.get(random.nextInt(0,10));
        String email = randFirst+"."+randLast+"@"+domains.get(random.nextInt(0,5))+".com";
        return new Student(randFirst,randLast,email);

    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
