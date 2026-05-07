package org.paloma.plottwist.dao;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
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

        Persona director1 = new Persona("David", "Fincher", "Director estadounidense conocido por thrillers psicológicos y ciencia ficción.",
                LocalDate.of(1962, 8, 28), "Estadounidense", new ArrayList<>());
        ObjectId idDirector1 = new ObjectId();
        List<ObjectId> idsActores1 = List.of("507f1f77bcf86cd799439101", "507f1f77bcf86cd799439102", "507f1f77bcf86cd799439103").stream().map(ObjectId::new).collect(Collectors.toList());
        Serie serie1 = new Serie("Stranger Things", Year.of(2016), List.of(Genero.DRAMA, Genero.CIENCIA_FICCION, Genero.MISTERIO),
                idDirector1, 8.7, idsActores1, 4, 34, 50, Estado.EMISION);
        serie1.setDirector(director1);
        series.add(serie1);

        Persona director2 = new Persona("Vince", "Gilligan", "Creador de series y guionista estadounidense con enfoque en suspense y humanidad.",
                LocalDate.of(1967, 2, 10), "Estadounidense", new ArrayList<>());
        ObjectId idDirector2 = new ObjectId();
        List<ObjectId> idsActores2 = List.of("507f1f77bcf86cd799439104", "507f1f77bcf86cd799439105").stream().map(ObjectId::new).collect(Collectors.toList());
        Serie serie2 = new Serie("Breaking Bad", Year.of(2008), List.of(Genero.DRAMA, Genero.SUSPENSE, Genero.MISTERIO), idDirector2,
                9.5, idsActores2, 5, 62, 49, Estado.FINALIZADA);
        serie2.setDirector(director2);
        series.add(serie2);

        Persona director3 = new Persona("David Benioff", "& D. B.", "Showrunner y escritor conocido por dramas épicos de fantasía.",
                LocalDate.of(1970, 9, 25), "Estadounidense", new ArrayList<>());
        ObjectId idDirector3 = new ObjectId();
        List<ObjectId> idsActores3 = List.of("507f1f77bcf86cd799439106", "507f1f77bcf86cd799439107").stream().map(ObjectId::new).collect(Collectors.toList());
        Serie serie3 = new Serie("Game of Thrones", Year.of(2011), List.of(Genero.AVENTURA, Genero.FANTASIA, Genero.DRAMA),
                idDirector3, 9.3, idsActores3, 8, 73, 57, Estado.FINALIZADA);
        serie3.setDirector(director3);
        series.add(serie3);

        Persona director4 = new Persona("Greg", "Daniels", "Guionista y director estadounidense conocido por comedias inteligentes.",
                LocalDate.of(1963, 6, 13), "Estadounidense", new ArrayList<>());
        ObjectId idDirector4 = new ObjectId();
        List<ObjectId> idsActores4 = List.of("507f1f77bcf86cd799439108", "507f1f77bcf86cd799439109").stream().map(ObjectId::new).collect(Collectors.toList());
        Serie serie4 = new Serie("The Office", Year.of(2005), List.of(Genero.COMEDIA, Genero.DRAMA), idDirector4, 8.8,
                idsActores4, 9, 201, 22, Estado.FINALIZADA);
        serie4.setDirector(director4);
        series.add(serie4);

        Persona director5 = new Persona("Peter", "Morgan", "Guionista británico especializado en drama histórico y biográfico.",
                LocalDate.of(1963, 3, 10), "Británico", new ArrayList<>());
        ObjectId idDirector5 = new ObjectId();
        List<ObjectId> idsActores5 = List.of("507f1f77bcf86cd799439110", "507f1f77bcf86cd799439111").stream().map(ObjectId::new).collect(Collectors.toList());
        Serie serie5 = new Serie("The Crown", Year.of(2016), List.of(Genero.DRAMA, Genero.ROMANCE), idDirector5, 8.7,
                idsActores5, 6, 60, 58, Estado.EMISION);
        serie5.setDirector(director5);
        series.add(serie5);

        Persona director6 = new Persona("Charlie", "Brooker", "Creador británico de antologías de ciencia ficción centradas en la tecnología.",
                LocalDate.of(1971, 3, 3), "Británico", new ArrayList<>());
        ObjectId idDirector6 = new ObjectId();
        List<ObjectId> idsActores6 = List.of("507f1f77bcf86cd799439112", "507f1f77bcf86cd799439113").stream().map(ObjectId::new).collect(Collectors.toList());
        Serie serie6 = new Serie("Black Mirror", Year.of(2011), List.of(Genero.CIENCIA_FICCION, Genero.MISTERIO, Genero.DRAMA), idDirector6,
                8.8, idsActores6, 6, 22, 60, Estado.EMISION);
        serie6.setDirector(director6);
        series.add(serie6);

        Persona director7 = new Persona("James", "Burrows", "Director estadounidense con una larga carrera en comedia televisiva.",
                LocalDate.of(1940, 12, 30), "Estadounidense", new ArrayList<>());
        ObjectId idDirector7 = new ObjectId();
        List<ObjectId> idsActores7 = List.of("507f1f77bcf86cd799439114", "507f1f77bcf86cd799439115").stream().map(ObjectId::new).collect(Collectors.toList());
        Serie serie7 = new Serie("Friends", Year.of(1994), List.of(Genero.COMEDIA, Genero.ROMANCE), idDirector7, 8.9,
                idsActores7, 10, 236, 22, Estado.FINALIZADA);
        serie7.setDirector(director7);
        series.add(serie7);

        Persona director8 = new Persona("Jon", "Favreau", "Director y guionista estadounidense conocido por producciones de acción y ciencia ficción.",
                LocalDate.of(1966, 10, 19), "Estadounidense", new ArrayList<>());
        ObjectId idDirector8 = new ObjectId();
        List<ObjectId> idsActores8 = List.of("507f1f77bcf86cd799439116", "507f1f77bcf86cd799439117").stream().map(ObjectId::new).collect(Collectors.toList());
        Serie serie8 = new Serie("The Mandalorian", Year.of(2019), List.of(Genero.AVENTURA, Genero.CIENCIA_FICCION), idDirector8,
                8.7, idsActores8, 3, 24, 35, Estado.EMISION);
        serie8.setDirector(director8);
        series.add(serie8);

        Persona director9 = new Persona("Lauren", "Schmidt Hissrich", "Showrunner estadounidense conocida por adaptaciones de fantasía.",
                LocalDate.of(1980, 6, 10), "Estadounidense", new ArrayList<>());
        ObjectId idDirector9 = new ObjectId();
        List<ObjectId> idsActores9 = List.of("507f1f77bcf86cd799439118", "507f1f77bcf86cd799439119").stream().map(ObjectId::new).collect(Collectors.toList());
        Serie serie9 = new Serie("The Witcher", Year.of(2019), List.of(Genero.FANTASIA, Genero.AVENTURA, Genero.ACCION), idDirector9,
                8.2, idsActores9, 3, 24, 60, Estado.EMISION);
        serie9.setDirector(director9);
        series.add(serie9);

        Persona director10 = new Persona("J. J.", "Abrams", "Director y productor estadounidense de acción y ciencia ficción.",
                LocalDate.of(1966, 6, 27), "Estadounidense", new ArrayList<>());
        ObjectId idDirector10 = new ObjectId();
        List<ObjectId> idsActores10 = List.of("507f1f77bcf86cd799439120", "507f1f77bcf86cd799439121").stream().map(ObjectId::new).collect(Collectors.toList());
        Serie serie10 = new Serie("Lost", Year.of(2004), List.of(Genero.DRAMA, Genero.MISTERIO, Genero.SUSPENSE), idDirector10,
                8.3, idsActores10, 6, 121, 44, Estado.FINALIZADA);
        serie10.setDirector(director10);
        series.add(serie10);
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
