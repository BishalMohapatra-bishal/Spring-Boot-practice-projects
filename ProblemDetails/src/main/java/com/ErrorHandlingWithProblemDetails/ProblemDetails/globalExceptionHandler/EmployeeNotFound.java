package com.ErrorHandlingWithProblemDetails.ProblemDetails.globalExceptionHandler;

public class EmployeeNotFound extends RuntimeException{

    public EmployeeNotFound(String message) {
        super(message);
    }
}
