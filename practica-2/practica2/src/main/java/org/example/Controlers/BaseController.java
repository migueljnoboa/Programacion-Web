package org.example.Controlers;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.example.Data.Data;
import org.example.Data.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class BaseController {
    protected Javalin app;


    public BaseController(Javalin app) {
        this.app = app;
    }

    abstract public void applyPaths();

    public Map<String,Object> getBasicMap (Context ctx){
        // Thymeleaf
        Map<String, Object> map = new HashMap<>();

        // Login and Sign Up buttons
        String loginText, signUpText, signUpPath;
        User user = ctx.sessionAttribute("user");
        if (user == null){
            loginText = "Login";
            signUpText = "Sign Up";
            signUpPath = "/signUp";
        }else{
            loginText = user.getName();
            signUpText = "Log Out";
            signUpPath = "/invalidate";
        }
        map.put("loginText", loginText);
        map.put("signUpText", signUpText);
        map.put("signUpPath", signUpPath);

        return map;
    }

}
