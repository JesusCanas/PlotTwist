package org.paloma.plottwist.dao;

import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.Pelicula;
import org.paloma.plottwist.model.Persona;
import org.paloma.plottwist.model.Metraje;

public class PeliculasDAO {

    /** Lista que almacena los datos en memoria. */
	private final List<Pelicula> peliculas;



    public PeliculasDAO() {
        // Constructor vacío
        peliculas = new ArrayList<>();
        
        // Crear datos de prueba
        Persona director1 = new Persona("Christopher", "Nolan", 53, "Británico", "Director", new ArrayList<>());
        peliculas.add(new Pelicula("Inception", Year.of(2010), List.of(Genero.CIENCIA_FICCION, Genero.THRILLER), director1, 8.8, List.of("507f1f77bcf86cd799439011", "507f1f77bcf86cd799439012", "507f1f77bcf86cd799439013"), 148));
        
        Persona director2 = new Persona("Quentin", "Tarantino", 60, "Americano", "Director", new ArrayList<>());
        peliculas.add(new Pelicula("Pulp Fiction", Year.of(1994), List.of(Genero.DRAMA, Genero.THRILLER), director2, 8.9, List.of("507f1f77bcf86cd799439014", "507f1f77bcf86cd799439015"), 154));
        
        Persona director3 = new Persona("Steven", "Spielberg", 76, "Americano", "Director", new ArrayList<>());
        peliculas.add(new Pelicula("Jurassic Park", Year.of(1993), List.of(Genero.AVENTURA, Genero.CIENCIA_FICCION), director3, 8.1, List.of("507f1f77bcf86cd799439016", "507f1f77bcf86cd799439017", "507f1f77bcf86cd799439018"), 127));
        
        Persona director4 = new Persona("Martin", "Scorsese", 81, "Americano", "Director", new ArrayList<>());
        peliculas.add(new Pelicula("The Wolf of Wall Street", Year.of(2013), List.of(Genero.DRAMA, Genero.COMEDIA), director4, 8.2, List.of("507f1f77bcf86cd799439019", "507f1f77bcf86cd799439020"), 180));
        
        Persona director5 = new Persona("Ridley", "Scott", 86, "Británico", "Director", new ArrayList<>());
        peliculas.add(new Pelicula("Gladiator", Year.of(2000), List.of(Genero.ACCION, Genero.DRAMA), director5, 8.5, List.of("507f1f77bcf86cd799439021", "507f1f77bcf86cd799439022", "507f1f77bcf86cd799439023"), 155));
        
        Persona director6 = new Persona("James", "Cameron", 69, "Canadiense", "Director", new ArrayList<>());
        peliculas.add(new Pelicula("Titanic", Year.of(1997), List.of(Genero.ROMANCE, Genero.DRAMA), director6, 7.8, List.of("507f1f77bcf86cd799439024", "507f1f77bcf86cd799439025"), 195));
        
        Persona director7 = new Persona("Alfred", "Hitchcock", 80, "Británico", "Director", new ArrayList<>()); // Fallecido, pero para prueba
        peliculas.add(new Pelicula("Psycho", Year.of(1960), List.of(Genero.THRILLER, Genero.HORROR), director7, 8.5, List.of("507f1f77bcf86cd799439026", "507f1f77bcf86cd799439027"), 109));
        
        Persona director8 = new Persona("Tim", "Burton", 65, "Americano", "Director", new ArrayList<>());
        peliculas.add(new Pelicula("The Nightmare Before Christmas", Year.of(1993), List.of(Genero.FANTASIA, Genero.COMEDIA), director8, 8.0, List.of("507f1f77bcf86cd799439028", "507f1f77bcf86cd799439029", "507f1f77bcf86cd799439030"), 76));
        
        Persona director9 = new Persona("Stanley", "Kubrick", 70, "Americano", "Director", new ArrayList<>()); // Fallecido
        peliculas.add(new Pelicula("2001: A Space Odyssey", Year.of(1968), List.of(Genero.CIENCIA_FICCION, Genero.AVENTURA), director9, 8.3, List.of("507f1f77bcf86cd799439031", "507f1f77bcf86cd799439032"), 149));
        
        Persona director10 = new Persona("Francis Ford", "Coppola", 84, "Americano", "Director", new ArrayList<>());
        peliculas.add(new Pelicula("The Godfather", Year.of(1972), List.of(Genero.DRAMA, Genero.THRILLER), director10, 9.2, List.of("507f1f77bcf86cd799439033", "507f1f77bcf86cd799439034", "507f1f77bcf86cd799439035"), 175));
    }

    public List<Pelicula> obtenerPeliculasTodas() {
		return peliculas;
	}

    public List<Pelicula> obtenerPeliculasFiltradas(String nombre, List<Genero> generos, Year anio, Double valoracion) {
        ArrayList<Pelicula> listaFiltrada = new ArrayList<>();
            for (Pelicula busqueda : peliculas) {
                if(!(nombre != null && busqueda.getTitulo().toLowerCase().contains(nombre.toLowerCase()))) {
                    continue;
                }
                if(generos == null) {
                    continue;
                } else {
                    boolean generoEncontrado = false;
                    for(int i = 0; i<busqueda.getGeneros().size(); i++) {
                        if(generos.get(i) == busqueda.getGeneros().get(i)) {
                            generoEncontrado = true;
                            break;
                        }
                    }
                    if(generoEncontrado == false) {
                        continue;
                    }
                }
                if(!(anio != null && busqueda.getAnyo().equals(anio))) {
                    continue;
                }

                if(!(valoracion != null && busqueda.getValoracion() >= valoracion.doubleValue()) ) {
                    continue;
                }

                listaFiltrada.add(busqueda);
            }
		return listaFiltrada;
	}

    public List<Pelicula> obtenerDestacados(int cantidad) {
        peliculas.sort(Comparator.comparingDouble(Pelicula::getValoracion).reversed());
        if (cantidad <= 0) {
            return new ArrayList<>();
        }
        int limite = Math.min(cantidad, peliculas.size());
        return new ArrayList<>(peliculas.subList(0, limite));
    }

    public Pelicula obtenerPeliculaPorId(String id) {
        for(Pelicula busqueda : peliculas) {
            if(busqueda.getId().equals(id)) {
                return busqueda;
            }
        }
        return null;
    }
}
