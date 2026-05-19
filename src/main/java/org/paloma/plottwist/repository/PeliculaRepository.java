
package org.paloma.plottwist.repository;

import java.util.List;

import org.paloma.plottwist.model.Pelicula;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

/**
 * Interfaz del repositorio de la coleccion "pelicula"
 * Extiende MongoRepository para que SpringBoot pueda hacer consultas en la base
 * de datos Mongo con el codigo java
 * 
 * @author MiguelSg77
 * @version 1.0
 */
@Repository
public interface PeliculaRepository extends MongoRepository<Pelicula, String> {

    /**
     * Declarar este método sirve para que Spring detecte el nombre y haga la
     * consulta en base al atributo especificado.
     * Básicamente, este método ordena todos los datos de la colección "pelicula"
     * de mayor a menor en base a sus valoraciones
     * y recoge n datos, los cuales seleccionaremos con el parámetro pageable
     * 
     * @param pageable Numero de datos que vamos a recoger
     * @return Una lista con los datos recogidos en la colección
     */
    List<Pelicula> findTopByOrderByValoracionDesc(Pageable pageable);

    /**
     * Este método ordenará por el año de salida las películas de mayor a menor y
     * devolverá una lista de todas las series ordenadas
     * 
     * @return Lista de películas ordenada por año
     */
    List<Pelicula> findByOrderByAnyoDesc();

}
