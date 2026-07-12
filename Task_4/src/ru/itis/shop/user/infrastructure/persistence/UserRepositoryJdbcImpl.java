package ru.itis.shop.user.infrastructure.persistence;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {

    private final Connection connection;

    public UserRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String profileDescription = resultSet.getString("profile_description");

                User user = new User(id, email, password, profileDescription);
                users.add(user);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        return users;
    }

    @Override
    public void save(User user) {
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void update(User userUpdate) {
    }
}
