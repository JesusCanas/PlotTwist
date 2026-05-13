
package org.paloma.plottwist.repository;

import org.paloma.plottwist.model.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SerieRepository extends MongoRepository<Serie, String> {


    List<Serie> findTopByOrderByValoracionDesc(Pageable pageable);

    List<Serie> findTopByOrderByAnyoDesc();

}
