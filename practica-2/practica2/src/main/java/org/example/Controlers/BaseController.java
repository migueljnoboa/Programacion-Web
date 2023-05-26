package org.example.Controlers;

import io.javalin.Javalin;
import org.example.Data.Data;

public abstract class BaseController {
    protected Javalin app;


    public BaseController(Javalin app) {
        this.app = app;
    }

    abstract public void applyPaths();

}
