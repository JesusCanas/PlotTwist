package org.paloma.plottwist.model;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "personas")
public class Persona {
    @Id
    private String id;
    private String nombre;
    @Field("FechaNacimiento")
    private LocalDate fechaDeNacimiento;
    private String nacionalidad;
    @Field("imagen")
    private String imagenURL;
    private String apellido; 
    private String biografia;
    @Field("idMetrajes")
    private List<String> metrajesId;
    private List<Metraje> metrajes;

    public Persona(String nombre, String apellido, String biografia, LocalDate fechaDeNacimiento, String nacionalidad,
            List<String> metrajesId) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.biografia = biografia;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.nacionalidad = nacionalidad;
        this.metrajesId = metrajesId;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public List<String> getMetrajesId() {
        return metrajesId;
    }

    public void setMetrajesId(List<String> metrajesId) {
        this.metrajesId = metrajesId;
    }

    public List<Metraje> getMetrajes() {
        return metrajes;
    }

    public void setMetrajes(List<Metraje> metrajes) {
        this.metrajes = metrajes;
    }

}
