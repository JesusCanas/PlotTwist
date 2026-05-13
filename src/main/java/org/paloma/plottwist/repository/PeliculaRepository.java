
package org.paloma.plottwist.repository;

import java.util.List;

import org.paloma.plottwist.model.Pelicula;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface PeliculaRepository extends MongoRepository<Pelicula, String> {
   
    List<Pelicula> findTopByOrderByValoracionDesc(Pageable pageable);

}
