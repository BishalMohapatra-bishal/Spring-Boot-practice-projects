package com.ErrorHandlingWithProblemDetails.ProblemDetails.controller;

import com.ErrorHandlingWithProblemDetails.ProblemDetails.dto.EmployeeRequestDto;
import com.ErrorHandlingWithProblemDetails.ProblemDetails.dto.EmployeeResponseDto;
import com.ErrorHandlingWithProblemDetails.ProblemDetails.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(@Valid @RequestBody EmployeeRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createEmployee(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@Valid @RequestBody EmployeeRequestDto dto,
                                                              @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateEmployee(id, dto));
    }

    @GetMapping("/byname")
    public ResponseEntity<EmployeeResponseDto> getEmployeeByName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getEmployeeByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getEmployeeById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAllResponse() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllEmployee());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
        service.deleteEmployeeById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllEmployee() {
        service.deleteAllEmployee();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
