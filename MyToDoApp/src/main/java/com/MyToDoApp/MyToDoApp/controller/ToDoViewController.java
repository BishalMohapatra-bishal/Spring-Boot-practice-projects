package com.MyToDoApp.MyToDoApp.controller;

import com.MyToDoApp.MyToDoApp.dto.ToDoRequest;
import com.MyToDoApp.MyToDoApp.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class ToDoViewController {

    private final ToDoService service;

    // View all ToDos
    @GetMapping
    public String index(Model model) {
        model.addAttribute("todos", service.getAllToDos());
        model.addAttribute("newTodo", new ToDoRequest(""));
        return "todos";
    }

    // Add a new ToDo
    @PostMapping("/add")
    public String addTodo(@ModelAttribute("newTodo") ToDoRequest request) {
        if (request.task() != null && !request.task().trim().isEmpty()) {
            service.createTask(request);
        }
        return "redirect:/todos";
    }

    // Toggle completed state
    @PostMapping("/toggle/{id}")
    public String toggleTodo(@PathVariable Long id) {
        service.toggleToDoById(id);
        return "redirect:/todos";
    }

    // Update task description
    @PostMapping("/update/{id}")
    public String updateTodo(@PathVariable Long id, @RequestParam("task") String task) {
        service.updateToDo(id, new ToDoRequest(task));
        return "redirect:/todos";
    }

    // Delete single item
    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        service.deleteToDoById(id);
        return "redirect:/todos";
    }

    // Clear all items
    @PostMapping("/delete-all")
    public String deleteAll() {
        service.deleteAllToDos();
        return "redirect:/todos";
    }
}