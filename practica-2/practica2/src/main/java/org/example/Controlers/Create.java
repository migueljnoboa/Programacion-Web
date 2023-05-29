package org.example.Controlers;

import io.javalin.Javalin;
import org.example.Data.Article;
import org.example.Data.Data;
import org.example.Data.Tag;
import org.example.Data.User;

import java.util.List;
import java.util.Map;

public class Create extends BaseController{
    public Create(Javalin app) {
        super(app);
    }

    @Override
    public void applyPaths() {

        // Verificando que tenga una session iniciada
        app.before("/create", ctx -> {
            User user = ctx.sessionAttribute("user");
            if (user == null){
                ctx.redirect("/home");
            }
        });

        app.get("/create", ctx -> {

            // Thymeleaf
            Map<String,Object> map = getBasicMap(ctx);

            User authorName = ctx.sessionAttribute("user");
            map.put("authorName",authorName.getName());

            // Go to inicio.html
            ctx.render("public/html/create.html", map);
        });

        // POST create -> view
        app.post("/post-create-blog", ctx -> {

            String title = ctx.formParam("titlearticle");
            String body = ctx.formParam("bodyarticle");
            User author = ctx.sessionAttribute("user");
            String tagsRaw = ctx.formParam("tagarticle");
            String[] tagsStringList = tagsRaw.split(",");

            Article a = Data.getInstance().addArticle(title, body, author, tagsStringList);

            ctx.redirect("/view/" + a.getId());

        });
    }
}
