package org.paloma.plottwist.model;

import org.springframework.data.annotation.Id;
import java.time.Year;
import java.util.List;

public abstract class Metraje {
    @Id
    private String id;
    private String titulo;
    private Year anyo;
    private Genero genero;
    private String director;
    private double valoracion;
    private List<String> actoresId;
    
    public Metraje(String titulo, Year anyo, Genero genero, String creador, double valoracion, List<String> actores) {
        this.titulo = titulo;
        this.anyo = anyo;
        this.genero = genero;
        this.director = creador;
        this.valoracion = valoracion;
        this.actoresId = actores;
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

    public Year getAnyo() {
        return anyo;
    }

    public void setAnyo(Year anyo) {
        this.anyo = anyo;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
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

    public List<String> getActoresId() {
        return actoresId;
    }

    public void setActoresId(List<String> actores) {
        this.actoresId = actores;
    }

    

}
