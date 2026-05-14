package org.paloma.plottwist.model;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Clase que representa una serie de televisión.
 * Extiende la clase Metraje y añade atributos específicos de series como número de temporadas,
 * número de episodios, duración de cada episodio y estado de la serie.
 * Esta clase se mapea a la colección "serie" en la base de datos MongoDB.
 * 
 * @author MiguelSg77
 * @version 1.0
 */
@Document(collection = "serie")
public class Serie extends Metraje {

    private int numTemporadas;
    private int numEpisodios;
    private int duracionEpisodio;
    private Estado estado;

    /**
     * Constructor vacío de Serie.
     */
    public Serie() {
    }

    /**
     * Constructor parametrizado de Serie.
     * 
     * @param titulo Título de la serie
     * @param anyo Año de estreno de la serie
     * @param generos Lista de géneros de la serie
     * @param sinopsis Descripción de la serie
     * @param imagenURL URL del póster de la serie
     * @param idDirector ID del director de la serie
     * @param valoracion Valoración de la serie
     * @param idsActores Lista de IDs de los actores
     * @param numeroTemporadas Número de temporadas
     * @param numEpisodios Número total de episodios
     * @param duracionEpisodio Duración en minutos de cada episodio
     * @param estado Estado actual de la serie
     */
    public Serie(String titulo, int anyo, List<Genero> generos, String sinopsis, String imagenURL, String idDirector, double valoracion, List<String> idsActores, int numeroTemporadas, int numEpisodios, int duracionEpisodio, Estado estado) {
        super(titulo, anyo, generos, sinopsis, imagenURL, idDirector, valoracion, idsActores);
        this.numTemporadas = numeroTemporadas;
        this.numEpisodios = numEpisodios;
        this.duracionEpisodio = duracionEpisodio;
        this.estado = estado;
    }

    // Getters and Setters
    /**
     * Obtiene el número de temporadas de la serie.
     * 
     * @return Número de temporadas
     */
    public int getNumTemporadas() {
        return numTemporadas;
    }

    /**
     * Establece el número de temporadas de la serie.
     * 
     * @param numeroTemporadas Número de temporadas
     */
    public void setNumTemporadas(int numeroTemporadas) {
        this.numTemporadas = numeroTemporadas;
    }

    /**
     * Obtiene el número total de episodios de la serie.
     * 
     * @return Número de episodios
     */
    public int getNumEpisodios() {
        return numEpisodios;
    }

    /**
     * Establece el número total de episodios de la serie.
     * 
     * @param numEpisodios Número de episodios
     */
    public void setNumEpisodios(int numEpisodios) {
        this.numEpisodios = numEpisodios;
    }

    /**
     * Obtiene la duración en minutos de cada episodio.
     * 
     * @return Duración en minutos
     */
    public int getDuracionEpisodio() {
        return duracionEpisodio;
    }

    /**
     * Establece la duración en minutos de cada episodio.
     * 
     * @param duracionEpisodio Duración en minutos
     */
    public void setDuracionEpisodio(int duracionEpisodio) {
        this.duracionEpisodio = duracionEpisodio;
    }

    /**
     * Obtiene el estado actual de la serie.
     * 
     * @return Estado de la serie
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual de la serie.
     * 
     * @param estado Estado de la serie
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
