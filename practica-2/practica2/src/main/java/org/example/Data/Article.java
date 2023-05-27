package org.example.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Article {

    private long id;
    private String title;
    private String body;
    private User author;
    private Date date;
    private List<Comment> comments = new ArrayList<>();;
    private List<Tag> tags;

    public Article(long id, String title, String body, User author, Date date, List<Comment> comments, List<Tag> tags){
        super();
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
        this.date = date;
        this.comments = comments;
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
