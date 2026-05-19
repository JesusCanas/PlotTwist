package org.paloma.plottwist;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.paloma.plottwist.model.Metraje;
import org.paloma.plottwist.model.Pelicula;
import org.paloma.plottwist.model.Serie;

import org.paloma.plottwist.service.MetrajeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Clase MetrajeServiceIntegrationTest.
 * Esta clase contiene las pruebas de integración dinámicas para validar que
 * los métodos del servicio MetrajeService funcionen correctamente, interactuando con la base
 * de datos en la nube MongoDB Atlas sin alterar su contenido.
 * 
 * @author AdrianStephano
 * @version 1.0
 */
@SpringBootTest
public class MetrajeServiceIntegrationTest {

    @Autowired
    private MetrajeService metrajeService;

    /**
     * Este test prueba el método obtenerDestacados.
     * Verifica que los datos devueltos desde MongoDB Atlas
     * cumplan con las restricciones de negocio de la aplicación, controlando
     * que los atributos de cada clase no sean nulos.
     */
    @Test
    public void testObtenerDestacados() {
        // Debería obtener los metrajes destacados correctamente sin alterar la BD
        int cantidadSolicitada = 3;
        
        // Act - Llamada al método del servicio
        List<Metraje> destacados = metrajeService.obtenerDestacados(cantidadSolicitada);
        
        // Assertions estructurales básicas obligatorias
        assertNotNull(destacados, "La lista de destacados no debería ser nula");
        
        // Si la base de datos devuelve elementos, validamos las reglas de negocio de forma dinámica
        if (!destacados.isEmpty()) {
            
            // Comprobar que respeta las instancias básicas si la lista viene completa
            for (int i = 0; i < destacados.size(); i++) {
                assertNotNull(destacados.get(i), "Ningún elemento de la lista debe ser nulo en el índice " + i);
                assertNotNull(destacados.get(i).getTitulo(), "El metraje destacado debe tener un título válido en el índice " + i);
            }

            // Validación semántica tolerante: comprobar que el primer elemento recuperado es un Metraje válido
            Metraje primero = destacados.get(0);
            assertTrue(primero.getValoracion() >= 0 && primero.getValoracion() <= 5.0, 
                "La valoración obtenida (" + primero.getValoracion() + ") se sale del rango permitido [0.0, 5.0]");
        }
    }
}