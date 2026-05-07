package org.paloma.plottwist.dao;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.OrdenPorFecha;
import org.paloma.plottwist.model.OrdenPorValoracion;
import org.paloma.plottwist.model.Pelicula;
import org.paloma.plottwist.model.Persona;

public class PeliculasDAO {

    /** Lista que almacena los datos en memoria. */
    private final List<Pelicula> peliculas;

    public PeliculasDAO() {
        // Constructor vacío
        peliculas = new ArrayList<>();

        // Crear datos de prueba
        Persona director1 = new Persona("Christopher", "Nolan", "Director de cine británico conocido por guiones originales y tramas complejas.",
                LocalDate.of(1970, 7, 30), "Británico", new ArrayList<>());
        ObjectId idDirector1 = new ObjectId();
        List<ObjectId> idsActores1 = List.of("507f1f77bcf86cd799439011", "507f1f77bcf86cd799439012", "507f1f77bcf86cd799439013").stream().map(ObjectId::new).collect(Collectors.toList());
        Pelicula pelicula1 = new Pelicula("Inception", Year.of(2010), List.of(Genero.CIENCIA_FICCION, Genero.THRILLER),
                idDirector1, 8.8, idsActores1, 148);
        pelicula1.setDirector(director1);
        peliculas.add(pelicula1);

        Persona director2 = new Persona("Quentin", "Tarantino", "Director estadounidense con estilo violento y referencias pop.",
                LocalDate.of(1964, 3, 27), "Americano", new ArrayList<>());
        ObjectId idDirector2 = new ObjectId();
        List<ObjectId> idsActores2 = List.of("507f1f77bcf86cd799439014", "507f1f77bcf86cd799439015").stream().map(ObjectId::new).collect(Collectors.toList());
        Pelicula pelicula2 = new Pelicula("Pulp Fiction", Year.of(1994), List.of(Genero.DRAMA, Genero.THRILLER), idDirector2,
                8.9, idsActores2, 154);
        pelicula2.setDirector(director2);
        peliculas.add(pelicula2);

        Persona director3 = new Persona("Steven", "Spielberg", "Director estadounidense con películas épicas y aventuras de gran escala.",
                LocalDate.of(1946, 12, 18), "Americano", new ArrayList<>());
        ObjectId idDirector3 = new ObjectId();
        List<ObjectId> idsActores3 = List.of("507f1f77bcf86cd799439016", "507f1f77bcf86cd799439017", "507f1f77bcf86cd799439018").stream().map(ObjectId::new).collect(Collectors.toList());
        Pelicula pelicula3 = new Pelicula("Jurassic Park", Year.of(1993), List.of(Genero.AVENTURA, Genero.CIENCIA_FICCION),
                idDirector3, 8.1, idsActores3, 127);
        pelicula3.setDirector(director3);
        peliculas.add(pelicula3);

        Persona director4 = new Persona("Martin", "Scorsese", "Director estadounidense ganador de múltiples premios y especialista en drama criminal.",
                LocalDate.of(1942, 11, 17), "Americano", new ArrayList<>());
        ObjectId idDirector4 = new ObjectId();
        List<ObjectId> idsActores4 = List.of("507f1f77bcf86cd799439019", "507f1f77bcf86cd799439020").stream().map(ObjectId::new).collect(Collectors.toList());
        Pelicula pelicula4 = new Pelicula("The Wolf of Wall Street", Year.of(2013), List.of(Genero.DRAMA, Genero.COMEDIA),
                idDirector4, 8.2, idsActores4, 180);
        pelicula4.setDirector(director4);
        peliculas.add(pelicula4);

        Persona director5 = new Persona("Ridley", "Scott", "Director británico famoso por su trabajo en ciencia ficción y drama histórico.",
                LocalDate.of(1937, 11, 30), "Británico", new ArrayList<>());
        ObjectId idDirector5 = new ObjectId();
        List<ObjectId> idsActores5 = List.of("507f1f77bcf86cd799439021", "507f1f77bcf86cd799439022", "507f1f77bcf86cd799439023").stream().map(ObjectId::new).collect(Collectors.toList());
        Pelicula pelicula5 = new Pelicula("Gladiator", Year.of(2000), List.of(Genero.ACCION, Genero.DRAMA), idDirector5, 8.5,
                idsActores5, 155);
        pelicula5.setDirector(director5);
        peliculas.add(pelicula5);

        Persona director6 = new Persona("James", "Cameron", "Director canadiense conocido por películas de acción y ciencia ficción.",
                LocalDate.of(1954, 8, 16), "Canadiense", new ArrayList<>());
        ObjectId idDirector6 = new ObjectId();
        List<ObjectId> idsActores6 = List.of("507f1f77bcf86cd799439024", "507f1f77bcf86cd799439025").stream().map(ObjectId::new).collect(Collectors.toList());
        Pelicula pelicula6 = new Pelicula("Titanic", Year.of(1997), List.of(Genero.ROMANCE, Genero.DRAMA), idDirector6, 7.8,
                idsActores6, 195);
        pelicula6.setDirector(director6);
        peliculas.add(pelicula6);

        Persona director7 = new Persona("Alfred", "Hitchcock", "Director británico célebre por sus thrillers psicológicos.",
                LocalDate.of(1899, 8, 13), "Británico", new ArrayList<>()); // Fallecido, pero para prueba
        ObjectId idDirector7 = new ObjectId();
        List<ObjectId> idsActores7 = List.of("507f1f77bcf86cd799439026", "507f1f77bcf86cd799439027").stream().map(ObjectId::new).collect(Collectors.toList());
        Pelicula pelicula7 = new Pelicula("Psycho", Year.of(1960), List.of(Genero.THRILLER, Genero.HORROR), idDirector7, 8.5,
                idsActores7, 109);
        pelicula7.setDirector(director7);
        peliculas.add(pelicula7);

        Persona director8 = new Persona("Tim", "Burton", "Director estadounidense conocido por su estilo visual gótico y fantasía oscura.",
                LocalDate.of(1958, 8, 25), "Americano", new ArrayList<>());
        ObjectId idDirector8 = new ObjectId();
        List<ObjectId> idsActores8 = List.of("507f1f77bcf86cd799439028", "507f1f77bcf86cd799439029", "507f1f77bcf86cd799439030").stream().map(ObjectId::new).collect(Collectors.toList());
        Pelicula pelicula8 = new Pelicula("The Nightmare Before Christmas", Year.of(1993),
                List.of(Genero.FANTASIA, Genero.COMEDIA), idDirector8, 8.0, idsActores8, 76);
        pelicula8.setDirector(director8);
        peliculas.add(pelicula8);

        Persona director9 = new Persona("Stanley", "Kubrick", "Director estadounidense conocido por su cine de autor y cuidado estético.",
                LocalDate.of(1928, 7, 26), "Americano", new ArrayList<>()); // Fallecido
        ObjectId idDirector9 = new ObjectId();
        List<ObjectId> idsActores9 = List.of("507f1f77bcf86cd799439031", "507f1f77bcf86cd799439032").stream().map(ObjectId::new).collect(Collectors.toList());
        Pelicula pelicula9 = new Pelicula("2001: A Space Odyssey", Year.of(1968), List.of(Genero.CIENCIA_FICCION, Genero.AVENTURA),
                        idDirector9, 8.3, idsActores9, 149);
        pelicula9.setDirector(director9);
        peliculas.add(pelicula9);

        Persona director10 = new Persona("Francis Ford", "Coppola", "Director estadounidense clave en el cine de autor y las sagas familiares.",
                LocalDate.of(1939, 4, 7), "Americano", new ArrayList<>());
        ObjectId idDirector10 = new ObjectId();
        List<ObjectId> idsActores10 = List.of("507f1f77bcf86cd799439033", "507f1f77bcf86cd799439034", "507f1f77bcf86cd799439035").stream().map(ObjectId::new).collect(Collectors.toList());
        Pelicula pelicula10 = new Pelicula("The Godfather", Year.of(1972), List.of(Genero.DRAMA, Genero.THRILLER), idDirector10,
                9.2, idsActores10, 175);
        pelicula10.setDirector(director10);
        peliculas.add(pelicula10);
    }

