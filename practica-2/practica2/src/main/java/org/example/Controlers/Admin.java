package org.example.Controlers;

import io.javalin.Javalin;
import org.example.Data.User;
import org.example.Data.Data;

import java.util.Map;

public class Admin extends BaseController{
    public Admin(Javalin app) {
        super(app);
    }

    @Override
    public void applyPaths() {

        app.before("/admin", ctx -> {
            User user = ctx.sessionAttribute("user");

            if (user == null){
                ctx.redirect("/home");
            } else if (!user.getAdministrator()) {
                ctx.redirect("/home");
            }
        });

        app.get("/admin", ctx -> {

            Map<String,Object> map = getBasicMap(ctx);

            map.put("users", Data.getInstance().getUsers());

            ctx.render("public/html/admin.html", map);
        });

        // Author Permissions
        app.before("/permissionAuthor/{userId}", ctx -> {

            User user = ctx.sessionAttribute("user");

            if (user == null){
                ctx.redirect("/home");
            } else if (!user.getAdministrator()) {
                ctx.redirect("/home");
            }
        });

        app.get("/permissionAuthor/{userId}", ctx -> {
            User user = Data.getInstance().findUser(ctx.pathParam("userId"));

            if (user == null){
                ctx.redirect("/home");
            }
            else if (user.getAuthor() == Boolean.TRUE){
                user.removeAuthor();
            }else{
                user.makeAuthor();
            }
            ctx.redirect("/admin");

        });

        // Permission Admin

        // Author Permissions
        app.before("/permissionAdmin/{userId}", ctx -> {

            User user = ctx.sessionAttribute("user");

            if (user == null){
                ctx.redirect("/home");
            } else if (!user.getAdministrator()) {
                ctx.redirect("/home");
            }
        });

        app.get("/permissionAdmin/{userId}", ctx -> {
            User user = Data.getInstance().findUser(ctx.pathParam("userId"));

            if (user == null){
                ctx.redirect("/home");
            }
            else if (user.getAdministrator() == Boolean.TRUE){
                user.removeAdmin();
            }else{
                user.makeAdmin();
            }
            ctx.redirect("/admin");
        });
    }
}
