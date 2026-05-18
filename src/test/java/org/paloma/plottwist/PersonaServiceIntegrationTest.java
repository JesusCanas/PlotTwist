package org.paloma.plottwist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.paloma.plottwist.model.Metraje;
import org.paloma.plottwist.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonaServiceIntegrationTest {

    @Autowired
    private PersonaService personaService;

    @Test
    @DisplayName("Debe devolver una lista vacía si el ID de la persona no existe en el dataset de la BD")
    void testMetrajesDestacadosPersonaNoEncontrada() {
        String idInexistente = "persona_id_completamente_inventado_999";
        List<Metraje> resultado = personaService.metrajesDestacados(5, idInexistente);

        assertNotNull(resultado, "El resultado nunca debe ser null");
        assertTrue(resultado.isEmpty(), "Debe retornar una lista vacía al no encontrar la persona");
    }

    @Test
    @DisplayName("Debe consultar los metrajes de una persona existente en Atlas y validar de forma tolerante")
    void testMetrajesDestacadosPersonaEncontrada() {
        String idPersonaExistente = "persona_Christopher_Nolan"; 
        int cantidadPorTipo = 2;

        List<Metraje> resultado = personaService.metrajesDestacados(cantidadPorTipo, idPersonaExistente);

        // Assert: Comprobación de que el servicio responde y devuelve una estructura correcta
        assertNotNull(resultado, "La lista devuelta por el servicio no debe ser null");
        
        // Validación segura del límite combinado
        assertTrue(resultado.size() <= (cantidadPorTipo * 2), 
                "La lista devuelta no debe superar el límite combinado estipulado");
    }
}