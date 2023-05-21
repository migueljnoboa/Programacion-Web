package org.example.servicios;

import org.example.encapsulaciones.Usuario;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Ejemplo de servicio patron Singleton
 */
public class FakeServices {

    private static FakeServices instancia;
    private List<Usuario> listaUsuarios = new ArrayList<>();

    /**
     * Constructor privado.
     */
    private FakeServices(){


    }

    public static FakeServices getInstancia(){
        if(instancia==null){
            instancia = new FakeServices();
        }
        return instancia;
    }

    /**
     * Permite autenticar los usuarios. Lo ideal es sacar en
     * @param usuario
     * @param password
     * @return
     */
    public Usuario autheticarUsuario(String usuario, String password){
        //simulando la busqueda en la base de datos.
        return new Usuario(usuario, "Usuario "+usuario, password);
    }

    public List<Usuario> getListaUsuarios(){
        return listaUsuarios;
    }

}
