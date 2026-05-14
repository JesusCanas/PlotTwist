
package org.paloma.plottwist.repository;

import org.paloma.plottwist.model.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Interfaz del repositorio de la coleccion "serie"
 * Extiende MongoRepository para que SpringBoot pueda hacer consultas en la base
 * de datos Mongo con el codigo java
 * 
 * @author MiguelSg77
 * @version 1.0
 */
@Repository
public interface SerieRepository extends MongoRepository<Serie, String> {

    /**
     * Declarar este método sirve para que Spring detecte el nombre y haga la
     * consulta en base al atributo especificado
     * Básicamente, eeste método ordena todos los datos de la colección "serie"
     * de mayor a menor en base a sus valoraciones
     * y recoge n datos, los cuales seleccionarmos con el parametro pageable
     * 
     * @param pageable Numero de datos que vamos a recoger
     * @return Una lista con los datos recogidos en la colección
     */
    List<Serie> findTopByOrderByValoracionDesc(Pageable pageable);

    /**
     * Este metodó ordenara por el año de salida las series de mayor a menor y
     * devolverá
     * una lista de todas las series ordenadas
     * 
     * @return Lista de series ordenada por año
     */
    List<Serie> findTopByOrderByAnyoDesc();

}
