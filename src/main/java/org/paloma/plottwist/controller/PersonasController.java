package org.paloma.plottwist.controller;

import java.util.List;

import org.paloma.plottwist.model.Metraje;
import org.paloma.plottwist.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/personas")
public class PersonasController {
    @Autowired
    private PersonaService servicePersona;

    @GetMapping("/mostrarDestacados")
    public List<Metraje> mostrarDestacados(int cantidad, String idPersona) {
        return servicePersona.metrajesDestacados(cantidad, idPersona);
    }
    
}
