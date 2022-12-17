package service.Impl;

import enums.Gender;
import model.Book;
import model.User;
import service.UserService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class UserServiceImpl implements UserService {
    List<User> users = new ArrayList<>();

    @Override
    public String createUser(List<User> users) {
        this.users.addAll(users);
        return "WAS SUCCESSFUL!!!";
    }

    @Override
    public List<User> findAllUsers() {
        return users;
    }

    @Override
    public User findUserById(Long id) {
        List<User> users1 = new ArrayList<>();
        for (User user : users) {
            if (id.equals(user.getId())) {
                users1.add(user);
                return (User) users1;
            }
        }
        return null;
    }

    @Override
    public String removeUserByName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                this.users.remove(user);
            }
        }
        return "WAS SUCCESSFUL!!!";
    }

    @Override
    public void updateUser(Long id) {
    }

    @Override
    public void groupUsersByGender() {
        List<User> females = new LinkedList<>();
        List<User> males = new LinkedList<>();
        for (User user : this.users) {
            if (user.getGender().equals(Gender.FEMALE)) {
                females.add(user);
            } else {
                males.add(user);
            }
        }
        System.out.println(females);
        System.out.println(males);
    }

    @Override
    public String buyBooks(String name, List<Book> books) {
        System.out.print("Write the book name that you want buy: ");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.nextLine();
        for (User user : this.users) {
            for (Book book : books) {
                if (user.getName().equals(name)) {
                    if (book.getName().equals(bookName)) {
                        user.getBooks().add(book);
                        user.setMoney(user.getMoney().subtract(book.getPrice()));
                    }
                }
            }
        }
        return null;
    }
}

