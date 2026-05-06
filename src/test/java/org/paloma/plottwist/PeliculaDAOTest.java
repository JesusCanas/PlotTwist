package org.paloma.plottwist;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.paloma.plottwist.dao.PeliculasDAO;
import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.Pelicula;

import java.time.Year;

import java.util.List;

public class PeliculaDAOTest {

    private PeliculasDAO dao;

    @BeforeEach
    void inicializarDAO() {
        // Inicializamos el DAO antes de cada test.
        // Como el constructor rellena la lista, tendremos 10 películas listas.
        dao = new PeliculasDAO();
    }

    @Test
    void testObtenerPeliculasTodas() {
        List<Pelicula> todas = dao.obtenerPeliculasTodas();
        assertEquals(10, todas.size(), "Películas esperadas: 10");
    }

    @Test
    void testObtenerPeliculasFiltradas() {
        // Filtrar por nombre
        List<Pelicula> filtradasPorNombre = dao.obtenerPeliculasFiltradas("Inception", null, null, null);
        
        // Verificamos que se encuentre exactamente la película buscada
        assertFalse(filtradasPorNombre.isEmpty(), "Debería encontrar la película 'Inception'.");
        assertEquals("Inception", filtradasPorNombre.get(0).getTitulo());

        // Filtrar por género
        List<Pelicula> filtradasPorGenero = dao.obtenerPeliculasFiltradas(null, List.of(Genero.ACCION), null, null);

        // Verificamos que la lista no sea nula y contenga elementos
        assertNotNull(filtradasPorGenero);
        assertFalse(filtradasPorGenero.isEmpty(), "La lista de pelis de acción no debería estar vacía.");

        // Comprobamos que realmente son del género esperado
        for (Pelicula p : filtradasPorGenero) {
            assertTrue(p.getGeneros().contains(Genero.ACCION), "Se encontró una película que no es de acción: " + p.getTitulo());
        }

        // Filtrar por año
        List<Pelicula> filtradasPorAnio = dao.obtenerPeliculasFiltradas(null, null, Year.of(1994), null);
        
        // Verificamos que la película sea del año correcto y tenga el título esperado
        assertEquals(1, filtradasPorAnio.size(), "Debería haber exactamente 1 película de 1994.");
        assertEquals("The Shawshank Redemption", filtradasPorAnio.get(0).getTitulo());

        // Filtrar por valoración
        List<Pelicula> filtradasPorValoracion = dao.obtenerPeliculasFiltradas(null, null, null, 9.0);

        // Comprobamos que hay resultados
        assertNotNull(filtradasPorValoracion);
        assertFalse(filtradasPorValoracion.isEmpty(), "Debería haber películas con nota +9.0.");

        // Validamos que todas cumplan el rango
        for (Pelicula p : filtradasPorValoracion) {
            double v = p.getValoracion();
            assertTrue(v >= 9.0 && v < 10.0, "Valoración fuera de rango (9-10): " + v);
        }
    }

    @Test
    void testObtenerDestacados() {
        int cantidad = 3;
        List<Pelicula> destacados = dao.obtenerDestacados(cantidad);

        assertEquals(cantidad, destacados.size(), "Debería devolver la cantidad solicitada de películas destacadas. En este caso, " + cantidad);

        // El Padrino (9.2) debería ser la primera, al ser la mejor valorada.
        assertEquals("The Godfather", destacados.get(0).getTitulo());

        // Verificamos que las valoraciones estén en orden descendente.
        assertTrue(destacados.get(0).getValoracion() >= destacados.get(1).getValoracion(), "Las valoraciones no están en orden descendente.");
    }

    @Test
    void testObtenerPeliculaPorId() {
        String idExistente = "507f1f77bcf86cd799439033"; // ID de prueba, este es el ID de la peli "The Godfather"
        Pelicula pelicula = dao.obtenerPeliculaPorId(idExistente);

        // Verificamos que se haya encontrado la película y que el título sea correcto.
        assertNotNull(pelicula, "Debería encontrar la película con el ID existente.");
        assertEquals("The Godfather", pelicula.getTitulo(), "El título debería ser 'The Godfather'.");

        // Prueba con un ID que no existe
        String idInexistente = "EsteIDNoExiste"; // ID que no existe
        Pelicula peliculaNoEncontrada = dao.obtenerPeliculaPorId(idInexistente);
        assertNull(peliculaNoEncontrada, "Debería devolver null para un ID que no existe.");
    }

}