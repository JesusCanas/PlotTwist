package org.paloma.plottwist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.paloma.plottwist.model.*;

import java.time.Year;
import java.util.List;

public class PeliculaTest {
    
    @Test
    void getDuracion() {
        Pelicula pelicula = new Pelicula("Inception", Year.of(2010), Genero.CIENCIA_FICCION, "Christopher Nolan", 8.8, List.of("Leonardo DiCaprio", "Joseph Gordon-Levitt"), 148);
        assertEquals(148, pelicula.getDuracion());
    }

    @Test
    void setDuracion() {
        Pelicula pelicula = new Pelicula("Inception", Year.of(2010), Genero.CIENCIA_FICCION, "Christopher Nolan", 8.8, List.of("Leonardo DiCaprio", "Joseph Gordon-Levitt"), 148);
        pelicula.setDuracion(150);
        assertEquals(150, pelicula.getDuracion());
    }

}
