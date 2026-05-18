package org.paloma.plottwist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.paloma.plottwist.model.Metraje;
import org.paloma.plottwist.model.Pelicula;
import org.paloma.plottwist.model.Serie;
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
    void testMetrajesDestacados_PersonaNoEncontrada() {
        // Act: Consultar un ID que sabemos con total certeza que no existe en tu Atlas
        String idInexistente = "persona_id_completamente_inventado_999";
        List<Metraje> resultado = personaService.metrajesDestacados(5, idInexistente);

        // Assert: Validar que el flujo de guarda funciona correctamente sin lanzar excepciones
        assertNotNull(resultado, "El resultado nunca debe ser null");
        assertTrue(resultado.isEmpty(), "Debe retornar una lista vacía al no encontrar la persona");
    }

    @Test
    @DisplayName("Debe consultar los metrajes de una persona existente en Atlas y validar límites y orden dinámicamente")
    void testMetrajesDestacados_PersonaEncontrada() {
        // Arrange: Usamos un ID real de los datos que nos has facilitado (ej. Christopher Nolan)
        String idPersonaExistente = "persona_Christopher_Nolan"; 
        int cantidadPorTipo = 2;

        // Act: Realizar la consulta de lectura a la base de datos real a través del servicio
        List<Metraje> resultado = personaService.metrajesDestacados(cantidadPorTipo, idPersonaExistente);

        // Assert: Verificaciones basadas exclusivamente en los datos devueltos
        assertNotNull(resultado, "La lista devuelta por el servicio no debe ser null");
        
        // Validación del límite de la Query: Como máximo debe traer (cantidadPorTipo * 2) elementos
        assertTrue(resultado.size() <= (cantidadPorTipo * 2), 
                "La lista devuelta no debe superar el límite combinado estipulado");

        // Validación dinámica del orden (Regla de negocio del Javadoc):
        // "SIEMPRE se devuelve primero películas y luego series"
        if (!resultado.isEmpty()) {
            boolean seEncontroSerie = false;
            
            for (Metraje metraje : resultado) {
                if (metraje instanceof Serie) {
                    // Marcamos que a partir de aquí ya han empezado a aparecer series
                    seEncontroSerie = true;
                } else if (metraje instanceof Pelicula) {
                    // Si ya se había encontrado una serie previamente y ahora aparece una película, el orden es incorrecto
                    assertFalse(seEncontroSerie, 
                            "Error de ordenación: Se ha detectado una Película posicionada detrás de una Serie.");
                }
            }
        }
    }
}