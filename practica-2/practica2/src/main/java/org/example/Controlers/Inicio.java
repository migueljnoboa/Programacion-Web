package org.example.Controlers;

import io.javalin.Javalin;

public class Inicio extends BaseController{

    public Inicio(Javalin app) {
        super(app);
    }

    @Override
    public void applyPaths() {

        app.before("/", ctx -> {
            ctx.redirect("/inicio.html");
        });
    }
}