    public List<Pelicula> obtenerPeliculasTodas() {
        return peliculas;
    }

    public List<Pelicula> obtenerPeliculasFiltradas(String nombre, List<Genero> generos, Year anio, Double valoracion) {
        ArrayList<Pelicula> listaFiltrada = new ArrayList<>();
        for (Pelicula busqueda : peliculas) {
            if (nombre != null && !busqueda.getTitulo().toLowerCase().contains(nombre.toLowerCase())) {
                continue;
            }
            if (generos != null) {
                boolean generoEncontrado = false;
                for (int i = 0; i < generos.size(); i++) {
                    for (int j = 0; j < busqueda.getGeneros().size(); j++) {
                        if (generos.get(i) == busqueda.getGeneros().get(j)) {
                            generoEncontrado = true;
                            break;
                        }
                    }
                }
                if (generoEncontrado == false) {
                    continue;
                }
            }
            if (anio != null && !busqueda.getAnyo().equals(anio)) {
                continue;
            }
            if (valoracion != null && (busqueda.getValoracion() < valoracion.doubleValue()
                    || busqueda.getValoracion() >= valoracion.doubleValue() + 1)) {
                continue;
            }
            listaFiltrada.add(busqueda);
        }
        return listaFiltrada;
    }

    public List<Pelicula> obtenerDestacados(int cantidad) {
        Collections.sort(peliculas, new OrdenPorValoracion().reversed());
        ArrayList<Pelicula> listaDestacados = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            listaDestacados.add(peliculas.get(i));
        }
        return listaDestacados;
    }

    public List<Pelicula> obtenerPorFecha(int cantidad) {
        Collections.sort(peliculas, new OrdenPorFecha().reversed());
        ArrayList<Pelicula> listaFechas = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            listaFechas.add(peliculas.get(i));
        }
        return listaFechas;
    }

    public Pelicula obtenerPeliculaPorId(String id) {
        for (Pelicula busqueda : peliculas) {
            if (busqueda.getId().equals(id)) {
                return busqueda;
            }
        }
        return null;
    }
}
