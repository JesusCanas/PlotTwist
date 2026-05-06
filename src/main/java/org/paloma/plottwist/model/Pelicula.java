package org.paloma.plottwist.model;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Year;
import java.util.List;

@Document(collection = "peliculas")
public class Pelicula extends Metraje {

    private int duracion;

    public Pelicula(String titulo, Year anyo, List<Genero> generos, Persona director, double valoracion, List<String> actoresId, int duracion) {
        super(titulo, anyo, generos, director, valoracion, actoresId);
        this.duracion = duracion;
    }

    // Getters and Setters
    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

}
