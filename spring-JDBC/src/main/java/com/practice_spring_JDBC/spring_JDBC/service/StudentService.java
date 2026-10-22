package com.practice_spring_JDBC.spring_JDBC.service;


import com.practice_spring_JDBC.spring_JDBC.model.Student;
import com.practice_spring_JDBC.spring_JDBC.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student) {
        studentRepository.createStudent(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.getStudent();
    }

    public Student getStudentById(Long id) {
        return studentRepository.getStudentById(id);
    }

    public void updateStudent(Student student) {
        studentRepository.updateStudent(student, student.getId());
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteStudent(id);
    }
}
