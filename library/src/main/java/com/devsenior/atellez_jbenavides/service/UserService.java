package com.devsenior.atellez_jbenavides.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.devsenior.atellez_jbenavides.exception.NotFoundException;
import com.devsenior.atellez_jbenavides.model.User;

public class UserService {

    private List<User> users = new ArrayList<>();

    public void addUsers(String name, String email, String id) {
        users.add(new User(name, email, id));
    }

    public void addUsers(String name, String email, String id, LocalDate registerDate) {
        users.add(new User(name, email, id, registerDate));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(String id) throws NotFoundException {
        for (var user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        throw new NotFoundException("El usuario con ID " + id + " no se encuentra en la base de datos");
    }

    public void updateUserEmail(String id, String email) throws NotFoundException {

        var user = getUserById(id);
        user.setEmail(email);
    }

    public void updateUserName(String id, String name) throws NotFoundException {

        var user = getUserById(id);
        user.setName(name);
    }

    public void deleteUser(String id) throws NotFoundException {
        var user = getUserById(id);
        users.remove(user);
    }
}
