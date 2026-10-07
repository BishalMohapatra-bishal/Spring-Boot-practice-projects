package com.MyToDoApp.MyToDoApp.service;

import com.MyToDoApp.MyToDoApp.dto.ToDoRequest;
import com.MyToDoApp.MyToDoApp.dto.ToDoResponse;
import com.MyToDoApp.MyToDoApp.model.ToDo;
import com.MyToDoApp.MyToDoApp.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoResponse createTask(ToDoRequest toDoRequest) {
        ToDo toDo = new ToDo();
        toDo.setTask(toDoRequest.task());
        toDo.setCompleted(false);
        toDo.setCreatedAt(LocalDateTime.now());

        ToDo savedTodo = toDoRepository.save(toDo);

        return entityToDto(savedTodo);
    }

    public ToDoResponse updateToDo(Long id, ToDoRequest request) {
        ToDo todo = toDoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No ToDo found with id: " + id));

        todo.setTask(request.task());
        ToDo updatedToDo = toDoRepository.save(todo);

        return entityToDto(updatedToDo);
    }

    public ToDoResponse getToDoByID(Long id) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No todo found with id: " + id));

        return entityToDto(toDo);
    }

    public List<ToDoResponse> getAllToDos() {
        List<ToDo> todosList = toDoRepository.findAll();

       return todosList.stream().map(ToDoService::entityToDto).toList();
    }

    public ToDoResponse toggleToDoById(Long id) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No todo found with id: " + id));
        toDo.setCompleted(!toDo.getCompleted());
         ToDo savedTodo = toDoRepository.save(toDo);

         return entityToDto(savedTodo);
    }

    public void deleteToDoById(Long id) {
        toDoRepository.deleteById(id);
    }

    public void deleteAllToDos() {
        toDoRepository.deleteAll();
    }

    private static ToDoResponse entityToDto(ToDo todo) {
        return new ToDoResponse(
                todo.getId(),
                todo.getTask(),
                todo.getCompleted(),
                todo.getCreatedAt()
        );
    }
}
