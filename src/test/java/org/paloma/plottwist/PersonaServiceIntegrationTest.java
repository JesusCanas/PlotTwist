package org.paloma.plottwist;

import org.junit.jupiter.api.Test;
import org.paloma.plottwist.model.Metraje;
import org.paloma.plottwist.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase PersonaServiceIntegrationTest.
 * Esta clase agrupa los casos de prueba de integración correspondientes al servicio
 * PersonaService, encargados de verificar las consultas dinámicas a la colección de personas
 * y sus metrajes asociados en MongoDB Atlas.
 * 
 * Nota: para evitar el uso de Mocks, este test utiliza @Autowired con 
 * @SpringBootTest, Spring levanta un entorno similar al de producción. La anotación
 * busca los componentes reales configurados en el sistema y los introduce en este test.
 * En este caso, inyecta una instancia real de 
 * PersonaService.
 * 
 * @author AdrianStephano
 * @version 1.0
 */
@SpringBootTest
class PersonaServiceIntegrationTest {

    @Autowired
    private PersonaService personaService;

    /**
     * Prueba el comportamiento del servicio ante una búsqueda con un ID inexistente.
     * Asegura que el flujo de control maneje correctamente la ausencia de coincidencias
     * devolviendo una lista inicializada pero completamente vacía.
     */
    @Test
    void testMetrajesDestacadosPersonaNoEncontrada() {
        // Debe devolver una lista vacía si el ID de la persona no existe en el dataset de la BD
        String idInexistente = "persona_id_completamente_inventado_999";
        List<Metraje> resultado = personaService.metrajesDestacados(5, idInexistente);

        assertNotNull(resultado, "El resultado devuelto por el servicio nunca debe ser null");
        assertTrue(resultado.isEmpty(), "La lista debería estar vacía al buscar una persona que no existe en el sistema");
    }

    /**
     * Prueba el método metrajesDestacados para una persona que sí existe en MongoDB Atlas.
     * Ejecuta una consulta real sobre un ID conocido y comprueba que la estructura combinada
     * resultante (películas y series) respete los criterios de paginación o límites estipulados.
     */
    @Test
    void testMetrajesDestacadosPersonaEncontrada() {
        // Debe consultar los metrajes de una persona existente en Atlas y validar de forma tolerante
        String idPersonaExistente = "persona_Christopher_Nolan"; 
        int cantidadPorTipo = 2;

        List<Metraje> resultado = personaService.metrajesDestacados(cantidadPorTipo, idPersonaExistente);

        // Assert: Comprobación de que el servicio responde y devuelve una estructura correcta
        assertNotNull(resultado, "La lista devuelta por el servicio no debe ser null al consultar un ID existente");
        
        // Validación segura del límite combinado
        assertTrue(resultado.size() <= (cantidadPorTipo * 2), 
                "La lista devuelta superó el tamaño máximo esperado. Tamaño obtenido: " + resultado.size());
    }
}