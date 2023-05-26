package org.example.Data;

public class User {

    private String username;
    private String name;
    private String password;
    private Boolean administrator;
    private Boolean author;

    public user()

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator;
    }

    public Boolean getAuthor() {
        return author;
    }

    public void setAuthor(Boolean author) {
        this.author = author;
    }
}
