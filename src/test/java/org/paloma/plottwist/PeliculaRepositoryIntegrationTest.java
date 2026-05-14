package org.paloma.plottwist;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.paloma.plottwist.model.Genero;
import org.paloma.plottwist.model.Pelicula;
import org.paloma.plottwist.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
public class PeliculaRepositoryIntegrationTest {

    @Autowired
    private PeliculaRepository peliculaRepository;

    private final List<String> peliculaIds = new ArrayList<>();

    @AfterEach
    public void tearDown() {
        // Limpiar solo los datos creados por el test
        for (String id : peliculaIds) {
            peliculaRepository.deleteById(id);
        }
        peliculaIds.clear();
    }

    @Test
    public void testFindTopByOrderByValoracionDesc() {
        // Arrange
        Pelicula pelicula1 = new Pelicula("Movie 1", 2020, Arrays.asList(Genero.ACCION), "Sinopsis 1", "url1", "dir1", 1000.0, Arrays.asList("act1"), 120);
        Pelicula pelicula2 = new Pelicula("Movie 2", 2021, Arrays.asList(Genero.DRAMA), "Sinopsis 2", "url2", "dir2", 999.0, Arrays.asList("act2"), 110);
        Pelicula saved1 = peliculaRepository.save(pelicula1);
        Pelicula saved2 = peliculaRepository.save(pelicula2);
        peliculaIds.add(saved1.getId());
        peliculaIds.add(saved2.getId());

        // Act
        List<Pelicula> result = peliculaRepository.findTopByOrderByValoracionDesc(PageRequest.of(0, 2));

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(m -> "Movie 1".equals(m.getTitulo()) && m.getValoracion() == 1000.0));
        assertTrue(result.stream().anyMatch(m -> "Movie 2".equals(m.getTitulo()) && m.getValoracion() == 999.0));
    }

    @Test
    public void testFindAll() {
        // Arrange
        Pelicula pelicula = new Pelicula("Test Movie", 2020, Arrays.asList(Genero.ACCION), "Sinopsis", "url", "dir1", 4.0, Arrays.asList("act1"), 120);
        Pelicula saved = peliculaRepository.save(pelicula);
        peliculaIds.add(saved.getId());

        // Act
        List<Pelicula> result = peliculaRepository.findAll();

        // Assert
        assertTrue(result.stream().anyMatch(m -> "Test Movie".equals(m.getTitulo())));
    }

    @Test
    public void testFindById() {
        // Arrange
        Pelicula pelicula = new Pelicula("Find Movie", 2020, Arrays.asList(Genero.ACCION), "Sinopsis", "url", "dir1", 4.0, Arrays.asList("act1"), 120);
        Pelicula saved = peliculaRepository.save(pelicula);
        peliculaIds.add(saved.getId());

        // Act
        java.util.Optional<Pelicula> result = peliculaRepository.findById(saved.getId());

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Find Movie", result.get().getTitulo());
    }

    @Test
    public void testSave() {
        // Arrange
        Pelicula pelicula = new Pelicula("Save Movie", 2020, Arrays.asList(Genero.ACCION), "Sinopsis", "url", "dir1", 4.0, Arrays.asList("act1"), 120);

        // Act
        Pelicula saved = peliculaRepository.save(pelicula);
        peliculaIds.add(saved.getId());

        // Assert
        assertNotNull(saved.getId());
        assertEquals("Save Movie", saved.getTitulo());
    }

    @Test
    public void testDeleteById() {
        // Arrange
        Pelicula pelicula = new Pelicula("Delete Movie", 2020, Arrays.asList(Genero.ACCION), "Sinopsis", "url", "dir1", 4.0, Arrays.asList("act1"), 120);
        Pelicula saved = peliculaRepository.save(pelicula);
        peliculaIds.add(saved.getId());

        // Act
        peliculaRepository.deleteById(saved.getId());

        // Assert
        assertFalse(peliculaRepository.findById(saved.getId()).isPresent());
    }
}