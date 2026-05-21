package org.paloma.plottwist.controller;

import org.paloma.plottwist.model.Persona;
import org.paloma.plottwist.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para gestionar operaciones relacionadas con personas (actores y directores).
 * Proporciona endpoints para obtener información de personas y sus metrajes asociados.
 * 
 * @author MiguelSg77
 * @version 1.0
 */
@RestController
@RequestMapping("/personas")
public class PersonasController {
    
    @Autowired
    private PersonaService servicePersona;

    @GetMapping("/obtenerDetalles")
    public Persona obtenerDetalles(int cantidad, String idPersona) {
        return servicePersona.obtenerDetalles(cantidad, idPersona);
    }
    
}
