package org.paloma.plottwist.model;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Year;
import java.util.List;

enum estado {
        EMISION, FINALIZADA, CANCELADA
}

@Document(collection = "series")
public class Serie extends Metraje {

    private int numeroTemporadas;
    private int numEpisodios;
    private int duracionEpisodio;


    public Serie(String titulo, Year anyo, Genero genero, String creador, double valoracion, List<String> actoresId, int numeroTemporadas, int numEpisodios, int duracionEpisodio) {
        super(titulo, anyo, genero, creador, valoracion, actoresId);
        this.numeroTemporadas = numeroTemporadas;
        this.numEpisodios = numEpisodios;
        this.duracionEpisodio = duracionEpisodio;
    }

    // Getters and Setters
    public int getNumeroTemporadas() {
        return numeroTemporadas;
    }

    public void setNumeroTemporadas(int numeroTemporadas) {
        this.numeroTemporadas = numeroTemporadas;
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
    
    
}
