package ru.itis.shop.user.api;

import ru.itis.shop.user.api.dto.UserDto;
import ru.itis.shop.user.application.UserService;

import java.util.List;
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
            case "3":
                findById();
                break;
            case "4":
                updateDescription();
                break;
            case "5":
                showAll();
                break;
            case "6":
                showByDescription();
                break;
            case "7": {
                String email = scanner.nextLine();
                UserDto user = userService.getUserByEmail(email);
                System.out.println(user.getId() + " " + user.getProfileDescription() + " ");
            }

            case "0": {
                System.exit(0);
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить описание пользователя по почте");
        System.out.println("5. Получить информацию обо всех пользователях");
        System.out.println("6. Показать информацию о пользователях с заданным описанием профиля");
        System.out.println("7. Показать информацию о пользователя по email");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регистрировать пользователя");
        System.out.println("Введите name:");
        String name = scanner.nextLine();
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.signUp(name, email, password, profileDescription);
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

    private void findById() {
        System.out.println("Введите ID:");
        try {
            Integer id = Integer.parseInt(scanner.nextLine());
            UserDto user = userService.getUserById(id);
            System.out.println("Найден: Email = " + user.getEmail() + " | Описание = " + user.getProfileDescription());
        } catch (NumberFormatException e) {
            System.out.println("Вводить нужно число!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateDescription() {
        System.out.println("Введите почту пользователя:");
        String email = scanner.nextLine();
        System.out.println("Че ему накалякать?:");
        String newDesc = scanner.nextLine();

        userService.updateDescription(email, newDesc);
        System.out.println("сделано хозяин!");
    }

    private void showAll() {
        List<UserDto> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("пусто тут");
        } else {
            for (UserDto user : users) {
                System.out.println("ID: " + user.getId() + " | Email: " + user.getEmail() + " | Описание: " + user.getProfileDescription());
            }
        }
    }

    private void showByDescription() {
        System.out.println("по каким приметам пробивать(введите профиль):");
        String desc = scanner.nextLine();
        List<UserDto> users = userService.getUsersByDescription(desc);

        if (users.isEmpty()) {
            System.out.println("нет такого");
        } else {
            System.out.println("Вот ваши ахламоны:");
            for (UserDto user : users) {
                System.out.println("ID: " + user.getId() + " | Email: " + user.getEmail());
            }
        }
    }

}
