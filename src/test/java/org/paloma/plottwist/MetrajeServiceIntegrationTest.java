package org.paloma.plottwist;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.Metraje;
import org.paloma.plottwist.model.Pelicula;
import org.paloma.plottwist.model.Persona;
import org.paloma.plottwist.model.Serie;
import org.paloma.plottwist.repository.PeliculaRepository;
import org.paloma.plottwist.repository.PersonaRepository;
import org.paloma.plottwist.repository.SerieRepository;
import org.paloma.plottwist.service.MetrajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
public class MetrajeServiceIntegrationTest {

    @Autowired
    private MetrajeService metrajeService;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @AfterEach
    public void tearDown() {
        // Limpiar datos de test después de cada test
        peliculaRepository.deleteAll();
        serieRepository.deleteAll();
        personaRepository.deleteAll();
    }

    @Test
    public void testObtenerMetrajesFiltradosPelicula() {
        // Arrange
        Pelicula pelicula = new Pelicula("Test Movie", 2020, Arrays.asList(Genero.ACCION), "Sinopsis", "url", "dir1", 4.5, Arrays.asList("act1"), 120);
        peliculaRepository.save(pelicula);

        // Act
        List<Pelicula> result = metrajeService.obtenerMetrajesFiltrados(Pelicula.class, "Test", null, null, null);

        // Assert
        assertEquals(1, result.size());
        assertEquals("Test Movie", result.get(0).getTitulo());
    }

    @Test
    public void testObtenerDestacados() {
        // Arrange
        Pelicula pelicula = new Pelicula("Top Movie", 2020, Arrays.asList(Genero.ACCION), "Sinopsis", "url", "dir1", 5.0, Arrays.asList("act1"), 120);
        Serie serie = new Serie("Top Serie", 2021, Arrays.asList(Genero.DRAMA), "Sinopsis", "url", "dir2", 4.8, Arrays.asList("act2"), 2, 20, 45, org.paloma.plottwist.model.Estado.FINALIZADA);
        peliculaRepository.save(pelicula);
        serieRepository.save(serie);

        // Act
        List<Metraje> result = metrajeService.obtenerDestacados(2);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(m -> m.getValoracion() == 5.0));
        assertTrue(result.stream().anyMatch(m -> m.getValoracion() == 4.8));
    }

    @Test
    public void testObtenerDetallesPelicula() {
        // Arrange
        Pelicula pelicula = new Pelicula("Detail Movie", 2020, Arrays.asList(Genero.ACCION), "Sinopsis", "url", "dir1", 4.0, Arrays.asList("act1"), 120);
        Pelicula saved = peliculaRepository.save(pelicula);

        // Act
        Metraje result = metrajeService.obtenerDetalles(saved.getId());

        // Assert
        assertNotNull(result);
        assertEquals("Detail Movie", result.getTitulo());
    }

    @Test
    public void testObtenerDetallesSerie() {
        // Arrange
        Serie serie = new Serie("Detail Serie", 2021, Arrays.asList(Genero.DRAMA), "Sinopsis", "url", "dir1", 4.0, Arrays.asList("act1"), 1, 10, 50, org.paloma.plottwist.model.Estado.EMISION);
        Serie saved = serieRepository.save(serie);

        // Act
        Metraje result = metrajeService.obtenerDetalles(saved.getId());

        // Assert
        assertNotNull(result);
        assertEquals("Detail Serie", result.getTitulo());
    }

    @Test
    public void testHidratarMetraje() {
        // Arrange
        Persona director = new Persona("Director", "Name", "Bio", java.time.LocalDate.of(1970, 1, 1), "USA", Arrays.asList());
        Persona actor = new Persona("Actor", "Name", "Bio", java.time.LocalDate.of(1980, 1, 1), "USA", Arrays.asList());
        Persona savedDirector = personaRepository.save(director);
        Persona savedActor = personaRepository.save(actor);

        Pelicula pelicula = new Pelicula("Hydrate Movie", 2020, Arrays.asList(Genero.ACCION), "Sinopsis", "url", savedDirector.getId(), 4.0, Arrays.asList(savedActor.getId()), 120);

        // Act
        metrajeService.hidratarMetraje(pelicula);

        // Assert
        assertNotNull(pelicula.getDirector());
        assertEquals("Director", pelicula.getDirector().getNombre());
        assertEquals(1, pelicula.getActores().size());
        assertEquals("Actor", pelicula.getActores().get(0).getNombre());
    }
}