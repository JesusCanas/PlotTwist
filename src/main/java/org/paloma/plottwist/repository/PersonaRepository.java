
package org.paloma.plottwist.repository;

import org.paloma.plottwist.model.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PersonaRepository extends MongoRepository<Persona, String> {

}
