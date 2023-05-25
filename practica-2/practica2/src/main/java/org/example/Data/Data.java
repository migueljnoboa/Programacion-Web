package org.example.Data;

import java.util.List;

public class Data {

    private static Data instance;
    private List<User> users;
    private List<Article> articles;
    private List<Tag> tags;
    private List<Comment> comments;

    public static Data getInstance(){
        if (instance == null){
            instance = new Data();
        }
        return instance;
    }
}
