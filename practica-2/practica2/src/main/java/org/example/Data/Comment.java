package org.example.Data;

import java.awt.*;

public class Comment {

    private long id;
    private String comment;
    private User author;
    private Article article;

    public Comment(long id, String comment, User author, Article article){
        super();
        this.id = id;
        this.comment = comment;
        this.author = author;
        this.article = article;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
