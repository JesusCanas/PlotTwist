
package org.paloma.plottwist.service;

import java.time.Year;
import java.util.List;

import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class PeliculaService {
    
    @Autowired
    private MongoTemplate mongoTemplate;

     public List<Pelicula> obtenerPeliculasFiltradas(String nombre, List<Genero> generos, Year anio, Double valoracion) {
        Query query = new Query();

        if (nombre != null) {
                query.addCriteria(Criteria.where("titulo").regex(nombre, "i"));
        }
        
        if(generos != null) {
                query.addCriteria(Criteria.where("generos").in(generos));
        }

        if(anio != null) {
                query.addCriteria(Criteria.where("anyo"));
        }


     }
    
}

