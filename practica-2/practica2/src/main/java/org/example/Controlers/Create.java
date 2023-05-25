package org.example.Controlers;

import io.javalin.Javalin;
import org.example.Data.User;

public class Create extends BaseController{
    public Create(Javalin app) {
        super(app);
    }

    @Override
    public void applyPaths() {

        // Verificando que tenga una session iniciada
        app.before("/create.html", ctx -> {
            User user = ctx.sessionAttribute("user");
            if (user == null){
                ctx.redirect("log-in.html");
            }
        });


    }
}
