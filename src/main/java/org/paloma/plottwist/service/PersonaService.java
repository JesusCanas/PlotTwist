
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

@Service
public class PersonaService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PersonaRepository repositoryPersona;

    public List<Metraje> metrajesDestacados(int cantidad, String idPersona) {
        ArrayList<Metraje> devolucion = new ArrayList<>();
        Persona persona;

        Query query = new Query();
        
        System.out.println("Buscando persona con ID: " + idPersona);
        persona = repositoryPersona.findById(idPersona).orElse(null);
        System.out.println("Persona encontrada: " + persona);

        query.addCriteria(Criteria.where("_id").in(persona.getMetrajesId()));

        query.limit(cantidad);

        devolucion.addAll(mongoTemplate.find(query, Pelicula.class));
        devolucion.addAll(mongoTemplate.find(query, Serie.class));

        return devolucion;
    }
    
}
