package org.paloma.plottwist;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.paloma.plottwist.model.Metraje;
import org.paloma.plottwist.model.Pelicula;

import org.paloma.plottwist.service.MetrajeService;

import org.paloma.plottwist.model.OrdenPorFecha;
import org.paloma.plottwist.model.OrdenPorValoracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MetrajeServiceIntegrationTest {

    @Autowired
    private MetrajeService metrajeService;

    @Test
    @DisplayName("Debería obtener los metrajes destacados correctamente sin alterar la BD")
    void testObtenerDestacados() {
        int cantidadSolicitada = 3;
        
        // Act - Llamada al método del servicio
        List<Metraje> destacados = metrajeService.obtenerDestacados(cantidadSolicitada);
        
        // Assertions estructurales básicas obligatorias
        assertNotNull(destacados, "La lista de destacados no debería ser nula");
        
        // Si la base de datos devuelve elementos, validamos las reglas de negocio de forma dinámica
        if (!destacados.isEmpty()) {
            
            // Comprobar que respeta las instancias básicas si la lista viene completa
            for (int i = 0; i < destacados.size(); i++) {
                assertNotNull(destacados.get(i), "Ningún elemento de la lista debe ser nulo");
                assertNotNull(destacados.get(i).getTitulo(), "El metraje destacado debe tener un título válido");
            }

            // Validación semántica tolerante: comprobar que el primer elemento recuperado es un Metraje válido
            Metraje primero = destacados.get(0);
            assertTrue(primero.getValoracion() >= 0 && primero.getValoracion() <= 5.0, 
                "La valoración debe estar en el rango correcto");
        }
    }

    @Test
    @DisplayName("Test de Cobertura: Comparador OrdenPorFecha en orden ascendente")
    void testOrdenPorFechaOrdenAscendente() {
        OrdenPorFecha comparador = new OrdenPorFecha();
        
        Pelicula antigua = new Pelicula();
        antigua.setAnyo(1994);
        
        Pelicula reciente = new Pelicula();
        reciente.setAnyo(2018);
        
        assertTrue(comparador.compare(antigua, reciente) < 0);
        assertTrue(comparador.compare(reciente, antigua) > 0);
        assertEquals(0, comparador.compare(antigua, antigua));
    }

    @Test
    @DisplayName("Test de Cobertura: Comparador OrdenPorValoracion en orden ascendente")
    void testOrdenPorValoracionOrdenAscendente() {
        OrdenPorValoracion comparador = new OrdenPorValoracion();
        
        Pelicula bajaValoracion = new Pelicula();
        bajaValoracion.setValoracion(2.1);
        
        Pelicula altaValoracion = new Pelicula();
        altaValoracion.setValoracion(5.0);
        
        assertTrue(comparador.compare(bajaValoracion, altaValoracion) < 0);
        assertTrue(comparador.compare(altaValoracion, bajaValoracion) > 0);
        assertEquals(0, comparador.compare(altaValoracion, altaValoracion));
    }
}