package org.example.Controlers;

import io.javalin.Javalin;
import org.example.Data.Data;
import org.example.Data.User;

// Lo que tiene que ver con el Login y Sign-Up
public class Login extends BaseController{


    public Login(Javalin app) {
        super(app);
    }

    @Override
    public void applyPaths() {

        // Cuando viene a la pagina, si ya estaba loged-in, lo hara automaticamente
        app.before("/", ctx -> {

        });

        // Log-In (viene de log-in.html)
        app.post("/log-in-filter", ctx -> {

            // Obtain parameters from log-in.html
            String username = ctx.formParam("username");
            String password = ctx.formParam("password");

            // Find user
            User user = Data.getInstance().findUser(username, password);

            if (user == null){
                // On fail log in
                ctx.redirect("/html/log-in.html");
            }
            else{
                // Add user to session
                ctx.sessionAttribute("user", user);

                // Redirect al inicio o implementar cookie para el ultimo path
                ctx.redirect("/html/inicio.html");
            }
        });

        // Sign-Up (viene de sing-up.html)
        app.post("/sign-up-filter", ctx -> {

            // Todo: Crear usuario
            String username = ctx.formParam("username");
            String password = ctx.formParam("password");
            String name = ctx.formParam("name");
            User user = Data.getInstance().addUser(username, password, name);

            // Todo: Hacer la session
            ctx.sessionAttribute("user", user);

            // Redirect al inicio o implementar cookie para el ultimo path
            ctx.redirect("/html/inicio.html");

        });

        // Log Out
        app.before("/invalidate", ctx -> {

            // Invalidate el session
            ctx.req().getSession().invalidate();

            // Redirect al inicio o implementar cookie para el ultimo path
            ctx.redirect("/html/inicio.html");
        });
    }
}
