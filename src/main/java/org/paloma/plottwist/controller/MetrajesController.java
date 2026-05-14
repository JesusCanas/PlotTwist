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
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/metrajes")
public class MetrajesController {

    @Autowired
    private MetrajeService serviceMetraje;

    @GetMapping("/obtenerTipo")
    public <T extends Metraje> List<T> obtenerTipo(TipoMetraje tipoMetraje) {
        Class<T> clase;

        clase = (Class<T>) (tipoMetraje == TipoMetraje.PELICULA ? Pelicula.class : Serie.class);
        return serviceMetraje.obtenerUnTipoMetrajes(clase);
    }

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

    @GetMapping("/obtenerTodosFiltrados")
    public List<Metraje> obtenerTodosFiltrados(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) List<Genero> generos,
            @RequestParam(required = false) Integer anyo,
            @RequestParam(required = false) Double valoracion) {
        return serviceMetraje.obtenerTodosMetrajesFiltrados(nombre, generos, anyo, valoracion);
    }

    @GetMapping("obtenerDestacados")
    public List<Metraje> obtenerDestacados(int cantidad) {
        return serviceMetraje.obtenerDestacados(cantidad);
    }

    @GetMapping("obtenerDetalles")
    public Metraje obtenerDetalles(String metraje) {
        return serviceMetraje.obtenerDetalles(metraje);
    }

}
