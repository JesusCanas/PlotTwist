package org.paloma.plottwist;

import static org.junit.jupiter.api.Assertions.*;

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

    @AfterEach
    public void tearDown() {
        // Limpiar datos de test
        peliculaRepository.deleteAll();
    }

    @Test
    public void testFindTopByOrderByValoracionDesc() {
        // Arrange
        Pelicula pelicula1 = new Pelicula("Movie 1", 2020, Arrays.asList(Genero.ACCION), "Sinopsis 1", "url1", "dir1", 5.0, Arrays.asList("act1"), 120);
        Pelicula pelicula2 = new Pelicula("Movie 2", 2021, Arrays.asList(Genero.DRAMA), "Sinopsis 2", "url2", "dir2", 4.5, Arrays.asList("act2"), 110);
        peliculaRepository.save(pelicula1);
        peliculaRepository.save(pelicula2);

        // Act
        List<Pelicula> result = peliculaRepository.findTopByOrderByValoracionDesc(PageRequest.of(0, 2));

        // Assert
        assertEquals(2, result.size());
        assertEquals(5.0, result.get(0).getValoracion());
        assertEquals(4.5, result.get(1).getValoracion());
    }

    @Test
    public void testFindAll() {
        // Arrange
        Pelicula pelicula = new Pelicula("Test Movie", 2020, Arrays.asList(Genero.ACCION), "Sinopsis", "url", "dir1", 4.0, Arrays.asList("act1"), 120);
        peliculaRepository.save(pelicula);

        // Act
        List<Pelicula> result = peliculaRepository.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Test Movie", result.get(0).getTitulo());
    }

    @Test
    public void testFindById() {
        // Arrange
        Pelicula pelicula = new Pelicula("Find Movie", 2020, Arrays.asList(Genero.ACCION), "Sinopsis", "url", "dir1", 4.0, Arrays.asList("act1"), 120);
        Pelicula saved = peliculaRepository.save(pelicula);

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

        // Assert
        assertNotNull(saved.getId());
        assertEquals("Save Movie", saved.getTitulo());
    }

    @Test
    public void testDeleteById() {
        // Arrange
        Pelicula pelicula = new Pelicula("Delete Movie", 2020, Arrays.asList(Genero.ACCION), "Sinopsis", "url", "dir1", 4.0, Arrays.asList("act1"), 120);
        Pelicula saved = peliculaRepository.save(pelicula);

        // Act
        peliculaRepository.deleteById(saved.getId());

        // Assert
        assertFalse(peliculaRepository.findById(saved.getId()).isPresent());
    }
}