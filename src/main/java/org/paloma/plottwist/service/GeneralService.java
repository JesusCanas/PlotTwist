package org.paloma.plottwist.service;

import java.util.List;

import org.paloma.plottwist.model.Metraje;
import org.paloma.plottwist.model.Persona;
import org.paloma.plottwist.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.paloma.plottwist.repository.PersonaRepository;;

public class GeneralService {

    @Autowired
    PersonaRepository repository;

    public void hidratarMetraje(List<Persona> actores, Persona director, Metraje metraje) {
        actores = repository.findAllById(metraje.getIdsActores());
        metraje.setActores(repository.findAllById(metraje.getIdsActores()));
        repository.findAllById(metraje.getIdsActores());
        repository.findById(metraje.getIdDirector()).ifPresent(d -> metraje.setDirector(director));
        
    }
}
