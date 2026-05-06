/*
package org.paloma.plottwist.service;

import java.time.Year;
import java.util.List;

import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.Serie;
import org.paloma.plottwist.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class SerieService {

    @Autowired
    private SerieRepository repository;

    public List<Serie> obtenerTodas() {
        return repository.findAll();
    }

    public List<Serie> porGenero(Genero genero) {
        return repository.findByGenero(genero);
    }
     public List<Serie> porValoracion(Double valoracion) {
        return repository.findByValoracionGreaterThan(valoracion);
    }
    public List<Serie> porAnyo(Year anyo) {
        return repository.findByAnyo(anyo);
    }

}
*/
