package org.paloma.plottwist.controller;

import java.time.Year;
import java.util.List;
import org.paloma.plottwist.dao.PeliculasDAO;
import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.Pelicula;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/peliculas")
public class PeliculasController {
    PeliculasDAO peliculasDAOejemplo = new PeliculasDAO();

    @GetMapping("/mostrarTodo")
    public List<Pelicula> obtenerPeliculasTodas() {
        return peliculasDAOejemplo.obtenerPeliculasTodas();
    }

    @GetMapping("/mostrarFiltrado")
    public List<Pelicula> obtenerPeliculasFiltradas(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Double valoracion, 
            @RequestParam(required = false) String anyo,
            @RequestParam(required = false) List<Genero> generos) {
        
        Year anyoYear = (anyo != null) ? Year.parse(anyo) : null;
        return peliculasDAOejemplo.obtenerPeliculasFiltradas(nombre, generos, anyoYear, valoracion);
    }

    @GetMapping("/obtenerDestacados")
    public List<Pelicula> obtenerDestacados(@RequestParam int cantidad) {
        return peliculasDAOejemplo.obtenerDestacados(cantidad);
    }

    @GetMapping("/obtenerPorFecha")
    public List<Pelicula> obtenerPorFecha(@RequestParam int cantidad) {
        return peliculasDAOejemplo.obtenerPorFecha(cantidad);
    }

    @GetMapping("/obtenerPeliculaPorId")
    public Pelicula obtenerPeliculaPorId(@RequestParam String id) {
        return peliculasDAOejemplo.obtenerPeliculaPorId(id);
    }
}
