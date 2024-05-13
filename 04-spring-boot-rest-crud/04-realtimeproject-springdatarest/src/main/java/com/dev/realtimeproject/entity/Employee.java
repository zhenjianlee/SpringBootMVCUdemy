package com.dev.realtimeproject.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "employee")
public class Employee {

    public static Random random = new Random();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    public Employee(){

    }

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static Employee generateRandomEmployee(){
        List<String> firstNames = new ArrayList<>
                (List.of("Adam","Amber","Carly","Kellie","Jorge" ,"Dominic ", "Margarito", "Demarcus", "Winford", "Paige"));
        List<String> lastNames = new ArrayList<>
                (List.of("Davis","Brandt", "Hamilton", "Benjamin", "Rocha", "Mcconnell", "Vega", "Herring", "Lee", "Chong"));
        List<String> domains = new ArrayList<>
                (List.of("gmail","hotmail","yahoo","outlook", "facebook"));

        String randFirst = firstNames.get(random.nextInt(0,10));
        String randLast = lastNames.get(random.nextInt(0,10));
        String email = randFirst+"."+randLast+"@"+domains.get(random.nextInt(0,5))+".com";
        return new Employee(randFirst,randLast,email);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
