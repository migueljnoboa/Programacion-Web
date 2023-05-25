package org.example.Controlers;

import io.javalin.Javalin;

public class Blog extends BaseController{
    public Blog(Javalin app) {
        super(app);
    }

    @Override
    public void applyPaths() {
        app.before("/blogTemplate.hmtl", ctx -> {

        });
    }
}
