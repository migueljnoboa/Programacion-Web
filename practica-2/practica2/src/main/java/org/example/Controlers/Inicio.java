package org.example.Controlers;

import io.javalin.Javalin;
import org.example.Data.Data;

public class Inicio extends BaseController{

    public Inicio(Javalin app) {
        super(app);
    }

    @Override
    public void applyPaths() {

        app.before("/", ctx -> {
            ctx.redirect("/html/inicio.html");
        });
    }
}
