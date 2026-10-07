package com.MyToDoApp.MyToDoApp.controller;

import com.MyToDoApp.MyToDoApp.dto.ToDoRequest;
import com.MyToDoApp.MyToDoApp.dto.ToDoResponse;
import com.MyToDoApp.MyToDoApp.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService service;

    @PostMapping
    public ResponseEntity<ToDoResponse> createTodo(@RequestBody ToDoRequest request) {
        ToDoResponse response = service.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<ToDoResponse> updateToDo(@PathVariable Long id, @RequestBody ToDoRequest request) {
        ToDoResponse response = service.updateToDo(id, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("toggle/{id}")
    public ResponseEntity<ToDoResponse> toggleToDoById(@PathVariable Long id) {
        ToDoResponse response = service.toggleToDoById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ToDoResponse> getToDoById(@PathVariable Long id) {
        ToDoResponse response = service.getToDoByID(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ToDoResponse>> getAllToDos() {
        List<ToDoResponse> responses = service.getAllToDos();
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteToDoById(@PathVariable Long id) {
        service.deleteToDoById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllTodos() {
        service.deleteAllToDos();
        return ResponseEntity.noContent().build();
    }
}
