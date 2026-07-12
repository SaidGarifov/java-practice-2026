package ru.itis.shop.app;

import ru.itis.shop.user.api.UserConsoleOperations;
import ru.itis.shop.user.application.UserService;
import ru.itis.shop.user.infrastructure.persistence.UserRepositoryJdbcImpl;
import ru.itis.shop.user.repository.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/java_2026",
                "postgres", "2684play")) {

            UserRepository userRepositoryJdbc = new UserRepositoryJdbcImpl(connection);
            UserService userService = new UserService(userRepositoryJdbc);

            UserConsoleOperations operations = new UserConsoleOperations(userService);

            while (true) {
                operations.showMenu();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
