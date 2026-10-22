package com.practice_spring_JDBC.spring_JDBC.repository;

import com.practice_spring_JDBC.spring_JDBC.model.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

//    Most commonly used methods are
//    Update() --> It's similar to executeUpdate() -> use for (CREATE/UPDATE/DELETE)
//    Query() --> It's similar to executeQuery() -> use for (SELECT OR READ) Operation .It can return multiple row(return one row as well)
//    queryForObject() --> It only return one row -> use for (getStudentById)

    private JdbcTemplate jdbcTemplate;

    //private StudentRowMapper studentRowMapper = new StudentRowMapper();

    private RowMapper<Student> rowMapper =
            new BeanPropertyRowMapper<>(Student.class);

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createStudent(Student student) {
        String sql = """
                         INSERT INTO students(name, email, age)
                         VALUES(?, ?, ?)
                         """;
        int rowAffected = jdbcTemplate.update(sql,
                student.getName(), student.getEmail(), student.getAge());

        if(rowAffected == 1) {
            System.out.println("Create Student successful");
        }
        else {
            System.out.println("Create Student failed");
        }
    }

    public void updateStudent(Student student, Long id) {
        String sql = """
                     UPDATE students
                     SET name = ?,
                         email = ?,
                         age = ?
                     WHERE id = ?
                     """;

        int rowAffected = jdbcTemplate.update(sql,
                student.getName(),
                student.getEmail(),
                student.getAge(),
                id);

        if(rowAffected == 1) {
            System.out.println("Update operation successful");
        }
        else {
            System.out.println("Updation failed");
        }
    }

    public void deleteStudent(Long id) {
        String sql = """
            DELETE from students WHERE id = ?
        """;

        int rowAffected = jdbcTemplate.update(sql, id);

        if(rowAffected == 1) {
            System.out.println("Delete operation successful");
        }
        else {
            System.out.println("Deletion failed");
        }
    }

    public Student getStudentById(Long id) {

        String sql = """
                SELECT id, name, email, age FROM students
                WHERE id = ?
                """;

        return jdbcTemplate.queryForObject(sql,
                rowMapper,
                id);
    }

    public List<Student> getStudent() {
        String sql = """
                SELECT id, name, email, age FROM students
                """;

        List<Student> students = jdbcTemplate.query(
                sql, rowMapper);

        return students;
    }
}
