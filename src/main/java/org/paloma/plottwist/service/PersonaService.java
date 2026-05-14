
package org.paloma.plottwist.service;

import org.paloma.plottwist.model.Persona;
import org.paloma.plottwist.model.Serie;
import org.paloma.plottwist.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;
import org.paloma.plottwist.model.Metraje;
import org.paloma.plottwist.model.Pelicula;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase PersonaService.
 * Este servicio se centra en metodos a la colección personas, haciendo querys a
 * la base de datos de esta coleccion
 * 
 * @author MiguelSg77
 * @version 1.0
 */
@Service
public class PersonaService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PersonaRepository repositoryPersona;

    /**
     * Busca n cantidad de metrajes de cada tipo (Serie y Pelicula) de una persona
     * en base a su id.
     * Este método sirve para mostrar la cantidad de peliculas y series que queramos
     * para los detalles de una persona (director o actor)
     * La cantidad que pongamos será la cantidad de peliculas y la cantidad de
     * series que tenga la lista.
     * El id es de la persona de la que queremos sacar los metrajes
     * 
     * @param cantidad  Cantidad de metrajes (peliculas y series)
     * @param idPersona ID de la persona en la BD
     * @return una lista de n metrajes, n/2 peliculas y n/2 series
     */
    public List<Metraje> metrajesDestacados(int cantidad, String idPersona) {
        ArrayList<Metraje> devolucion = new ArrayList<>();
        Persona persona;

        Query query = new Query();

        persona = repositoryPersona.findById(idPersona).orElse(null);

        query.addCriteria(Criteria.where("_id").in(persona.getMetrajesId()));

        query.limit(cantidad);

        devolucion.addAll(mongoTemplate.find(query, Pelicula.class));
        devolucion.addAll(mongoTemplate.find(query, Serie.class));

        return devolucion;
    }

}
