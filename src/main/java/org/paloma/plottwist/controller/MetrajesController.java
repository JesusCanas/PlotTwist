package org.paloma.plottwist.controller;

import java.util.List;
import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.Metraje;
import org.paloma.plottwist.model.Pelicula;
import org.paloma.plottwist.model.Serie;
import org.paloma.plottwist.model.TipoMetraje;
import org.paloma.plottwist.service.MetrajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para gestionar operaciones relacionadas con metrajes (películas y series).
 * Proporciona endpoints para obtener, filtrar y buscar metrajes desde la base de datos.
 * Todos los métodos consultan la colección de películas o series según lo especificado.
 * 
 * @author MiguelSg77
 * @version 1.0
 */
@RestController
@RequestMapping("/metrajes")
public class MetrajesController {

    @Autowired
    private MetrajeService serviceMetraje;

    /**
     * Obtiene todos los metrajes de un tipo específico (películas o series).
     * Realiza una búsqueda completa en la colección especificada sin aplicar filtros.
     * El método utiliza generics para retornar el tipo correcto según el tipo de metraje solicitado.
     * 
     * @param tipoMetraje Tipo de metraje a obtener (PELICULA o SERIE)
     * @return Lista con todos los metrajes del tipo especificado
     */
    @GetMapping("/obtenerTipo")
    public <T extends Metraje> List<T> obtenerTipo(TipoMetraje tipoMetraje) {
        Class<T> clase;

        clase = (Class<T>) (tipoMetraje == TipoMetraje.PELICULA ? Pelicula.class : Serie.class);
        return serviceMetraje.obtenerUnTipoMetrajes(clase);
    }

    /**
     * Obtiene metrajes de un tipo específico aplicando filtros opcionales.
     * Permite filtrar metrajes por nombre, géneros, año de estreno y valoración.
     * Todos los filtros son opcionales. Si se proporcionan, se aplican conjuntamente (AND lógico).
     * 
     * @param tipoMetraje Tipo de metraje a obtener (PELICULA o SERIE)
     * @param nombre Filtro opcional: caracteres contenidos en el título del metraje
     * @param generos Filtro opcional: lista de géneros (el metraje debe tener al menos uno)
     * @param anyo Filtro opcional: año de estreno del metraje
     * @param valoracion Filtro opcional: valoración de 0 a 5 (retorna metrajes con valoración entre x.0 y x.9)
     * @return Lista de metrajes del tipo especificado que cumplen con los filtros
     */
    @GetMapping("/obtenerFiltrados")
    public <T extends Metraje> List<T> obtenerFiltrados(
            TipoMetraje tipoMetraje,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) List<Genero> generos,
            @RequestParam(required = false) Integer anyo,
            @RequestParam(required = false) Double valoracion) {

        Class<T> clase;

        clase = (Class<T>) (tipoMetraje == TipoMetraje.PELICULA ? Pelicula.class : Serie.class);
        return serviceMetraje.obtenerMetrajesFiltrados(clase, nombre, generos, anyo, valoracion);
    }

    /**
     * Obtiene metrajes de todos los tipos (películas y series) aplicando los mismos filtros opcionales.
     * Realiza búsquedas simultáneas en ambas colecciones y retorna los resultados combinados.
     * Los filtros funcionan igual que en obtenerFiltrados, aplicándose a ambos tipos de metrajes.
     * 
     * @param nombre Filtro opcional: caracteres contenidos en el título del metraje
     * @param generos Filtro opcional: lista de géneros (el metraje debe tener al menos uno)
     * @param anyo Filtro opcional: año de estreno del metraje
     * @param valoracion Filtro opcional: valoración de 0 a 5 (retorna metrajes con valoración entre x.0 y x.9)
     * @return Lista combinada de películas y series que cumplen con los filtros
     */
    @GetMapping("/obtenerTodosFiltrados")
    public List<Metraje> obtenerTodosFiltrados(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) List<Genero> generos,
            @RequestParam(required = false) Integer anyo,
            @RequestParam(required = false) Double valoracion) {
        return serviceMetraje.obtenerTodosMetrajesFiltrados(nombre, generos, anyo, valoracion);
    }

    /**
     * Obtiene los metrajes más destacados (mejor valorados) de cada tipo.
     * Retorna una cantidad equilibrada de películas y series ordenadas por valoración descendente.
     * Los metrajes con mayor puntuación aparecerán primeros en la lista.
     * 
     * @param cantidad Número de películas y de series a retornar (total = cantidad * 2)
     * @return Lista con los metrajes más destacados, primero películas y luego series
     */
    @GetMapping("obtenerDestacados")
    public List<Metraje> obtenerDestacados(int cantidad) {
        return serviceMetraje.obtenerDestacados(cantidad);
    }

    /**
     * Obtiene los detalles completos de un metraje específico por su ID.
     * Busca el metraje en ambas colecciones (películas y series) e hidrata todos sus atributos,
     * incluyendo los objetos relacionados de director y actores desde la colección de personas.
     * 
     * @param metraje ID del metraje a obtener
     * @return El metraje con todos sus detalles completos incluyendo director y actores
     */
    @GetMapping("obtenerDetalles")
    public Metraje obtenerDetalles(String metraje) {
        return serviceMetraje.obtenerDetalles(metraje);
    }

}
