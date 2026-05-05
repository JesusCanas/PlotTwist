package org.paloma.plottwist.dao;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.paloma.plottwist.model.Estado;
import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.OrdenPorFecha;
import org.paloma.plottwist.model.OrdenPorValoracion;
import org.paloma.plottwist.model.Persona;
import org.paloma.plottwist.model.Serie;

public class SeriesDAO {

    /** Lista que almacena los datos en memoria. */
    private final List<Serie> series;

    public SeriesDAO() {
        series = new ArrayList<>();

        Persona director1 = new Persona("David", "Fincher", 61, "Estadounidense", "Director", new ArrayList<>());
        series.add(new Serie("Stranger Things", Year.of(2016), List.of(Genero.DRAMA, Genero.CIENCIA_FICCION, Genero.MISTERIO),
                director1, 8.7, List.of("507f1f77bcf86cd799439101", "507f1f77bcf86cd799439102", "507f1f77bcf86cd799439103"), 4, 34, 50, Estado.EMISION));

        Persona director2 = new Persona("Vince", "Gilligan", 60, "Estadounidense", "Director", new ArrayList<>());
        series.add(new Serie("Breaking Bad", Year.of(2008), List.of(Genero.DRAMA, Genero.SUSPENSE, Genero.MISTERIO), director2,
                9.5, List.of("507f1f77bcf86cd799439104", "507f1f77bcf86cd799439105"), 5, 62, 49, Estado.FINALIZADA));

        Persona director3 = new Persona("David Benioff", "& D. B.", 53, "Estadounidense", "Director", new ArrayList<>());
        series.add(new Serie("Game of Thrones", Year.of(2011), List.of(Genero.AVENTURA, Genero.FANTASIA, Genero.DRAMA),
                director3, 9.3, List.of("507f1f77bcf86cd799439106", "507f1f77bcf86cd799439107"), 8, 73, 57, Estado.FINALIZADA));

        Persona director4 = new Persona("Greg", "Daniels", 56, "Estadounidense", "Director", new ArrayList<>());
        series.add(new Serie("The Office", Year.of(2005), List.of(Genero.COMEDIA, Genero.DRAMA), director4, 8.8,
                List.of("507f1f77bcf86cd799439108", "507f1f77bcf86cd799439109"), 9, 201, 22, Estado.FINALIZADA));

        Persona director5 = new Persona("Peter", "Morgan", 58, "Británico", "Director", new ArrayList<>());
        series.add(new Serie("The Crown", Year.of(2016), List.of(Genero.DRAMA, Genero.ROMANCE), director5, 8.7,
                List.of("507f1f77bcf86cd799439110", "507f1f77bcf86cd799439111"), 6, 60, 58, Estado.EMISION));

        Persona director6 = new Persona("Charlie", "Brooker", 48, "Británico", "Director", new ArrayList<>());
        series.add(new Serie("Black Mirror", Year.of(2011), List.of(Genero.CIENCIA_FICCION, Genero.MISTERIO, Genero.DRAMA), director6,
                8.8, List.of("507f1f77bcf86cd799439112", "507f1f77bcf86cd799439113"), 6, 22, 60, Estado.EMISION));

        Persona director7 = new Persona("James", "Burrows", 74, "Estadounidense", "Director", new ArrayList<>());
        series.add(new Serie("Friends", Year.of(1994), List.of(Genero.COMEDIA, Genero.ROMANCE), director7, 8.9,
                List.of("507f1f77bcf86cd799439114", "507f1f77bcf86cd799439115"), 10, 236, 22, Estado.FINALIZADA));

        Persona director8 = new Persona("Jon", "Favreau", 57, "Estadounidense", "Director", new ArrayList<>());
        series.add(new Serie("The Mandalorian", Year.of(2019), List.of(Genero.AVENTURA, Genero.CIENCIA_FICCION), director8,
                8.7, List.of("507f1f77bcf86cd799439116", "507f1f77bcf86cd799439117"), 3, 24, 35, Estado.EMISION));

        Persona director9 = new Persona("Lauren", "Schmidt Hissrich", 38, "Estadounidense", "Directora", new ArrayList<>());
        series.add(new Serie("The Witcher", Year.of(2019), List.of(Genero.FANTASIA, Genero.AVENTURA, Genero.ACCION), director9,
                8.2, List.of("507f1f77bcf86cd799439118", "507f1f77bcf86cd799439119"), 3, 24, 60, Estado.EMISION));

        Persona director10 = new Persona("J. J.", "Abrams", 57, "Estadounidense", "Director", new ArrayList<>());
        series.add(new Serie("Lost", Year.of(2004), List.of(Genero.DRAMA, Genero.MISTERIO, Genero.SUSPENSE), director10,
                8.3, List.of("507f1f77bcf86cd799439120", "507f1f77bcf86cd799439121"), 6, 121, 44, Estado.FINALIZADA));
    }

    public List<Serie> obtenerSeriesTodas() {
        return series;
    }

    public List<Serie> obtenerSeriesFiltradas(String nombre, List<Genero> generos, Year anio, Double valoracion) {
        ArrayList<Serie> listaFiltrada = new ArrayList<>();
        for (Serie busqueda : series) {
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

    public List<Serie> obtenerDestacados(int cantidad) {
        Collections.sort(series, new OrdenPorValoracion().reversed());
        ArrayList<Serie> listaDestacados = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            listaDestacados.add(series.get(i));
        }
        return listaDestacados;
    }

    public List<Serie> obtenerPorFecha(int cantidad) {
        Collections.sort(series, new OrdenPorFecha().reversed());
        ArrayList<Serie> listaFechas = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            listaFechas.add(series.get(i));
        }
        return listaFechas;
    }

    public Serie obtenerSeriePorId(String id) {
        for (Serie busqueda : series) {
            if (busqueda.getId().equals(id)) {
                return busqueda;
            }
        }
        return null;
    }
}
