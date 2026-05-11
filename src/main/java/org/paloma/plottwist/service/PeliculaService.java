
package org.paloma.plottwist.service;

import java.util.List;

import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.Pelicula;
import org.paloma.plottwist.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class PeliculaService {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PeliculaRepository repository;

     public List<Pelicula> obtenerPeliculasFiltradas(String nombre, List<Genero> generos, Integer anio, Double valoracion) {
        Query query = new Query();

        if (nombre != null) {
                query.addCriteria(Criteria.where("titulo").regex(nombre, "i"));
        }
        
        if(generos != null) {
                query.addCriteria(Criteria.where("generos").in(generos));
        }

        if(anio != null) {
                query.addCriteria(Criteria.where("anyo").eq(anio));
        }

        if(valoracion != null) {
                query.addCriteria(Criteria.where("valoracion").gte(valoracion).lt(valoracion + 1));
        }

        return mongoTemplate.find(query, Pelicula.class);
     }
    
     public List<Pelicula> hola() {
        return null;
     }
}

