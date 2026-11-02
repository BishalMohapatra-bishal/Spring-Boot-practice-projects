package com.ErrorHandlingWithProblemDetails.ProblemDetails.repository;

import com.ErrorHandlingWithProblemDetails.ProblemDetails.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByName(String name);
}
