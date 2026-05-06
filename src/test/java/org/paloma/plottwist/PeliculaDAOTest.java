package org.paloma.plottwist;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.paloma.plottwist.dao.PeliculasDAO;
import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.Pelicula;

import java.time.Year;

import java.util.List;
import java.util.ArrayList;

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
        List<Genero> generosAFiltrar = new ArrayList<>();
        generosAFiltrar.add(Genero.ACCION);

        List<Pelicula> filtradasPorGenero = dao.obtenerPeliculasFiltradas(null, generosAFiltrar, null, null);

        // Verificamos que la lista no sea nula y contenga elementos
        assertNotNull(filtradasPorGenero);
        assertFalse(filtradasPorGenero.isEmpty(), "La lista de pelis de acción no debería estar vacía.");

        // Comprobamos que realmente son del género esperado
        for (Pelicula p : filtradasPorGenero) {
            assertTrue(p.getGeneros().contains(Genero.ACCION),
                    "Se encontró una película que no es de acción: " + p.getTitulo());
        }

        // Filtrar por año
        Year anyoBusqueda = Year.of(1994);
        List<Pelicula> filtradasPorAnio = dao.obtenerPeliculasFiltradas(null, null, anyoBusqueda, null);

        // Verificamos que se hayan encontrado películas del año esperado
        assertNotNull(filtradasPorAnio, "La lista de películas filtradas por año no debería ser nula.");
        assertFalse(filtradasPorAnio.isEmpty(), "Debería encontrar al menos una película de 1994.");
        for (Pelicula p : filtradasPorAnio) {
            assertEquals(anyoBusqueda, p.getAnyo(), "La película " + p.getTitulo() + " no es del año esperado.");
        }

        // Filtrar por valoración
        List<Pelicula> filtradasPorValoracion = dao.obtenerPeliculasFiltradas(null, null, null, 9.0);

        // Comprobamos que hay resultados
        assertNotNull(filtradasPorValoracion);
        assertFalse(filtradasPorValoracion.isEmpty(), "Debería haber películas con nota +9.0.");

        // Verificamos que los resultados no tengan una valoración fuera de rango (10.0
        // o más)
        for (Pelicula p : filtradasPorValoracion) {
            double v = p.getValoracion();
            assertTrue(v >= 1.0 && v < 10.0, "Valoración fuera de rango: " + v);
        }
    }

    @Test
    void testObtenerDestacados() {
        int cantidad = 3;
        List<Pelicula> destacados = dao.obtenerDestacados(cantidad);

        // Verificamos que se devuelva la cantidad solicitada
        assertEquals(cantidad, destacados.size(),
                "Debería devolver la cantidad solicitada de películas destacadas. En este caso, " + cantidad);

        // Verificamos que estén en orden descendente recorriendo la lista
        for (int i = 0; i < destacados.size() - 1; i++) {
            double actual = destacados.get(i).getValoracion();
            double siguiente = destacados.get(i + 1).getValoracion();
            assertTrue(actual >= siguiente, "El orden de destacados no es descendente: " + actual + " < " + siguiente);
        }
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