package org.example.Controlers;

import io.javalin.Javalin;
import org.example.Data.Data;
import org.example.Data.User;

import java.util.HashMap;
import java.util.Map;

// Lo que tiene que ver con el Login y Sign-Up
public class Login extends BaseController{


    public Login(Javalin app) {
        super(app);
    }

    @Override
    public void applyPaths() {

        // GET login
        app.get("/login", ctx -> {

            // Thymeleaf
            Map<String,Object> map = getBasicMap(ctx);

            // Go to inicio.html
            ctx.render("public/html/log-in.html", map);
        });

        // GET signUp
        app.get("/signUp", ctx -> {

            // Thymeleaf
            Map<String,Object> map = getBasicMap(ctx);

            // Go to inicio.html
            ctx.render("public/html/sign-up.html", map);
        });

        // POST login -> /home
        app.post("/log-in-filter", ctx -> {

            // Obtain parameters from log-in.html
            String username = ctx.formParam("username");
            String password = ctx.formParam("password");

            // Find user
            User user = Data.getInstance().findUser(username, password);

            if (user == null){
                // On fail log in
                ctx.redirect("/login");
            }
            else{
                // Add user to session
                ctx.sessionAttribute("user", user);

                // Redirect al inicio o implementar cookie para el ultimo path
                ctx.redirect("/home");

            }
        });

        // POST sign up -> /home
        app.post("/sign-up-filter", ctx -> {

            String username = ctx.formParam("username");
            String password = ctx.formParam("password");
            String name = ctx.formParam("name");
            User user = Data.getInstance().addUser(username, password, name);

            ctx.sessionAttribute("user", user);

            // Redirect al inicio o implementar cookie para el ultimo path
            ctx.redirect("/home");

        });

        // Log Out
        app.before("/invalidate", ctx -> {

            // Invalidate el session
            ctx.req().getSession().invalidate();

            // Redirect al inicio o implementar cookie para el ultimo path
            ctx.redirect("/home");
        });
    }
}
