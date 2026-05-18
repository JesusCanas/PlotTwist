package org.paloma.plottwist;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.paloma.plottwist.model.Metraje;
import org.paloma.plottwist.model.Pelicula;
import org.paloma.plottwist.model.Serie;

import org.paloma.plottwist.service.MetrajeService;

import org.paloma.plottwist.model.OrdenPorFecha;
import org.paloma.plottwist.model.OrdenPorValoracion;

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

    /**
     * Este test prueba el comparador OrdenPorFecha (en este caso, en orden ascendente).
     * Comprueba que la lógica interna del comparador ordene correctamente los metrajes
     * según su año de estreno en orden ascendente, controlando también coincidencias entre fechas.
     */
    @Test
    public void testOrdenPorFechaAscendente() {
        // Test de Cobertura: Comparador OrdenPorFecha en orden ascendente
        OrdenPorFecha comparador = new OrdenPorFecha();
        
        Pelicula antigua = new Pelicula();
        antigua.setAnyo(1994);
        
        Pelicula reciente = new Pelicula();
        reciente.setAnyo(2018);
        
        assertTrue(comparador.compare(antigua, reciente) < 0, "El año 1994 debería considerarse menor que 2018");
        assertTrue(comparador.compare(reciente, antigua) > 0, "El año 2018 debería considerarse mayor que 1994");
        assertEquals(0, comparador.compare(antigua, antigua), "Dos metrajes con el mismo año de estreno deben devolver 0");
    }

    /**
     * Este test prueba el comparador OrdenPorValoracion en orden ascendente.
     * Comprueba que la lógica interna del comparador ordene de forma ascendente
     * las películas y series según su valoración.
     */
    @Test
    public void testOrdenPorValoracionAscendente() {
        // Test de Cobertura: Comparador OrdenPorValoracion en orden ascendente
        OrdenPorValoracion comparador = new OrdenPorValoracion();
        
        Pelicula bajaValoracion = new Pelicula();
        bajaValoracion.setValoracion(2.1);
        
        Pelicula altaValoracion = new Pelicula();
        altaValoracion.setValoracion(5.0);
        
        assertTrue(comparador.compare(bajaValoracion, altaValoracion) < 0, "La valoración 2.1 debería ser menor que 5.0");
        assertTrue(comparador.compare(altaValoracion, bajaValoracion) > 0, "La valoración 5.0 debería ser mayor que 2.1");
        assertEquals(0, comparador.compare(altaValoracion, altaValoracion), "Dos metrajes con la misma valoración deben devolver 0");
    }
}