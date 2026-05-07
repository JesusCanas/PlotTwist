
package org.paloma.plottwist.repository;

import org.paloma.plottwist.model.Pelicula;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PeliculaRepository extends MongoRepository<Pelicula, String> {
   
}
