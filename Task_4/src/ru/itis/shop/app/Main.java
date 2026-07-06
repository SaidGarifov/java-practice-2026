package ru.itis.shop.app;

import ru.itis.shop.user.api.UserConsoleOperations;
import ru.itis.shop.user.application.UserService;
import ru.itis.shop.user.infrastructure.persistence.UserFileRepository;
import ru.itis.shop.user.infrastructure.persistence.UserMapper;
import ru.itis.shop.user.infrastructure.persistence.UserRepositoryJdbcImpl;
import ru.itis.shop.user.repository.UserRepository;

import java.sql.*;

public class Main {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/java_2026";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "2684play";
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            UserRepository userRepositoryJdbc = new UserRepositoryJdbcImpl(connection);

            UserService userService = new UserService(userRepositoryJdbc);

            UserConsoleOperations operations = new UserConsoleOperations(userService);

            while (true) {
                operations.showMenu();
            }
        }

    }
}
