package org.example.Data;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private static Data instance;
    private List<User> users = new ArrayList<>();;
    private List<Article> articles = new ArrayList<>();;
    private List<Tag> tags = new ArrayList<>();;
    private List<Comment> comments = new ArrayList<>();;

    public static Data getInstance(){
        if (instance == null){
            instance = new Data();
        }
        return instance;
    }

    public User findUser(String username, String password) {

        for (User u : users){
            if (u.getUsername() == username && u.getPassword() == password){
                return u;
            }
        }
        return null;
    }
}
