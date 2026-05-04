package org.paloma.plottwist.model;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "peliculas")
public class Pelicula extends Metraje {

    private int duracion;

    public Pelicula(String titulo, LocalDate anyo, String genero, String creador, double valoracion, List<Persona> personas, int duracion) {
        super(titulo, anyo, genero, creador, valoracion, personas);
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
