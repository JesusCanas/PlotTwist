package org.paloma.plottwist.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public abstract class Metraje {
    @Id
    private String id;
    private String titulo;
    private int anyo;
    @Field("genero")
    private List<Genero> generos;
    private String sinopsis;
    @Field("imagen")
    private String imagenURL;
    @JsonInclude
    private String idDirector;
    @Transient
    private Persona director;
    private double valoracion;
    @Field("idActores")
    @JsonIgnore
    private List<String> idsActores;
    @Transient
    private List<Persona> actores;

    public Metraje(String titulo, int anyo, List<Genero> generos, String sinopsis, String imagenURL, String idDirector,
            double valoracion, List<String> idsActores) {
        this.titulo = titulo;
        this.anyo = anyo;
        this.generos = generos;
        this.sinopsis = sinopsis;
        this.imagenURL = imagenURL;
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

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(String idDirector) {
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

    public List<String> getIdsActores() {
        return idsActores;
    }

    public void setIdsActores(List<String> idsActores) {
        this.idsActores = idsActores;
    }

    public List<Persona> getActores() {
        return actores;
    }

    public void setActores(List<Persona> actores) {
        this.actores = actores;
    }

}
