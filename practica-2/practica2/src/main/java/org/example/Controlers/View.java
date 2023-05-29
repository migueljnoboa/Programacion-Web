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

            String deletePath = "/delete/" + idStr;

            String deleteType = "hidden";

            if (user != null){
                if (user.getAuthor() || user.getAdministrator()){
                    deleteType = "button";
                }
            }

            // Adding article to map
            map.put("a", a);

            // Adding deletePath to map
            map.put("deletePath", deletePath);

            // Adding the visibility of the button to the path (deleteType)
            map.put("deleteType", deleteType);

            // Render view.html with thymeleaf with the map
            ctx.render("public/html/view.html", map);

            ctx.cookie("articleId", idStr);

        });

        app.before("/delete/{articleId}", ctx -> {

            Data.getInstance().deleteArticle(ctx.pathParam("articleId"));
            ctx.redirect("/home");

        });

        app.before("/make-comment", ctx -> {

            System.out.println(ctx.cookie("articleId"));

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
