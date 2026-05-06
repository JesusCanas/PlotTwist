package org.paloma.plottwist.repository;

import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.Pelicula;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Year;
import java.util.List;

public interface PeliculaRepository extends MongoRepository<Pelicula, String> {


    List<Pelicula> findByTitulo(String titulo);

    List<Pelicula> findByGenero(Genero genero);

   
    List<Pelicula> findByValoracionGreaterThan(double valoracion);
      List<Pelicula> findByAnyo(Year anyo);
   
}