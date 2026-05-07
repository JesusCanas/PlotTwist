package org.paloma.plottwist.model;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Year;
import java.util.List;

@Document(collection = "series")
public class Serie extends Metraje {

    private int numeroTemporadas;
    private int numEpisodios;
    private int duracionEpisodio;
    private Estado estado;


    public Serie(String titulo, Year anyo, List<Genero> generos, ObjectId idDirector, double valoracion, List<ObjectId> idsActores, int numeroTemporadas, int numEpisodios, int duracionEpisodio, Estado estado) {
        super(titulo, anyo, generos, idDirector, valoracion, idsActores);
        this.numeroTemporadas = numeroTemporadas;
        this.numEpisodios = numEpisodios;
        this.duracionEpisodio = duracionEpisodio;
        this.estado = estado;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
