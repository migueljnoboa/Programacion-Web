package org.example;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.example.encapsulaciones.Usuario;
import org.example.servicios.FakeServices;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Starting Javalin\n");

        // creando el objeto Javalin y poniendo las opciones de documentos estaticos
        var app = Javalin.create(config -> {

            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/publico";
                staticFileConfig.location = Location.CLASSPATH;
                staticFileConfig.precompress = false;
                staticFileConfig.aliasCheck = null;
            });
        });

        // Inicializando el servidor en el puerto 7070
        app.start(7070);

        // el / es donde comienzan
        app.get("/", ctx -> {

            // Revisamos si el usuario ya tiene una sesion en el servidor
            Integer contador = ctx.sessionAttribute("contador");

            // Si nunca a venido a la pagina, Se redirecciona a registrarse
            if(contador==null || contador == 0){
                contador = 0;
                ctx.sessionAttribute("contador", contador);

                ctx.redirect("/html/login.html");

            }else{
                // Ya tiene una session, por tanto se le enseña el mensaje
                ctx.result("Bienvenido!!!");
            }
        });

        app.post("/autenticar", ctx -> {

            // Obtener usuario
            String user = ctx.formParam("usuario");
            String password = ctx.formParam("password");

            // Creando usuario
            Usuario usuario = FakeServices.getInstancia().autheticarUsuario(user,password);

            // Creando Cookie
            ctx.cookie( user, password, 120);

            // Creando session
            ctx.req().getSession();
            Integer contador = ctx.sessionAttribute("contador");
            contador++;
            ctx.sessionAttribute("contador", contador);

            ctx.redirect("/");

        });
    }
}