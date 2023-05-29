package org.example.Controlers;

import io.javalin.Javalin;
import org.example.Data.Article;
import org.example.Data.Data;

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

            // Creating map
            Map<String,Object> map = getBasicMap(ctx);

            // Searching for article
            String idStr = ctx.pathParam("articleId");
            long id = Long.parseLong(idStr);
            Article a = Data.getInstance().findArticle(id);

            // Adding article to map
            map.put("a", a);

            ctx.render("public/html/view.html", map);

        });
    }

}
