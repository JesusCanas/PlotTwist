/*
package org.paloma.plottwist.repository;

import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Year;
import java.util.List;

public interface SerieRepository extends MongoRepository<Serie, String> {


    List<Serie> findByTitulo(String titulo);

    List<Serie> findByGenero(Genero genero);

    List<Serie> findByValoracionGreaterThan(double valoracion);
    
    List<Serie> findByAnyo(Year anyo);
}
*/