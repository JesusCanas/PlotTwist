package org.paloma.plottwist.controller;

import java.util.List;

import org.paloma.plottwist.model.Metraje;
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
     * Obtiene los metrajes más destacados de una persona específica.
     * Retorna una cantidad equilibrada de películas y series en las que ha participado la persona
     * (como actor o director).
     * Este método es útil para mostrar los trabajos más importantes de un actor o director
     * en su página de perfil.
     * 
     * SIEMPRE se devuelve primero películas y luego series
     * 
     * @param cantidad Número de películas y de series a retornar de la persona
     * @param idPersona ID de la persona (actor o director) de la que se desean obtener los metrajes
     * @return Lista con los metrajes más destacados de la persona, películas y series combinadas
     */
    @GetMapping("/mostrarDestacados")
    public List<Metraje> mostrarDestacados(int cantidad, String idPersona) {
        return servicePersona.metrajesDestacados(cantidad, idPersona);
    }
    
}
