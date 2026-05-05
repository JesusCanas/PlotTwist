package org.paloma.plottwist.model;

import org.springframework.data.annotation.Id;
import java.time.Year;
import java.util.List;
import java.util.Comparator;

public abstract class Metraje {
    @Id
    private String id;
    private String titulo;
    private Year anyo;
    private List<Genero> generos;
    private Persona director;
    private double valoracion;
    private List<String> actoresId;
    
    public Metraje(String titulo, Year anyo, List<Genero> generos, Persona director, double valoracion, List<String> actores) {
        this.titulo = titulo;
        this.anyo = anyo;
        this.generos = generos;
        this.director = director;
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

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public Persona getDirector() {
        return director;
    }

    public void setDirector(Persona director) {
        this.director = director;
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

public static class OrdenPorValoracion implements Comparator<Metraje> {
    @Override
    public int compare(Metraje m1, Metraje m2) {
        return Double.compare(m2.getValoracion(), m1.getValoracion());
    }
}
