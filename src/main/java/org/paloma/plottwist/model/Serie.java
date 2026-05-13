package org.paloma.plottwist.model;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "serie")
public class Serie extends Metraje {

    private int numTemporadas;
    private int numEpisodios;
    private int duracionEpisodio;
    private Estado estado;

    public Serie() {
    }

    public Serie(String titulo, int anyo, List<Genero> generos, String sinopsis, String imagenURL, String idDirector, double valoracion, List<String> idsActores, int numeroTemporadas, int numEpisodios, int duracionEpisodio, Estado estado) {
        super(titulo, anyo, generos, sinopsis, imagenURL, idDirector, valoracion, idsActores);
        this.numTemporadas = numeroTemporadas;
        this.numEpisodios = numEpisodios;
        this.duracionEpisodio = duracionEpisodio;
        this.estado = estado;
    }

    // Getters and Setters
    public int getNumTemporadas() {
        return numTemporadas;
    }

    public void setNumTemporadas(int numeroTemporadas) {
        this.numTemporadas = numeroTemporadas;
    }

    public int getNumEpisodios() {
        return numEpisodios;
    }

    public void setNumEpisodios(int numEpisodios) {
        this.numEpisodios = numEpisodios;
    }

    public int getDuracionEpisodio() {
        return duracionEpisodio;
    }

    public void setDuracionEpisodio(int duracionEpisodio) {
        this.duracionEpisodio = duracionEpisodio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
