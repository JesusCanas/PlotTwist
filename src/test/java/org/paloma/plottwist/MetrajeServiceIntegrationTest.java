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
        Pelicula pelicula = new Pelicula("Test Movie " + suffix, 2020, Arrays.asList(Genero.ACCION), "Sinopsis", "url",
                "dir1", 4.5, Arrays.asList("act1"), 120);
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
        // Arrange: Crear listas usando sintaxis clásica o constructor directo
        List<Genero> generosAccion = new ArrayList<>();
        generosAccion.add(Genero.ACCION);

        List<Genero> generosDrama = new ArrayList<>();
        generosDrama.add(Genero.DRAMA);

        List<String> actoresPelicula = new ArrayList<>();
        actoresPelicula.add("act1");

        List<String> actoresSerie = new ArrayList<>();
        actoresSerie.add("act2");

        // Guardar 2 películas para que el repositorio pueda devolver hasta 2
        Pelicula pelicula1 = new Pelicula("Top Movie 1", 2020, generosAccion, "Sinopsis", "url", "dir1", 1000.0,
                actoresPelicula, 120);
        Pelicula pelicula2 = new Pelicula("Top Movie 2", 2022, generosAccion, "Sinopsis", "url", "dir3", 950.0,
                actoresPelicula, 110);

        // Guardar 2 series para que el repositorio pueda devolver hasta 2
        Serie serie1 = new Serie("Top Serie 1", 2021, generosDrama, "Sinopsis", "url", "dir2", 999.0, actoresSerie, 2,
                20, 45, org.paloma.plottwist.model.Estado.FINALIZADA);
        Serie serie2 = new Serie("Top Serie 2", 2023, generosDrama, "Sinopsis", "url", "dir4", 899.0, actoresSerie, 1,
                10, 50, org.paloma.plottwist.model.Estado.FINALIZADA);

        peliculaIds.add(peliculaRepository.save(pelicula1).getId());
        peliculaIds.add(peliculaRepository.save(pelicula2).getId());
        serieIds.add(serieRepository.save(serie1).getId());
        serieIds.add(serieRepository.save(serie2).getId());

        // Act
        List<Metraje> result = metrajeService.obtenerDestacados(2);

        // Assert: Verificación de tamaño
        assertEquals(4, result.size(), "El tamaño de la lista de destacados debería ser 4 (2 películas y 2 series)");

        // Assert: Verificación de contenido usando un bucle for clásico
        boolean tieneValoracion1000 = false;
        boolean tieneValoracion999 = false;

        for (int i = 0; i < result.size(); i++) {
            Metraje m = result.get(i);
            if (m.getValoracion() == 1000.0) {
                tieneValoracion1000 = true;
            }
            if (m.getValoracion() == 999.0) {
                tieneValoracion999 = true;
            }
        }

        assertTrue(tieneValoracion1000, "La lista de destacados debería contener una película con valoración 1000.0");
        assertTrue(tieneValoracion999, "La lista de destacados debería contener una serie con valoración 999.0");
    }

    @Test
    public void testHidratarMetraje() {
        // Arrange
        Persona director = new Persona("Director", "Name", "Bio", java.time.LocalDate.of(1970, 1, 1), "USA",
                Arrays.asList());
        Persona actor = new Persona("Actor", "Name", "Bio", java.time.LocalDate.of(1980, 1, 1), "USA", Arrays.asList());
        Persona savedDirector = personaRepository.save(director);
        Persona savedActor = personaRepository.save(actor);
        personaIds.add(savedDirector.getId());
        personaIds.add(savedActor.getId());

        Pelicula pelicula = new Pelicula("Hydrate Movie", 2020, Arrays.asList(Genero.ACCION), "Sinopsis", "url",
                savedDirector.getId(), 4.0, Arrays.asList(savedActor.getId()), 120);

        // Act
        metrajeService.hidratarMetraje(pelicula);

        // Assert
        assertNotNull(pelicula.getDirector());
        assertEquals("Director", pelicula.getDirector().getNombre());
        assertEquals(1, pelicula.getActores().size());
        assertEquals("Actor", pelicula.getActores().get(0).getNombre());
    }
}