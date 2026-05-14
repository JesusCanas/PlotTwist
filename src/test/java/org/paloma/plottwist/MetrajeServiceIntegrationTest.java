package org.paloma.plottwist;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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

    private final List<String> peliculaIds = new ArrayList<>();
    private final List<String> serieIds = new ArrayList<>();
    private final List<String> personaIds = new ArrayList<>();

    @AfterEach
    public void tearDown() {
        // Limpiar solo los datos creados por el test
        for (String id : peliculaIds) {
            peliculaRepository.deleteById(id);
        }
        for (String id : serieIds) {
            serieRepository.deleteById(id);
        }
        for (String id : personaIds) {
            personaRepository.deleteById(id);
        }
        peliculaIds.clear();
        serieIds.clear();
        personaIds.clear();
    }

    @Test
    public void testObtenerMetrajesFiltradosPelicula() {
        // Arrange
        String suffix = UUID.randomUUID().toString();
        Pelicula pelicula = new Pelicula("Test Movie " + suffix, 2020, Arrays.asList(Genero.ACCION), "Sinopsis", "url", "dir1", 4.5, Arrays.asList("act1"), 120);
        Pelicula savedPelicula = peliculaRepository.save(pelicula);
        peliculaIds.add(savedPelicula.getId());

        // Act
        List<Pelicula> result = metrajeService.obtenerMetrajesFiltrados(Pelicula.class, suffix, null, null, null);

        // Assert
        assertEquals(1, result.size());
        assertEquals("Test Movie " + suffix, result.get(0).getTitulo());
    }

    @Test
    public void testObtenerDestacados() {
        // Arrange
        Pelicula pelicula = new Pelicula("Top Movie", 2020, Arrays.asList(Genero.ACCION), "Sinopsis", "url", "dir1", 1000.0, Arrays.asList("act1"), 120);
        Serie serie = new Serie("Top Serie", 2021, Arrays.asList(Genero.DRAMA), "Sinopsis", "url", "dir2", 999.0, Arrays.asList("act2"), 2, 20, 45, org.paloma.plottwist.model.Estado.FINALIZADA);
        Pelicula savedPelicula = peliculaRepository.save(pelicula);
        Serie savedSerie = serieRepository.save(serie);
        peliculaIds.add(savedPelicula.getId());
        serieIds.add(savedSerie.getId());

        // Act
        List<Metraje> result = metrajeService.obtenerDestacados(2);

        // Assert
        assertEquals(4, result.size());
        assertTrue(result.stream().anyMatch(m -> m.getValoracion() == 1000.0));
        assertTrue(result.stream().anyMatch(m -> m.getValoracion() == 999.0));
    }

    @Test
    public void testHidratarMetraje() {
        // Arrange
        Persona director = new Persona("Director", "Name", "Bio", java.time.LocalDate.of(1970, 1, 1), "USA", Arrays.asList());
        Persona actor = new Persona("Actor", "Name", "Bio", java.time.LocalDate.of(1980, 1, 1), "USA", Arrays.asList());
        Persona savedDirector = personaRepository.save(director);
        Persona savedActor = personaRepository.save(actor);
        personaIds.add(savedDirector.getId());
        personaIds.add(savedActor.getId());

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