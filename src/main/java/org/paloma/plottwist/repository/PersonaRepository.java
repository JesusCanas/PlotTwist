
package org.paloma.plottwist.repository;

import org.paloma.plottwist.model.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz repositorio de la coleccion "persona".
 * Extiende MongoRepository para que SpringBoot pueda hacer consultas en la base
 * de datos Mongo con el codigo java
 * 
 * @author MiguelSg77
 * @version 1.0
 */
@Repository
public interface PersonaRepository extends MongoRepository<Persona, String> {

}
