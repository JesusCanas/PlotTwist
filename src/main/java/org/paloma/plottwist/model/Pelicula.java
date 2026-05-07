package org.paloma.plottwist.model;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Year;
import java.util.List;

@Document(collection = "pelicula")
public class Pelicula extends Metraje {

    private int duracion;

    public Pelicula(String titulo, Year anyo, List<Genero> generos, ObjectId idDirector, double valoracion, List<ObjectId> idsActores, int duracion) {
        super(titulo, anyo, generos, idDirector, valoracion, idsActores);
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