package org.paloma.plottwist.service;

import java.util.ArrayList;
import java.util.List;

import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.Metraje;
import org.paloma.plottwist.model.Pelicula;
import org.paloma.plottwist.model.Serie;
import org.paloma.plottwist.model.TipoMetraje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.paloma.plottwist.repository.PeliculaRepository;
import org.paloma.plottwist.repository.PersonaRepository;
import org.paloma.plottwist.repository.SerieRepository;

/**
 * Clase metrajeService. Este servicio lo que tiene son diferentes metodos
 * relacionados a peliculas y series, y estos lo que hacen son querys a la
 * coleccion "serie" y coleccion "pelicula",
 * para devolver distintos tipos de datos de estos dos.
 * Las consultas de las dos colecciones se han unido ya que tienen los mismos
 * metodos para las dos colecciones.
 * 
 * Es por ello que varios métodos tienen el parametro "tipoMetraje", que
 * dictamina si queremos buscar en la colección "serie" o "pelicula"
 * 
 * @author MiguelSg77
 * @version 1.0
 */
@Service
public class MetrajeService {

    @Autowired
    PersonaRepository repositoryPersona;

    @Autowired
    SerieRepository repositorySerie;

    @Autowired
    PeliculaRepository repositoryPelicula;

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Método hidratar metraje.
     * Un metraje viene con listas o atributos de otros objetos, pero para
     * guardarlos, necesitamos encontrar dichos objetos, ya que no vienen incluidos,
     * y para hacer eso necesitamos un metodo que consulte otra vez la Base de Datos
     * para buscar los atributos que faltan gracias a los ids que si vienen con el
     * objeto
     * metraje.
     * 
     * @param metraje El metraje que quieres hidratar
     */
    public void hidratarMetraje(Metraje metraje) {
        metraje.setActores(repositoryPersona.findAllById(metraje.getIdsActores()));
        repositoryPersona.findById(metraje.getIdDirector()).ifPresent(d -> metraje.setDirector(d));
    }

    /**
     * Este metodo devuelve una lista de todos los metrajes de un tipo que queramos
     * ("SERIE" o "PELICULA").
     * Hace una consulta a una coleccion específica, recogiendo cada uno de los
     * datos. Para decirle que colección queremos,
     * debemos escoger una de estas dos clases: Pelicula.class y Serie.class. Esto
     * hara la consulta para la coleccion que decidamos
     * según la clase
     * 
     * @param <T>   Generíco para que java sepa que T tiene que ser un metraje
     * @param clase Tipo que queremos: Pelicula.class | Serie.class
     * @return Una lista con todos los datos de la colección especificada
     */
    public <T extends Metraje> List<T> obtenerUnTipoMetrajes(Class<T> clase) {
        return mongoTemplate.findAll(clase);
    }

    /**
     * Este método devuelve una lista de metrajes de un tipo ("SERIE" o "PELICULA")
     * con los filtros que seleccionemos.
     * Primeramente, hay que especificar que tipo de metraje queremos
     * (Pelicula.class o Serie.class).
     * Después, tenemos 4 tipos de filtros:
     * Nombre: buscará metrajes que contenga la secuencia de caracteres que tiene
     * este parámetro
     * Generos: buscará metrajes que tengan al menos UN género de la lista de
     * géneros que pongamos como parámetro
     * Anio: buscará metrajes que se hayan estrenado en el año puesto en el
     * parámetro
     * Valoracion: buscará metrajes con una valoracion entre x.0 y x.9, siendo x la
     * valoración de 0 a 5
     * 
     * Hay que tener en cuenta que estos métodos no son necesarios. Si no pones uno,
     * simplemente se ignorará
     * 
     * @param <T>
     * @param clase      Pelicula.class | Serie.class
     * @param nombre     Caracteres que contienen las películas
     * @param generos    Generos de los metrajes a buscar
     * @param anio       Año de los metrajes
     * @param valoracion Valoración de los metrajes
     * @return Una lista con metrajes de un tipo con los filtros especificados
     */
    public <T extends Metraje> List<T> obtenerMetrajesFiltrados(Class<T> clase, String nombre, List<Genero> generos,
            Integer anio,
            Double valoracion) {
        Query query = new Query();

        if (nombre != null) {
            query.addCriteria(Criteria.where("titulo").regex(nombre, "i"));
        }

        if (generos != null) {
            query.addCriteria(Criteria.where("generos").in(generos));
        }

        if (anio != null) {
            query.addCriteria(Criteria.where("anyo").eq(anio));
        }

        if (valoracion != null) {
            query.addCriteria(Criteria.where("valoracion").gte(valoracion).lt(valoracion + 1));
        }

        return mongoTemplate.find(query, clase);
    }

