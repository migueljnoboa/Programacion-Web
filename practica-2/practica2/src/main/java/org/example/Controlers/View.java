package org.example.Controlers;

import io.javalin.Javalin;
import org.example.Data.Article;
import org.example.Data.Data;
import org.example.Data.User;

import java.util.Map;

public class View extends BaseController{
    public View(Javalin app) {
        super(app);

    }

    @Override
    public void applyPaths() {

        app.before("/view/{articleId}", ctx -> {

            String idStr = ctx.pathParam("articleId");
            long id = Long.parseLong(idStr);
            Article a = Data.getInstance().findArticle(id);
            if (a == null) {
                ctx.redirect("/home");
            }
        });

        app.get("/view/{articleId}", ctx -> {

            User user = ctx.sessionAttribute("user");

            // Creating map
            Map<String,Object> map = getBasicMap(ctx);

            // Searching for article
            String idStr = ctx.pathParam("articleId");
            long id = Long.parseLong(idStr);
            Article a = Data.getInstance().findArticle(id);

            // Delete Button
            String deletePath = "/delete/" + idStr;
            String deleteType = "hidden";

            // Write a Comment
            String comment = "d-none";


            if (user != null){
                comment = "";
                if (user.getAuthor() || user.getAdministrator()){
                    deleteType = "button";
                }
            }

            // Adding article to map
            map.put("a", a);
            // Delete Button
            map.put("deletePath", deletePath);
            map.put("deleteType", deleteType);
            map.put("comment", comment);

            // Render view.html with thymeleaf with the map
            ctx.render("public/html/view.html", map);

            ctx.cookie("articleId", idStr);

        });

        app.before("/delete/{articleId}", ctx -> {

            Data.getInstance().deleteArticle(ctx.pathParam("articleId"));
            ctx.redirect("/home");

        });

        app.before("/make-comment", ctx -> {

            String idStr = ctx.cookie("articleId");
            User user = ctx.sessionAttribute("user");
            if (user == null){
                ctx.redirect("/view/" + idStr);
            }

        });

        app.post("/make-comment", ctx -> {
            User user = ctx.sessionAttribute("user");
            String idStr = ctx.cookie("articleId");
            Long id = Long.parseLong(idStr);

            String comment = ctx.formParam("comment-Body");

            Data.getInstance().addComment(comment, user, Data.getInstance().findArticle(id));

            ctx.redirect("/view/" + idStr);
        });
    }

}
