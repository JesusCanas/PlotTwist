package org.paloma.plottwist.model;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

/**
 * Clase que representa una película.
 * Extiende la clase Metraje y añade el atributo de duración específico de películas.
 * Esta clase se mapea a la colección "pelicula" en la base de datos MongoDB.
 * 
 * @author MiguelSg77
 * @version 1.0
 */
@Document(collection = "pelicula")
public class Pelicula extends Metraje {

    private int duracion;

    /**
     * Constructor parametrizado de Pelicula.
     * 
     * @param titulo Título de la película
     * @param anyo Año de estreno de la película
     * @param generos Lista de géneros de la película
     * @param sinopsis Descripción de la película
     * @param imagenURL URL del póster de la película
     * @param idDirector ID del director de la película
     * @param valoracion Valoración de la película
     * @param idsActores Lista de IDs de los actores
     * @param duracion Duración en minutos de la película
     */
    public Pelicula(String titulo, int anyo, List<Genero> generos, String sinopsis, String imagenURL, String idDirector, double valoracion, List<String> idsActores, int duracion) {
        super(titulo, anyo, generos, sinopsis, imagenURL, idDirector, valoracion, idsActores);
        this.duracion = duracion;
    }

    /**
     * Constructor vacío de Pelicula.
     */
    public Pelicula() {
        
    }

    // Getters and Setters
    /**
     * Obtiene la duración de la película en minutos.
     * 
     * @return Duración en minutos
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Establece la duración de la película en minutos.
     * 
     * @param duracion Duración en minutos
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

}