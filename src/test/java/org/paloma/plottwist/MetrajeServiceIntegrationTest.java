package org.paloma.plottwist;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.paloma.plottwist.model.Metraje;
import org.paloma.plottwist.model.Pelicula;
import org.paloma.plottwist.model.Serie;
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
    public void testObtenerDestacados() {
        int cantidadSolicitada = 3;
        
        // Act - Llamada al método del servicio
        List<Metraje> destacados = metrajeService.obtenerDestacados(cantidadSolicitada);
        
        // Assertions estructurales
        assertNotNull(destacados, "La lista de destacados no debería ser nula");
        assertEquals(cantidadSolicitada * 2, destacados.size(), 
            "La lista debería contener exactamente el doble de la cantidad (n películas y n series)");
        
        // Comprobar la regla de negocio: "SIEMPRE se devolverán primero las películas y luego las series"
        for (int i = 0; i < cantidadSolicitada; i++) {
            assertTrue(destacados.get(i) instanceof Pelicula, "Los primeros elementos deben ser instancias de Pelicula");
        }
        for (int i = cantidadSolicitada; i < destacados.size(); i++) {
            assertTrue(destacados.get(i) instanceof Serie, "Los últimos elementos deben ser instancias de Serie");
        }

        // Validaciones semánticas basadas en tu base de datos real:
        // La película con mayor valoración en tu JSON es 'The Dark Knight' con un 5.0
        Pelicula topPelicula = (Pelicula) destacados.get(0);
        assertEquals(5.0, topPelicula.getValoracion(), 0.01, "La película más destacada debería tener valoración 5.0");
        assertEquals("The Dark Knight", topPelicula.getTitulo(), "La película con 5.0 debería ser 'The Dark Knight'");

        // La serie con mayor valoración en tu JSON es 'Breaking Bad' con un 5.0
        Serie topSerie = (Serie) destacados.get(cantidadSolicitada);
        assertEquals(5.0, topSerie.getValoracion(), 0.01, "La serie más destacada debería tener valoración 5.0");
        assertEquals("Breaking Bad", topSerie.getTitulo(), "La serie con 5.0 debería ser 'Breaking Bad'");
    }

    @Test
    @DisplayName("Debería obtener los detalles completos de una película existente")
    public void testObtenerDetallesPeliculaExistente() {
        String idExistente = "pelicula_the_dark_knight_2008";
        
        // Act
        Metraje metraje = metrajeService.obtenerDetalles(idExistente);
        
        // Assert
        assertNotNull(metraje, "El metraje con ID real debería ser encontrado");
        assertTrue(metraje instanceof Pelicula, "El metraje recuperado debería ser una Pelicula");
        
        Pelicula pelicula = (Pelicula) metraje;
        assertEquals("The Dark Knight", pelicula.getTitulo());
        assertEquals(2008, pelicula.getAnyo());
        assertEquals(5.0, pelicula.getValoracion(), 0.01);
        assertEquals(152, pelicula.getDuracion(), "La duración debería coincidir con los 152 minutos de la BD");
    }

    @Test
    @DisplayName("Debería obtener los detalles completos de una serie existente")
    public void testObtenerDetallesSerieExistente() {
        String idExistente = "serie_breaking_bad_2008";
        
        // Act
        Metraje metraje = metrajeService.obtenerDetalles(idExistente);
        
        // Assert
        assertNotNull(metraje, "La serie con ID real debería ser encontrada");
        assertTrue(metraje instanceof Serie, "El metraje recuperado debería ser una Serie");
        
        Serie serie = (Serie) metraje;
        assertEquals("Breaking Bad", serie.getTitulo());
        assertEquals(2008, serie.getAnyo());
        assertEquals(5, serie.getNumTemporadas(), "Debería tener 5 temporadas según la BD");
        assertEquals(62, serie.getNumEpisodios(), "Debería tener 62 episodios según la BD");
    }

    @Test
    @DisplayName("Debería devolver null al buscar un ID de metraje que no existe")
    public void testObtenerDetallesInexistente() {
        String idFalso = "id_inexistente_de_prueba_segura";
        
        // Act
        Metraje metraje = metrajeService.obtenerDetalles(idFalso);
        
        // Assert
        assertNull(metraje, "El método debería retornar null limpiamente si el ID no existe en ninguna colección");
    }

    @Test
    @DisplayName("Test de Cobertura: Comparador OrdenPorFecha en orden ascendente")
    public void testOrdenPorFechaCoverage() {
        OrdenPorFecha comparador = new OrdenPorFecha();
        
        // Instanciamos objetos en memoria sin tocar la Base de Datos
        Pelicula antigua = new Pelicula();
        antigua.setAnyo(1994); // Pulp Fiction, por ejemplo
        
        Pelicula reciente = new Pelicula();
        reciente.setAnyo(2018); // Spider-Man, por ejemplo
        
        // Ejecuciones para cubrir todas las ramas (menor, mayor, igual)
        assertTrue(comparador.compare(antigua, reciente) < 0, "1994 debería ser menor (anterior) que 2018");
        assertTrue(comparador.compare(reciente, antigua) > 0, "2018 debería ser mayor (posterior) que 1994");
        assertEquals(0, comparador.compare(antigua, antigua), "Años idénticos deberían retornar exactamente 0");
    }

    @Test
    @DisplayName("Test de Cobertura: Comparador OrdenPorValoracion en orden ascendente")
    public void testOrdenPorValoracionCoverage() {
        OrdenPorValoracion comparador = new OrdenPorValoracion();
        
        // Instanciamos objetos en memoria sin tocar la Base de Datos
        Pelicula bajaValoracion = new Pelicula();
        bajaValoracion.setValoracion(2.1); // Lionheart
        
        Pelicula altaValoracion = new Pelicula();
        altaValoracion.setValoracion(5.0); // The Dark Knight
        
        // Ejecuciones para cubrir todas las ramas (menor, mayor, igual)
        assertTrue(comparador.compare(bajaValoracion, altaValoracion) < 0, "2.1 debería ser menor que 5.0");
        assertTrue(comparador.compare(altaValoracion, bajaValoracion) > 0, "5.0 debería ser mayor que 2.1");
        assertEquals(0, comparador.compare(altaValoracion, altaValoracion), "Valoraciones idénticas deben retornar 0");
    }
}