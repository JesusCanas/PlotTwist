package org.paloma.plottwist;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.paloma.plottwist.dao.PeliculasDAO;
import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.Pelicula;

import java.util.List;

public class PeliculaDAOTest {

    private PeliculasDAO dao;

    @BeforeEach
    void setUp() {
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
    void testFiltrarPorNombre() {
        List<Pelicula> resultado = dao.obtenerPeliculasFiltradas("Inception", null, null, null);

        assertFalse(resultado.isEmpty());
        assertTrue(resultado.get(0).getTitulo().contains("Inception"), "Título esperado: 'Inception'");
    }

    @Test
    void testFiltrarPorGenero() {
        List<Genero> generosBusqueda = List.of(Genero.CIENCIA_FICCION);
        List<Pelicula> resultado = dao.obtenerPeliculasFiltradas(null, generosBusqueda, null, null);

        // Inception, Jurassic Park y 2001 son de ciencia ficción, así que esperamos al menos esas 3.
        assertTrue(resultado.size() >= 3);
        for (Pelicula p : resultado) {
            assertTrue(p.getGeneros().contains(Genero.CIENCIA_FICCION), "Las películas devueltas deberían tener el género ciencia ficción");
        }
    }

    @Test
    void testObtenerDestacados() {
        int cantidad = 3;
        List<Pelicula> destacados = dao.obtenerDestacados(cantidad);

        assertEquals(cantidad, destacados.size(), "Debería devolver la cantidad solicitada de películas destacadas.");

        // El Padrino (9.2) debería ser la primera, al ser la mejor valorada.
        assertEquals("The Godfather", destacados.get(0).getTitulo());

        // Verificamos que las valoraciones estén en orden descendente.
        assertTrue(destacados.get(0).getValoracion() >= destacados.get(1).getValoracion());
    }

    @Test
    void testObtenerPeliculaPorIdInexistente() {
        Pelicula p = dao.obtenerPeliculaPorId("idInexistente)");
        assertNull(p, "Debería devolver un null para un ID que no existe.");
    }

}