package org.example.Controlers;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.example.Data.Data;
import org.example.Data.User;
import org.example.Data.Article;
import org.example.Data.Tag;

import java.util.*;

public class Inicio extends BaseController{

    public Inicio(Javalin app) {
        super(app);
    }

    @Override
    public void applyPaths() {

        // Redirect to Home
        app.before("/", ctx -> ctx.redirect("/home"));

        // Go to Home
        app.get("home",ctx -> {

            // Thymeleaf
            Map<String,Object> map = getBasicMap(ctx);

            List<Article> articles = Data.getInstance().getArticles();
            Collections.reverse(articles);
            map.put("articles", articles);
            // Go to inicio.html
            ctx.render("public/html/inicio.html", map);
            Collections.reverse(articles);

        });
    }

}