    /**
     * Hace lo mismo que obtenerMetrajesFiltrados pero con los dos tipos de metrajes
     * juntos.
     * Busca en las dos colecciones los mismos filtros y guarda en una lista las dos
     * listas de metrajes encontrados con los filtros seleccionados
     * 
     * @param nombre
     * @param generos
     * @param anio
     * @param valoracion
     * @return Lista de las dos listas (Serie y Pelicula) juntas
     */
    public List<Metraje> obtenerTodosMetrajesFiltrados(String nombre, List<Genero> generos, Integer anio,
            Double valoracion) {
        ArrayList<Metraje> metrajesFiltrados = new ArrayList<>();

        metrajesFiltrados.addAll(obtenerMetrajesFiltrados(Pelicula.class, nombre, generos, anio, valoracion));
        metrajesFiltrados.addAll(obtenerMetrajesFiltrados(Serie.class, nombre, generos, anio, valoracion));

        return metrajesFiltrados;
    }

    /**
     * Busca n cantidad de metrajes con las mejores valoraciones
     * Este metodo está pensado para mostrar n peliculas y n series
     * en la pagina de inicio.
     * 
     * Básicamente, la cantidad que pongamos como parámetro será la cantidad de
     * objetos que se devolveran de cada tipo.
     * 
     * SIEMPRE se devolverán primero las películas y luego las series
     * 
     * EJEMPLO
     * Si la cantidad es 5, devolverá una lista de 10 objetos, 5 peliculas y 5
     * series mas destacadas
     * 
     * @param cantidad Cantidad de peliculas Y series que se devolverán
     * @return Lista con n metrajes, n/2 peliculas y n/2 series.
     */
    public List<Metraje> obtenerDestacados(int cantidad) {
        ArrayList<Metraje> metrajesDestacados = new ArrayList<>();

        metrajesDestacados.addAll(repositoryPelicula.findTopByOrderByValoracionDesc(PageRequest.of(0, cantidad)));
        metrajesDestacados.addAll(repositorySerie.findTopByOrderByValoracionDesc(PageRequest.of(0, cantidad)));

        return metrajesDestacados;
    }

    /**
     * Ordena la lista de metrajes (PELICULA o SERIE) de mas reciente a mas antigua
     * y la devuelve.
     * 
     * @param <T>
     * @param tipoMetraje PELICULA o SERIE
     * @return Lista ordenada por fecha
     */
    public <T extends Metraje> List<T> obtenerPorFecha(TipoMetraje tipoMetraje) {
        switch (tipoMetraje) {
            case PELICULA:
                return (List<T>) repositoryPelicula.findByOrderByAnyoDesc();

            case SERIE:
                return (List<T>) repositorySerie.findByOrderByAnyoDesc();

            default:
                return null;
        }
    }

    /**
     * Busca el metraje con el id seleccionado y usa el metodo hidratarMetraje para
     * obtener todos los detalles del metraje
     * 
     * @param idMetraje ID del metraje que queremos
     * @return El metraje con todos los atributos completos
     */
    public Metraje obtenerDetalles(String idMetraje) {
        Metraje metraje = repositoryPelicula.findById(idMetraje).orElse(null);
        if (metraje == null) {
            metraje = repositorySerie.findById(idMetraje).orElse(null);
        }

        hidratarMetraje(metraje);

        return metraje;
    }

}
