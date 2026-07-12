package ru.itis.shop.user.api;

import ru.itis.shop.user.application.UserService;

import java.util.Scanner;

public class UserConsoleOperations {

    private final UserService userService;
    private final Scanner scanner;

    public UserConsoleOperations(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        printUserMenu();

        String command = scanner.nextLine();

        switch (command) {
            case "1": {
                signUp();
            }
            break;
            case "2": {
                signIn();
            }
            break;
            case "4": {
                updateProfile();
            }
            break;
            case "0": {
                System.exit(0);
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить данные пользователя");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регистрировать пользователя");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.signUp(email, password, profileDescription);
    }


    private void signIn() {
        System.out.println("Вы можете войти в приложение");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();

        if (userService.signIn(email, password)) {
            System.out.println("Вы вошли в приложение");
        } else {
            System.out.println("Email или пароль не верны");
        }
    }

    private void updateProfile() {
        System.out.println("Введите email пользователя, данные которого хотите обновить:");
        String email = scanner.nextLine();
        java.util.Optional<ru.itis.shop.user.domain.User> userOptional = userService.findByEmail(email);

        if (userOptional.isPresent()) {
            ru.itis.shop.user.domain.User user = userOptional.get();

            System.out.println("Это тот?  " + user.getProfileDescription());

            System.out.println("Че накалякать? ");
            String newDescription = scanner.nextLine();
            user.setProfileDescription(newDescription);

            userService.update(user);
        } else {
            System.out.println("Такого типочка нема");
        }
    }

}
