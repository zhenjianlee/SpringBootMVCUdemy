package com.dev.realtimeproject.exception;

public class EmployeesNotFoundException extends RuntimeException {

    public EmployeesNotFoundException() {
        super();
    }

    public EmployeesNotFoundException(String name) {
        super("Could not find the employees with the name "+name);
    }
}
