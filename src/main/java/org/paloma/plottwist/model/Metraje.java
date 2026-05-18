package org.paloma.plottwist.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta que representa un metraje audiovisual (película o serie).
 * Esta clase contiene los atributos comunes a películas y series, como título,
 * año de estreno, géneros, sinopsis, director y elenco de actores.
 * 
 * La clase utiliza anotaciones de Spring Data MongoDB para mapear los atributos
 * a campos en la base de datos. Algunos atributos se marcan como @Transient
 * ya que se hidratan desde consultas a otras colecciones.
 * 
 * @author MiguelSg77
 * @version 1.0
 */
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
    @JsonIgnore
    private String idDirector;
    @Transient
    private Persona director;
    private double valoracion;
    @Field("idActores")
    @JsonIgnore
    private List<String> idsActores;
    @Transient
    private List<Persona> actores;

    /**
     * Constructor parametrizado de Metraje.
     * 
     * @param titulo Título del metraje
     * @param anyo Año de estreno del metraje
     * @param generos Lista de géneros del metraje
     * @param sinopsis Descripción del metraje
     * @param imagenURL URL de la imagen o póster
     * @param idDirector ID del director
     * @param valoracion Valoración numérica del metraje
     * @param idsActores Lista de IDs de los actores
     */
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

    /**
     * Constructor vacío de Metraje.
     */
    public Metraje() {
        
    }

    // Getters and Setters
    /**
     * Obtiene el identificador único del metraje.
     * 
     * @return ID del metraje
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador único del metraje.
     * 
     * @param id ID del metraje
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el título del metraje.
     * 
     * @return Título del metraje
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del metraje.
     * 
     * @param titulo Título del metraje
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el año de estreno del metraje.
     * 
     * @return Año de estreno
     */
    public int getAnyo() {
        return anyo;
    }

    /**
     * Establece el año de estreno del metraje.
     * 
     * @param anyo Año de estreno
     */
    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    /**
     * Obtiene la lista de géneros del metraje.
     * 
     * @return Lista de géneros
     */
    public List<Genero> getGeneros() {
        return generos;
    }

    /**
     * Establece la lista de géneros del metraje.
     * 
     * @param generos Lista de géneros
     */
    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    /**
     * Obtiene la sinopsis del metraje.
     * 
     * @return Sinopsis del metraje
     */
    public String getSinopsis() {
        return sinopsis;
    }

    /**
     * Establece la sinopsis del metraje.
     * 
     * @param sinopsis Sinopsis del metraje
     */
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    /**
     * Obtiene el ID del director.
     * 
     * @return ID del director
     */
    public String getIdDirector() {
        return idDirector;
    }

    /**
     * Establece el ID del director.
     * 
     * @param idDirector ID del director
     */
    public void setIdDirector(String idDirector) {
        this.idDirector = idDirector;
    }

    /**
     * Obtiene el objeto Persona del director.
     * 
     * @return Objeto Persona del director
     */
    public Persona getDirector() {
        return director;
    }

    /**
     * Establece el objeto Persona del director.
     * 
     * @param director Objeto Persona del director
     */
    public void setDirector(Persona director) {
        this.director = director;
    }

    /**
     * Obtiene la valoración del metraje.
     * 
     * @return Valoración del metraje
     */
    public double getValoracion() {
        return valoracion;
    }

    /**
     * Establece la valoración del metraje.
     * 
     * @param valoracion Valoración del metraje
     */
    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    /**
     * Obtiene la lista de IDs de los actores.
     * 
     * @return Lista de IDs de los actores
     */
    public List<String> getIdsActores() {
        return idsActores;
    }

    /**
     * Establece la lista de IDs de los actores.
     * 
     * @param idsActores Lista de IDs de los actores
     */
    public void setIdsActores(List<String> idsActores) {
        this.idsActores = idsActores;
    }

    /**
     * Obtiene la lista de objetos Persona de los actores.
     * 
     * @return Lista de actores
     */
    public List<Persona> getActores() {
        return actores;
    }

    /**
     * Establece la lista de objetos Persona de los actores.
     * 
     * @param actores Lista de actores
     */
    public void setActores(List<Persona> actores) {
        this.actores = actores;
    }

}
