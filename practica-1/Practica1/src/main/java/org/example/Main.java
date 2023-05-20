package org.example;

import io.javalin.Javalin;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.*;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        // Tomando Input del Usuario (URL)
        System.out.printf("\nLink URL: ");
        Scanner sc = new Scanner(System.in);
        String URL = sc.nextLine();
        //String URL = "https://upload.wikimedia.org/wikipedia/commons/9/9e/Ours_brun_parcanimalierpyrenees_1.jpg";

        // Creando el cliente
        HttpClient client = HttpClient.newHttpClient();

        // Creando el request usand el URL del input.
        HttpRequest request;

        // Creando respuesta antes del try catch
        HttpResponse respuesta;

        try {
            // Mandando y reciviendo la respuesta del Servidor
            request = HttpRequest.newBuilder().uri(URI.create(URL)).build();
        }catch (Exception e){
            //System.out.println(e);
            System.out.println("\nNo se encontro URL valido. \nRecordar poner en formato: http://... o https://... e intentar otra vez");
            return;
        }

        try {
            // Mandando y reciviendo la respuesta del Servidor
            respuesta = client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch (Exception e){
            //System.out.println(e);
            System.out.println("\nNo se ha podido conectar y o recibir una respuesta del servidor.");
            System.out.println("Revise el URL e intente otra vez.");
            return;
        }

        // Obtener tipo de Recurso
        String recurso = respuesta.headers().firstValue("Content-Type").orElse("unknown").split("/")[1].split(";")[0].toString();

        System.out.println("\nTipo de Recurso Recivido: " + recurso + "\n");

        // Revisando si el html (si no es de tipo html termina)
        if (!recurso.equalsIgnoreCase("html")){
            return;
        }

        // Conectando con Jsoup
        Document doc;
        try {
            doc = Jsoup.connect(URL).get();

        }catch (Exception e){
            System.out.println(e);
            System.out.println("Jsoup ha fallado");
            return;
        }

        // Obteniendo el numero de lineas
        String fullHtml = doc.outerHtml().toString();
        String[] strList = fullHtml.split("\n");
        Integer cantLineas = strList.length;

        // Obteniendo la cantidad de parrafos
        Elements paragraphs = doc.select("p");
        Integer cantParagraphs = paragraphs.size();

        // Obteniendo la cantidad de imagenes en los parrafos
        Integer cantImg = 0;
        for(Element p : paragraphs){
            cantImg += p.select("img").size();
        }

        // Obteniendo la cantidad de forms en el documento con Get
        List<FormElement> forms = doc.forms();
        Integer cantForm = forms.size();

        // Obteniendo la cantidad de forms en el documento con post
        List<FormElement> formsPost = doc.select("form[method=post]").forms();
        Integer cantFormPost = formsPost.size();

        // Obteniendo la cantidad de forms en el documento con Get
        List<FormElement> formsGet = doc.select("form[method=get]").forms();
        Integer cantFormGet = formsGet.size();



        System.out.println("Informacion del Documento:");
        System.out.println("# de Lineas: " + cantLineas);
        System.out.println("# de Parrafos: " + cantParagraphs);
        System.out.println("# de Imagenes en Parrafos: " + cantImg);
        System.out.println("# de Forms: " + cantForm);
        System.out.println("# de Forms con POST: " + cantFormPost);
        System.out.println("# de Forms con GET: " + cantFormGet);

        // Obteniendo la informcion del input de los formularios


        Integer count = 1;
        System.out.println("\nFormularios:\n");
        for(FormElement f : forms){
            System.out.println("Formulario " + count);
            Elements inputs = f.select("input");
            for(Element i : inputs){
                System.out.println("    input -> type: " + i.attr("type"));
            }
            System.out.println();
            count++;
        }

        // Mandando forms
        Integer cantidad = 1;

        for (FormElement f : formsPost){
            System.out.println("Formulario " + cantidad + ":");

            String act = f.attr("action").toString();

            var testAction = act.toString().split("/");
            if (!testAction[0].contains("http")){
                var strs = URL.toString().split("/");
                act = (strs[0]+strs[1] + "//" + strs[2] + act);
            }

            //System.out.println(act);

            // Try catch para mandar los formularios.
            try{
                //Haciendo el Request a la direccion del action.
                Connection.Response res =
                        Jsoup.connect(act).method(Connection.Method.POST).
                        header("Date", "Sun").data("asignatura", "practica1").execute();

                System.out.println(res.statusCode());
                System.out.println(res.body());
                System.out.println();
            }catch (Exception e){
                System.out.println("No se pudo mandar el form");
            }


            //System.out.println(act + "\n");
            //System.out.println(f);
        }

        //System.out.println(doc);
        //doc.select("form[method=post]").forms().get(0).submit().execute()
    }
}