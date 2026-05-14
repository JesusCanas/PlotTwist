package org.paloma.plottwist.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

/**
 * Clase que representa un usuario de la aplicación.
 * Contiene información de autenticación (nombre, contraseña, correo)
 * y una lista de metrajes que el usuario ha añadido a su lista personal.
 * Esta clase se mapea a la colección "usuarios" en la base de datos MongoDB.
 * 
 * @author MiguelSg77
 * @version 1.0
 */
@Document(collection = "usuarios")
public class Usuario {
    
    @Id
    private String id;
    private String nombre;
    private String contrasenya;
    private String correo;
    private int fechaRegistro;
    private List<Metraje> listaMetrajes;

    /**
     * Constructor parametrizado de Usuario.
     * 
     * @param nombre Nombre de usuario
     * @param contrasenya Contraseña del usuario
     * @param correo Correo electrónico del usuario
     * @param fechaRegistro Fecha de registro (timestamp)
     * @param listaMetrajes Lista de metrajes en la lista personal
     */
    public Usuario(String nombre, String contrasenya, String correo, int fechaRegistro, List<Metraje> listaMetrajes) {
        this.nombre = nombre;
        this.contrasenya = contrasenya;
        this.correo = correo;
        this.fechaRegistro = fechaRegistro;
        this.listaMetrajes = listaMetrajes;
    }

    // Getters and Setters
    /**
     * Obtiene el identificador único del usuario.
     * 
     * @return ID del usuario
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el nombre de usuario.
     * 
     * @return Nombre de usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de usuario.
     * 
     * @param nombre Nombre de usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     * 
     * @return Correo electrónico
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del usuario.
     * 
     * @param email Correo electrónico
     */
    public void setCorreo(String email) {
        this.correo = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return Contraseña del usuario
     */
    public String getContrasenya() {
        return contrasenya;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param password Contraseña del usuario
     */
    public void setContrasenya(String password) {
        this.contrasenya = password;
    }

    /**
     * Obtiene la fecha de registro del usuario.
     * 
     * @return Fecha de registro (timestamp)
     */
    public int getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Establece la fecha de registro del usuario.
     * 
     * @param fechaRegistro Fecha de registro (timestamp)
     */
    public void setFechaRegistro(int fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Obtiene la lista de metrajes en la lista personal del usuario.
     * 
     * @return Lista de metrajes
     */
    public List<Metraje> getListaMetrajes() {
        return listaMetrajes;
    }

    /**
     * Establece la lista de metrajes en la lista personal del usuario.
     * 
     * @param listaMetrajes Lista de metrajes
     */
    public void setListaMetrajes(List<Metraje> listaMetrajes) {
        this.listaMetrajes = listaMetrajes;
    }

}
