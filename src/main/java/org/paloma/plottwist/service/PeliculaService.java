package org.paloma.plottwist.service;

import java.time.Year;
import java.util.List;

import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.Pelicula;
import org.paloma.plottwist.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PeliculaService {
    
    @Autowired
    private PeliculaRepository repository;

    public List<Pelicula> obtenerTodas() {
        return repository.findAll();
    }

    public List<Pelicula> porGenero(Genero genero) {
        return repository.findByGenero(genero);
    }
    public List<Pelicula> porValoracion(double valoracion) {
        return repository.findByValoracionGreaterThan(valoracion);
    }
     
    public List<Pelicula> porAnyo(Year anyo) {
        return repository.findByAnyo(anyo);
    }
}
