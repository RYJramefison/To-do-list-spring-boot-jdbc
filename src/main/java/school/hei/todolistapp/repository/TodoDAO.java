package school.hei.todolistapp.repository;
import school.hei.todolistapp.entity.Todo;

import java.util.List;
import java.util.Optional;


public interface TodoDAO {
    void save(Todo toAdd);

    List<Todo> findAll();

    Optional<Todo> findById(int id);

    Todo update(int id, Todo toUpdate);

    void deleteById(int id);
}
