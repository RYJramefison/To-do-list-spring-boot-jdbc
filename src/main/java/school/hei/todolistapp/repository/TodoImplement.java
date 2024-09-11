package school.hei.todolistapp.repository;

import org.springframework.stereotype.Repository;
import school.hei.todolistapp.entity.Priority;
import school.hei.todolistapp.entity.Status;
import school.hei.todolistapp.entity.Todo;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Controller -> Service : gère la pagination  -> DAO / Repository -> Connection
@Repository
public class TodoImplement implements TodoDAO {
    DatabaseConnection connection = new DatabaseConnection();


    @Override
    public void save(Todo toAdd) {

    }


    @Override
    public List<Todo> findAll() {
        List<Todo> todos = new ArrayList<>();
        String sql = "SELECT * FROM todo";

        try (Statement stm = this.connection.getConnection().createStatement();
             ResultSet res = stm.executeQuery(sql)
        ){
            while (res.next()){
                todos.add(new Todo(
                        res.getInt("id"),
                        res.getString("title"),
                        res.getString("description"),
                        res.getObject("create_date", LocalDateTime.class),
                        res.getObject("deadline", LocalDateTime.class),
                        res.getObject("executionDate", LocalDateTime.class),
                        Priority.valueOf(res.getString("priority")),
                        Status.valueOf(res.getString("status"))
                ));
            }
        }

         catch (SQLException e) {
            e.printStackTrace();
        }

        return todos;
    }

    @Override
    public Optional<Todo> findById(int id) {
        String sql = "SELECT * FROM todo WHERE id = ?";
        try (PreparedStatement pstm = this.connection.getConnection().prepareStatement(sql);
        ){

            pstm.setInt(1, id);
            ResultSet res = pstm.executeQuery();

            if (res.next()) {
                return Optional.of(new Todo(
                        res.getInt("id"),
                        res.getString("title"),
                        res.getString("description"),
                        res.getObject("create_date", LocalDateTime.class),
                        res.getObject("deadline", LocalDateTime.class),
                        res.getObject("executionDate", LocalDateTime.class),
                        Priority.valueOf(res.getString("priority")),
                        Status.valueOf(res.getString("status"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public Todo update(int id, Todo toUpdate) {
        return null;
    }


    @Override
    public void deleteById(int id) {

    }
}
