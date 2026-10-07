package com.MyToDoApp.MyToDoApp.repository;

import com.MyToDoApp.MyToDoApp.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
//    Optional<ToDo> findByName(String name);
}
