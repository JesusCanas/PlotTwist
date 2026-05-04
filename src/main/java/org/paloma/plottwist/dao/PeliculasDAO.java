package org.paloma.plottwist.dao;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import org.paloma.plottwist.model.Pelicula;

public class PeliculasDAO {

    /** Lista que almacena los datos en memoria. */
	private final List<Pelicula> peliculas;

    public PeliculasDAO() {
        // Constructor vacío
        peliculas = new ArrayList<>();
    }

    public List<Pelicula> obtenerPeliculasTodas() {
		return peliculas;
	}

    public List<Pelicula> obtenerPeliculasFiltradas(String nombre, String genero, Year anio, Double valoracion) {
		return peliculas;
	}

    
}
