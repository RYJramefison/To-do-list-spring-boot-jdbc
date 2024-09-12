package school.hei.todolistapp.service;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Service;
import school.hei.todolistapp.entity.Status;
import school.hei.todolistapp.entity.Todo;
import school.hei.todolistapp.repository.TodoImplement;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode


@Service
public class TodoService {
    private TodoImplement todoDAO;

    public List<Todo> getAllTodos(){
        return todoDAO.findAll();
    }

    public Optional<Todo>  getTodoById(int id){
        return todoDAO.findById(id);
    }
    public List<Todo> getTodoByStatus(Status status){
        return todoDAO.getTodoByStatus(status);
    }

    public void createTodo(Todo todo) {
        todoDAO.save(todo);
    }



}
