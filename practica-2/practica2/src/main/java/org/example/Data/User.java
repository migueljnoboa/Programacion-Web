package org.example.Data;

public class User {

    private String username;
    private String name;
    private String password;
    private Boolean administrator;
    private Boolean author;

    public User(String username, String password, String name, boolean administrator, Boolean author) {
        super();
        this.username = username;
        this.password = password;
        this.name = name;
        this.author = author;
        this.administrator = administrator;
    }

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

    public User makeAuthor(){
        this.author = Boolean.TRUE;
        return this;
    }

    public User makeAdmin(){
        this.author = Boolean.TRUE;
        this.administrator = Boolean.TRUE;
        return this;
    }

    public User removeAuthor(){
        this.author = Boolean.FALSE;
        this.administrator = Boolean.FALSE;
        return this;
    }

    public User removeAdmin(){
        this.administrator = Boolean.FALSE;
        return this;
    }
}
