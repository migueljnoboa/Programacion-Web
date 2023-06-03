package org.example.Controlers;

import io.javalin.Javalin;
import org.example.Data.User;

import java.util.Map;

public class Admin extends BaseController{
    public Admin(Javalin app) {
        super(app);
    }

    @Override
    public void applyPaths() {

        app.before("/admin", ctx -> {
            User user = ctx.sessionAttribute("user");
            if (!user.getAdministrator()){
                ctx.redirect("/home");
            }
        });

        app.get("/admin", ctx -> {

            Map<String,Object> map = getBasicMap(ctx);

            ctx.render("public/html/admin.html", map);
        });
    }
}
