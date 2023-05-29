package org.example.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Data {

    private static Data instance;
    private List<User> users = new ArrayList<>();;
    private List<Article> articles = new ArrayList<>();;
    private List<Tag> tags = new ArrayList<>();;

    private long idArticle = 0;
    private long idTag = 0;
    private long idComment = 0;
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

    public List<Article> getArticles() {
        return articles;
    }

    public User findUser(String username, String password) {

        for (User u : users){
            if (u.getUsername().equals(username) && u.getPassword().equals(password)){
                return u;
            }
        }
        return null;
    }

    public Tag addTag(String tag){
        Tag newTag = new Tag(idTag+1, tag);
        this.idTag = idTag + 1;

        tags.add(newTag);
        return newTag;
    }

    public Tag findTag(String tag) {

        for (Tag t : tags){
            if (t.getTag().equalsIgnoreCase(tag)){
                return t;
            }
        }
        return null;
    }

    private List<Tag> addTagsToArticle(String[] tagsListString){


        // Obtener una lista de tag de la lista de Strings
        List<Tag> tagsRet = new ArrayList<>();

        for (String tagRaw : tagsListString){
            Tag t = findTag(tagRaw);
            if (t == null){
                t = addTag(tagRaw);
            }
            tagsRet.add(t);
        }

        return tagsRet;
    }

    public Article addArticle(String title, String body, User author, String[] tagsListString){

        List<Tag> tags = addTagsToArticle(tagsListString);

        Article newArticle = new Article(idArticle+1, title, body, author, new Date(), new ArrayList<>(), tags);
        this.idArticle = idArticle + 1;
        articles.add(newArticle);
        return newArticle;
    }

    public Article findArticle(long idArticle) {

        for (Article a : articles){
            if (a.getId() == idArticle){
                return a;
            }
        }
        return null;
    }

    public Comment addComment(String comment, User author, Article article) {
        Comment newComment = new Comment(idComment + 1, comment, author, article);
        this.idComment = idComment + 1;
        findArticle(article.getId()).getComments().add(newComment);
        return newComment;
    }
}
