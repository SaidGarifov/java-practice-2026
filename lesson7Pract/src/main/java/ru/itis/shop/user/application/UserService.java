package ru.itis.shop.user.application;

import ru.itis.shop.user.api.dto.UserDto;
import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(RuntimeException::new);

        return new UserDto(user.getId(), user.getEmail(), user.getProfileDescription());
    }

    public void signUp(String name, String email, String password, String profileDescription) {
        User user = new User(name, email, password, profileDescription);
        userRepository.save(user);
    }

    public boolean signIn(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get().getPassword().equals(password);
        } else return false;
    }

    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return new UserDto(user.getId(), user.getEmail(), user.getProfileDescription());
    }

    public void updateDescription(String email, String newDescription) {
        userRepository.updateProfileDescriptionByEmail(email, newDescription);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> dtoList = new ArrayList<>();
        for (User user : users) {
            dtoList.add(new UserDto(user.getId(), user.getEmail(), user.getProfileDescription()));
        }
        return dtoList;
    }

    public List<UserDto> getUsersByDescription(String description) {
        List<User> users = userRepository.findAllByProfileDescription(description);
        List<UserDto> dtoList = new ArrayList<>();
        for (User user : users) {
            dtoList.add(new UserDto(user.getId(), user.getEmail(), user.getProfileDescription()));
        }
        return dtoList;
    }
}
