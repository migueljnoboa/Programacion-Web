package org.example;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.example.Controlers.*;
import org.example.Data.Data;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        // Creating Javalin Object
        Javalin app = Javalin.create(config -> {

            // Configuracion de static files
            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/public";
                staticFileConfig.location = Location.CLASSPATH;
                staticFileConfig.precompress = false;
                staticFileConfig.aliasCheck = null;
            });
        });

        // Iniciando en el Puerto 7000
        app.start(7000);

        Data.getInstance();

        Data.getInstance().addUser("miguel", "12345", "miguel");

        System.out.println(Data.getInstance().findUser("miguel", "12345"));

        new Login(app).applyPaths();

        new Inicio(app).applyPaths();

        new Create(app).applyPaths();



    }
}