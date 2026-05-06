package org.paloma.plottwist.controller;

import java.time.Year;
import java.util.List;
import org.paloma.plottwist.dao.SeriesDAO;
import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.Serie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/series")
public class SeriesController {
    SeriesDAO listaEjemplo = new SeriesDAO();

    @GetMapping("/mostrarTodo")
    public List<Serie> obtenerSeriesTodas() {
        return listaEjemplo.obtenerSeriesTodas();
    }

    @GetMapping("/mostrarFiltrado")
    public List<Serie> obtenerSeriesFiltradas(@RequestParam(required = false) String nombre,
            @RequestParam(required = false) Double valoracion, @RequestParam(required = false) String anyo,
            @RequestParam(required = false) List<Genero> generos) {
        if(anyo != null) {
            return listaEjemplo.obtenerSeriesFiltradas(nombre, generos, Year.parse(anyo), valoracion);
        }
        return listaEjemplo.obtenerSeriesFiltradas(nombre, generos, null, valoracion);
    }

    @GetMapping("/obtenerDestacados")
    public List<Serie> obtenerDestacados(@RequestParam int cantidad) {
        return listaEjemplo.obtenerDestacados(cantidad);
    }

    @GetMapping("/obtenerPorFecha")
    public List<Serie> obtenerPorFecha(@RequestParam int cantidad) {
        return listaEjemplo.obtenerPorFecha(cantidad);
    }

    @GetMapping("/obtenerSeriePorId")
    public Serie obtenerSeriePorId(@RequestParam String id) {
        return listaEjemplo.obtenerSeriePorId(id);
    }
}
