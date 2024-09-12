package school.hei.todolistapp.repository;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter

public class DatabaseConnection {
    private Connection connection = null;

    public DatabaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todo_list_spring_boot", "postgres", "dangerous");
//            System.out.println("bienvenue sur le bd todo² _list_spring_boot");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
