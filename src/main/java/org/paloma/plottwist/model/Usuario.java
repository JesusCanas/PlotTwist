package org.paloma.plottwist.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Year;
import java.util.List;

@Document(collection = "usuarios")
public class Usuario {
    @Id
    private String id;
    private String nombre;
    private String contrasenya;
    private String correo;
    private Year fechaRegistro;
    private List<Metraje> listaMetrajes;

    public Usuario(String nombre, String contrasenya, String correo, Year fechaRegistro, List<Metraje> listaMetrajes) {
        this.nombre = nombre;
        this.contrasenya = contrasenya;
        this.correo = correo;
        this.fechaRegistro = fechaRegistro;
        this.listaMetrajes = listaMetrajes;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String email) {
        this.correo = email;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String password) {
        this.contrasenya = password;
    }

    public Year getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Year fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Metraje> getListaMetrajes() {
        return listaMetrajes;
    }

    public void setListaMetrajes(List<Metraje> listaMetrajes) {
        this.listaMetrajes = listaMetrajes;
    }

}
