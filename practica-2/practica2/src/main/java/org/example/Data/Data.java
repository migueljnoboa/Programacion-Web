package org.example.Data;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private static Data instance;
    private List<User> users = new ArrayList<>();;
    private List<Article> articles = new ArrayList<>();;
    private List<Tag> tags = new ArrayList<>();;
    private List<Comment> comments = new ArrayList<>();;
    private static Data data = null;

    public static Data getInstance(){
        if (data == null){
            data = new Data();
        }
        return data;
    }

    // Creating new User
    public User addUser(String username, String password, String name){
        User newUser = new User(username, password, name, Boolean.FALSE, Boolean.FALSE);
        users.add(newUser);
        return newUser;
    }

    public User findUser(String username, String password) {

        for (User u : users){
            if (u.getUsername().equals(username) && u.getPassword().equals(password)){
                return u;
            }
        }
        return null;
    }
}
