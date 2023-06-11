package org.example.servicios;

import org.example.encapsulaciones.Estudiante;
import org.example.encapsulaciones.Usuario;
import org.example.util.NoExisteEstudianteException;
import org.example.util.RolesApp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Ejemplo de servicio patron Singleton
 */
public class FakeServices {

    private static FakeServices instancia;

    EstudianteServices estudianteServices = new EstudianteServices();
    private List<Estudiante> listaEstudiante = new ArrayList<>();
    private List<Usuario> listaUsuarios = new ArrayList<>();

    /**
     * Constructor privado.
     */
    private FakeServices(){
        //añadiendo los estudiantes.
        listaEstudiante.add(new Estudiante(20011136, "Carlos Camacho", "ITT"));
        //anadiendo los usuarios.
        listaUsuarios.add(new Usuario("admin", "admin", "1234", Set.of(RolesApp.ROLE_ADMIN, RolesApp.CUALQUIERA, RolesApp.LOGUEADO)));
        listaUsuarios.add(new Usuario("logueado", "logueado", "logueado", Set.of(RolesApp.CUALQUIERA)));
        listaUsuarios.add(new Usuario("usuario", "usuario", "usuario", Set.of(RolesApp.ROLE_USUARIO)));

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

    public List<Estudiante> listarEstudiante() throws SQLException {


        return estudianteServices.listaEstudiantes();
    }

    public Estudiante getEstudiantePorMatricula(int matricula) throws SQLException {

        return estudianteServices.getEstudiante(matricula);
    }

    public Estudiante crearEstudiante(Estudiante estudiante) throws SQLException {

        if (estudianteServices.getEstudiante(estudiante.getMatricula()) != null){
            System.out.println("Estudiante registrado...");
            return null; //generar una excepcion...
        }
        estudianteServices.crearEstudiante(estudiante);
        return estudiante;
    }

    public Estudiante actualizarEstudiante(Estudiante estudiante) throws SQLException {
        Estudiante tmp = estudianteServices.getEstudiante(estudiante.getMatricula());
        if(tmp == null){//no existe, no puede se actualizado
            throw new NoExisteEstudianteException("No Existe el estudiante: "+estudiante.getMatricula());
        }
        estudianteServices.actualizarEstudiante(estudiante);
        return tmp;
    }

    public boolean eliminandoEstudiante(int matricula) throws SQLException {

        boolean tmp = estudianteServices.borrarEstudiante(matricula);
        return tmp;
    }

}
