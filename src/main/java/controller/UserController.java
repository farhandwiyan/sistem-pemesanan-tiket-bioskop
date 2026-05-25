package controller;

import java.util.*;

import model.User;
import repository.UserRepository;

public class UserController implements Controller<User>{
    private UserRepository userRepo;
    private static User loggedInUser = null;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override 
    public void handleSave(User user) throws Exception {
        userRepo.save(user);
    }

    public boolean login(String username, String password) throws Exception {
        User user = userRepo.find(username, password);

        if (user != null) {
            loggedInUser = user;
            return true;
        } else {
            return false;
        }
    }

    public void logout() {
        loggedInUser = null;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public void handleDeleteUser(String username, String password) throws IllegalArgumentException, Exception {
        User user = userRepo.find(username, password);

        if (user != null) {
            userRepo.deleteUser(username, password);
        } else {
            throw new IllegalArgumentException("User tidak ditemukan");
        }
    }

    @Override
    public List<User> getAll() throws Exception {
        return userRepo.findAll();
    }
}
