package ru.itis.shop.user.api;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UserConsoleOperations {

    private final UserRepository userRepository;
    private final Scanner scanner;

    public UserConsoleOperations(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("0. Выход");

        String command = scanner.nextLine();

        switch (command) {
            case "1": {
                System.out.println("Сейчас будем регистрировать пользователя");
                System.out.println("Введите email:");
                String email = scanner.nextLine();
                System.out.println("Введите password:");
                String password = scanner.nextLine();
                System.out.println("Введите описание профиля:");
                String profileDescription = scanner.nextLine();
                User user = new User(email, password, profileDescription);
                userRepository.save(user);
            }
            break;
            case "2": {
                System.out.println("Вы можете войти в приложение");
            }
            case "3":{
                System.out.println("Введите id кента:");
                String id = scanner.nextLine();
                try{
                    BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\uchob\\Downloads\\Telegram Desktop\\Lesson_1\\Lesson_1\\Task_1\\users.txt"));
                    String line;
                    boolean found = false;
                    while ((line = br.readLine())!= null){
                        if(line.contains(id)){
                            String[] parts = line.split("\\|");
                            System.out.println(parts[1]);
                            found = true;
                        }
                    }
                    if(!found){
                        System.out.println("кент не найден");
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            break;
            case "0": {
                System.exit(0);
            }
        }
    }
}
