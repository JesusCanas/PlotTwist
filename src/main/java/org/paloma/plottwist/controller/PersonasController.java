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

    /**
     * Obtiene los detalles de una persona y sus metrajes asociados.
     * Delegado al servicio de persona, que realiza la consulta en la colección
     * de personas y devuelve la información completa para el identificador dado.
     *
     * @param cantidad Número de metrajes asociados a devolver junto con la persona
     * @param idPersona Identificador de la persona a consultar
     * @return Objeto Persona con los detalles completos y sus metrajes
     */
    @GetMapping("/obtenerDetalles")
    public Persona obtenerDetalles(int cantidad, String idPersona) {
        return servicePersona.obtenerDetalles(cantidad, idPersona);
    }
    
}
