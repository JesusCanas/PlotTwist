package org.paloma.plottwist.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.paloma.plottwist.model.Pelicula;

public class PeliculasDAO {

    /** Lista que almacena los datos en memoria. */
	private final List<Pelicula> peliculas;

    public PeliculasDAO() {
        // Constructor vacío
        peliculas = new ArrayList<>();

        // Agregar algunos datos de ejemplo
        Pelicula pelicula1 = new Pelicula("Inception", null, "Sci-Fi", "Christopher Nolan", 8.8, null, 148);

        Pelicula pelicula2 = new Pelicula("The Matrix", null, "Action", "The Wachowskis", 8.7, null, 136);
        peliculas.add(pelicula1);
    }

    public List<Pelicula> obtenerPeliculasTodas() {
		return peliculas;
	}

    public List<Pelicula> obtenerPeliculasFiltradas(String nombre, String genero, LocalDate anio, Double valoracion) {
		return peliculas;
	}

    
}
