package org.paloma.plottwist.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public abstract class Metraje {
    @Id
    private String id;
    private String titulo;
    private Year anyo;
    private List<Genero> generos;
    private ObjectId idDirector;
    private Persona director;
    private double valoracion;
    private List<ObjectId> idsActores;
    private List<Persona> actores;

    public Metraje(String titulo, Year anyo, List<Genero> generos, ObjectId idDirector,
            double valoracion, List<ObjectId> idsActores) {
        this.titulo = titulo;
        this.anyo = anyo;
        this.generos = generos;
        this.idDirector = idDirector;
        this.director = null;
        this.valoracion = valoracion;
        this.idsActores = idsActores;
        this.actores = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ObjectId getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(ObjectId idDirector) {
        this.idDirector = idDirector;
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

    public List<ObjectId> getIdsActores() {
        return idsActores;
    }

    public void setIdsActores(List<ObjectId> idsActores) {
        this.idsActores = idsActores;
    }

    public List<Persona> getActores() {
        return actores;
    }

    public void setActores(List<Persona> actores) {
        this.actores = actores;
    }

}
