package org.paloma.plottwist.model;

import org.springframework.data.annotation.Id;
import java.time.LocalDate;
import java.util.List;

public abstract class Metraje {
    @Id
    private String id;
    private String titulo;
    private LocalDate anyo;
    private String genero;
    private String director;
    private double valoracion;
    private List<Persona> actores;

    public Metraje(String titulo, LocalDate anyo, String genero, String creador, double valoracion, List<Persona> personas) {
        this.titulo = titulo;
        this.anyo = anyo;
        this.genero = genero;
        this.director = creador;
        this.valoracion = valoracion;
        this.actores = personas;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getAnyo() {
        return anyo;
    }

    public void setAnyo(LocalDate anyo) {
        this.anyo = anyo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String creador) {
        this.director = creador;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public List<Persona> getActores() {
        return actores;
    }

    public void setActores(List<Persona> personas) {
        this.actores = personas;
    }

    

}
