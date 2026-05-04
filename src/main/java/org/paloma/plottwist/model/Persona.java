package org.paloma.plottwist.model;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "personas")
public class Persona {
    @Id
    private String id;
    private String nombre;
    private String apellido;
    private int edad;
    private String nacionalidad;
    private String rol;
    private List<Metraje> metrajes;

    public Persona(String nombre, String apellido, int edad, String nacionalidad, String rol, List<Metraje> metrajes) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.rol = rol;
        this.metrajes = metrajes;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<Metraje> getMetrajes() {
        return metrajes;
    }

    public void setMetrajes(List<Metraje> metrajes) {
        this.metrajes = metrajes;
    }

}
