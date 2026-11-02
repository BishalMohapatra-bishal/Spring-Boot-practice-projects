package com.ErrorHandlingWithProblemDetails.ProblemDetails.service;

import com.ErrorHandlingWithProblemDetails.ProblemDetails.dto.EmployeeRequestDto;
import com.ErrorHandlingWithProblemDetails.ProblemDetails.dto.EmployeeResponseDto;
import com.ErrorHandlingWithProblemDetails.ProblemDetails.globalExceptionHandler.EmployeeNotFound;
import com.ErrorHandlingWithProblemDetails.ProblemDetails.model.Employee;
import com.ErrorHandlingWithProblemDetails.ProblemDetails.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeResponseDto createEmployee(EmployeeRequestDto requestDto) {
//        return entityToDto(repository.save(dtoToEntity(requestDto)));
        Employee emp = dtoToEntity(requestDto);
        Employee savedEmp = repository.save(emp);
        return entityToDto(savedEmp);
    }

    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto dto) {

//        first get the employee with provided id
        Employee existEmp = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFound("No employee found with this id : " + id));

        existEmp.setName(dto.name());
        existEmp.setDesignation(dto.designation());

        Employee updatedEmployee = repository.save(existEmp);
        return entityToDto(updatedEmployee);
    }

    public EmployeeResponseDto getEmployeeById(Long id) {

        Employee fetchEmployee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFound("No Employee Found with id : " + id));

//        Employee emp;
//        if (fetchEmployee.isPresent()) {
//            emp = fetchEmployee.get();
//        } else {
//            throw new EmployeeNotFound("No Employee Found with id : " + id);
//        }

        return entityToDto(fetchEmployee);
    }

    public List<EmployeeResponseDto> getAllEmployee() {
        List<Employee> employeeList = repository.findAll();

       return employeeList.stream().map(this::entityToDto).toList();
    }

    public EmployeeResponseDto getEmployeeByName(String name) {
        Employee fetchEmp = repository.findByName(name)
                .orElseThrow(() -> new EmployeeNotFound("No Employee found with name : " + name));
        return entityToDto(fetchEmp);
    }

    public void deleteEmployeeById(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllEmployee() {
        repository.deleteAll();
    }





    private Employee dtoToEntity(EmployeeRequestDto dto) {
        Employee e = new Employee();
        e.setName(dto.name());
        e.setDesignation(dto.designation());
        return e;
    }

    private EmployeeResponseDto entityToDto(Employee employee) {
        return new EmployeeResponseDto(employee.getId(),
                                       employee.getName(),
                                       employee.getDesignation());
    }
}
