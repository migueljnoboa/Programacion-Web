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

    EstudianteServices estudianteServices = EstudianteServices.getInstancia();
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

    public List<Estudiante> listarEstudiante() throws SQLException {

        return estudianteServices.findAll();
    }

    public Estudiante getEstudiantePorMatricula(int matricula) throws SQLException {

        return estudianteServices.find(matricula);
    }

    public Estudiante crearEstudiante(Estudiante estudiante) throws SQLException {

        if (estudianteServices.getEstudiante(estudiante.getMatricula()) != null){
            System.out.println("Estudiante registrado...");
            return null; //generar una excepcion...
        }
        estudianteServices.crear(estudiante);
        return estudiante;
    }

    public Estudiante actualizarEstudiante(Estudiante estudiante) throws SQLException {
        Estudiante tmp = estudianteServices.getEstudiante(estudiante.getMatricula());
        if(tmp == null){//no existe, no puede se actualizado
            throw new NoExisteEstudianteException("No Existe el estudiante: "+estudiante.getMatricula());
        }
        estudianteServices.editar(estudiante);
        return tmp;
    }

    public boolean eliminandoEstudiante(int matricula) throws SQLException {

        boolean tmp = estudianteServices.eliminar(matricula);
        return tmp;
    }

}
