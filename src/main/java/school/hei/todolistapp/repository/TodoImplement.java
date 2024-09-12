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
        String sql = "INSERT INTO todo (title, description, create_date, deadline, executionDate, priority, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, toAdd.getTitle());
            ps.setString(2, toAdd.getDescription());
            ps.setObject(3, toAdd.getCreate_date(), java.sql.Types.TIMESTAMP);
            ps.setObject(4, toAdd.getDeadline(),  java.sql.Types.TIMESTAMP);
            ps.setObject(5, toAdd.getExecutionDate(),  java.sql.Types.TIMESTAMP);
            ps.setString(6, toAdd.getPriority().name());
            ps.setString(7, toAdd.getStatus().name());

            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    toAdd.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public List<Todo> getTodoByStatus(Status status) {
        List<Todo> todos = new ArrayList<>();
        String sql = "SELECT * FROM todo WHERE status = ?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status.name()); // Passez le statut comme chaîne de caractères

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    todos.add(new Todo(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getObject("create_date", LocalDateTime.class),
                            rs.getObject("deadline", LocalDateTime.class),
                            rs.getObject("executionDate", LocalDateTime.class),
                            Priority.valueOf(rs.getString("priority")),
                            Status.valueOf(rs.getString("status"))
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return todos;
    }

    @Override
    public void deleteById(int id) {

    }
}
