package org.paloma.plottwist.service;

import java.util.ArrayList;
import java.util.List;

import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.Metraje;
import org.paloma.plottwist.model.Pelicula;
import org.paloma.plottwist.model.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.paloma.plottwist.repository.PeliculaRepository;
import org.paloma.plottwist.repository.PersonaRepository;
import org.paloma.plottwist.repository.SerieRepository;

/**
 * Clase metrajeService. Este servicio lo que tiene son diferentes metodos
 * relacionados a peliculas y series, y estos lo que hacen son querys con codigo
 */
@Service
public class MetrajeService {

    @Autowired
    PersonaRepository repositoryPersona;

    @Autowired
    SerieRepository repositorySerie;

    @Autowired
    PeliculaRepository repositoryPelicula;

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Método hidratar metraje
     * 
     * @param metraje
     */
    public void hidratarMetraje(Metraje metraje) {
        metraje.setActores(repositoryPersona.findAllById(metraje.getIdsActores()));
        repositoryPersona.findById(metraje.getIdDirector()).ifPresent(d -> metraje.setDirector(d));
    }

    public <T extends Metraje> List<T> obtenerUnTipoMetrajes(Class<T> clase) {
        return mongoTemplate.findAll(clase);
    }

    public <T extends Metraje> List<T> obtenerMetrajesFiltrados(Class<T> clase, String nombre, List<Genero> generos,
            Integer anio,
            Double valoracion) {
        Query query = new Query();

        if (nombre != null) {
            query.addCriteria(Criteria.where("titulo").regex(nombre, "i"));
        }

        if (generos != null) {
            query.addCriteria(Criteria.where("generos").in(generos));
        }

        if (anio != null) {
            query.addCriteria(Criteria.where("anyo").eq(anio));
        }

        if (valoracion != null) {
            query.addCriteria(Criteria.where("valoracion").gte(valoracion).lt(valoracion + 1));
        }

        return mongoTemplate.find(query, clase);
    }

    public List<Metraje> obtenerTodosMetrajesFiltrados(String nombre, List<Genero> generos, Integer anio,
            Double valoracion) {
        ArrayList<Metraje> metrajesFiltrados = new ArrayList<>();

        metrajesFiltrados.addAll(obtenerMetrajesFiltrados(Pelicula.class, nombre, generos, anio, valoracion));
        metrajesFiltrados.addAll(obtenerMetrajesFiltrados(Serie.class, nombre, generos, anio, valoracion));

        return metrajesFiltrados;
    }

    public List<Metraje> obtenerDestacados(int cantidad) {
        ArrayList<Metraje> metrajesDestacados = new ArrayList<>();

        metrajesDestacados.addAll(repositoryPelicula.findTopByOrderByValoracionDesc(PageRequest.of(0, cantidad)));
        metrajesDestacados.addAll(repositorySerie.findTopByOrderByValoracionDesc(PageRequest.of(0, cantidad)));

        return metrajesDestacados;
    }

    public Metraje obtenerDetalles(String idMetraje) {
        Metraje metraje = repositoryPelicula.findById(idMetraje).orElse(null);
        if (metraje == null) {
            metraje = repositorySerie.findById(idMetraje).orElse(null);
        }

        hidratarMetraje(metraje);

        return metraje;
    }

}
