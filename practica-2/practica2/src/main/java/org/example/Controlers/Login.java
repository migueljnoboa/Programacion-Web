package org.example.Controlers;

import io.javalin.Javalin;

public class Login extends BaseController{


    public Login(Javalin app) {
        super(app);
    }

    @Override
    public void applyPaths() {

        app.before("/html5/login.html", ctx -> {

        });
    }
}
