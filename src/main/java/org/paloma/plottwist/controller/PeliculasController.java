package org.paloma.plottwist.controller;

import java.util.List;
import org.paloma.plottwist.dao.PeliculasDAO;
import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.Pelicula;
import org.paloma.plottwist.repository.PeliculaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/peliculas")
public class PeliculasController {

    private final PeliculaRepository repository;

    public PeliculasController(PeliculaRepository repository) {
        this.repository = repository;
    }

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

        Integer anyoInt = (anyo != null) ? Integer.parseInt(anyo) : null;
        return peliculasDAOejemplo.obtenerPeliculasFiltradas(nombre, generos, anyoInt, valoracion);
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

    @GetMapping("/mostrarBDTodo")
    public List<Pelicula> obtenerTodo() {
        return repository.findAll();
    }

}
