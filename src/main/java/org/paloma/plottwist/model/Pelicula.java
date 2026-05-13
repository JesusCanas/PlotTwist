package org.paloma.plottwist.model;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "pelicula")
public class Pelicula extends Metraje {

    private int duracion;

    public Pelicula(String titulo, int anyo, List<Genero> generos, String sinopsis, String imagenURL, String idDirector, double valoracion, List<String> idsActores, int duracion) {
        super(titulo, anyo, generos, sinopsis, imagenURL, idDirector, valoracion, idsActores);
        this.duracion = duracion;
    }

    public Pelicula() {
        
    }

    // Getters and Setters
    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

}