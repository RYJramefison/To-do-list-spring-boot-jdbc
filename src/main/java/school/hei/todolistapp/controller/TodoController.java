package school.hei.todolistapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hei.todolistapp.entity.Status;
import school.hei.todolistapp.entity.Todo;
import school.hei.todolistapp.service.TodoService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@RestController
public class TodoController {
    private TodoService todoService;

    @GetMapping("/todos")
    public List<Todo> getALlTodo(){
        return todoService.getAllTodos();
    }

    @GetMapping("/todo/{id}")
    public Optional<Todo> getTodoById(@PathVariable int id){
        return  todoService.getTodoById(id);
    }

    @GetMapping("/search")
    public List<Todo> getTodoByStatus(@RequestParam @MatrixVariable Status status) {
        return todoService.getTodoByStatus(status);
    }

    @PostMapping("/todo")
    public ResponseEntity<Void> createTodo(@RequestBody Todo todo) {
        todoService.createTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
