/*
package org.paloma.plottwist.service;

import org.paloma.plottwist.model.Persona;
import org.paloma.plottwist.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository repository;

    public List<Persona> obtenerTodas() {
        return repository.findAll();
    }

    public List<Persona> porNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    public List<Persona> porApellido(String apellido) {
        return repository.findByApellido(apellido);
    }

    public List<Persona> porRol(String rol) {
        return repository.findByRol(rol);
    }
}
*/