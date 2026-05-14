package org.paloma.plottwist;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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
    public void testHidratarMetraje() {
        // Arrange
        List<String> premiosVacios = new ArrayList<>();
        Persona director = new Persona("Director", "Name", "Bio", java.time.LocalDate.of(1970, 1, 1), "USA", premiosVacios);
        Persona actor = new Persona("Actor", "Name", "Bio", java.time.LocalDate.of(1980, 1, 1), "USA", premiosVacios);
        
        Persona savedDirector = personaRepository.save(director);
        Persona savedActor = personaRepository.save(actor);
        personaIds.add(savedDirector.getId());
        personaIds.add(savedActor.getId());

        List<Genero> generos = new ArrayList<>();
        generos.add(Genero.ACCION);
        
        List<String> actoresIds = new ArrayList<>();
        actoresIds.add(savedActor.getId());

        Pelicula pelicula = new Pelicula("Hydrate Movie", 2020, generos, "Sinopsis", "url", savedDirector.getId(), 4.0, actoresIds, 120);

        // Act
        metrajeService.hidratarMetraje(pelicula);

        // Assert
        assertNotNull(pelicula.getDirector(), "El director no debería ser nulo después de hidratar");
        assertEquals("Director", pelicula.getDirector().getNombre(), "El nombre del director no coincide");
        assertEquals(1, pelicula.getActores().size(), "Debería haber un actor después de hidratar");
        assertEquals("Actor", pelicula.getActores().get(0).getNombre(), "El nombre del actor no coincide");
    }

    @Test
    public void testObtenerUnTipoMetrajes() {
        // Arrange
        List<Genero> generos = new ArrayList<>();
        generos.add(Genero.COMEDIA);
        List<String> actores = new ArrayList<>();
        actores.add("act1");

        Pelicula pelicula = new Pelicula("Pelicula Unica Tipo", 2024, generos, "Sinopsis", "url", "dir1", 3.0, actores, 90);
        Pelicula saved = peliculaRepository.save(pelicula);
        peliculaIds.add(saved.getId());

        // Act
        List<Pelicula> result = metrajeService.obtenerUnTipoMetrajes(Pelicula.class);

        // Assert
        boolean encontrado = false;
        for (Pelicula p : result) {
            if (p.getTitulo().equals("Pelicula Unica Tipo")) {
                encontrado = true;
            }
        }
        assertTrue(encontrado, "No se encontró la película esperada");
    }

    @Test
    public void testObtenerMetrajesFiltradosPelicula() {
        // Arrange
        List<Genero> generos = new ArrayList<>();
        generos.add(Genero.ACCION);
        List<String> actores = new ArrayList<>();
        actores.add("act1");

        Pelicula pelicula = new Pelicula("Pelicula Filtrada Buscar", 2020, generos, "Sinopsis", "url", "dir1", 4.5, actores, 120);
        Pelicula savedPelicula = peliculaRepository.save(pelicula);
        peliculaIds.add(savedPelicula.getId());

        // Act
        List<Pelicula> result = metrajeService.obtenerMetrajesFiltrados(Pelicula.class, "Filtrada", null, null, null);

        // Assert
        assertEquals(1, result.size(), "Se esperaba 1 película filtrada");
        assertEquals("Pelicula Filtrada Buscar", result.get(0).getTitulo(), "El título de la película no coincide");
    }

    @Test
    public void testObtenerTodosMetrajesFiltrados() {
        // Arrange
        List<Genero> generos = new ArrayList<>();
        generos.add(Genero.HORROR);
        List<String> actores = new ArrayList<>();
        actores.add("act");

        Pelicula p = new Pelicula("Metraje Compartido Especial", 2019, generos, "Sinopsis", "url", "dir", 4.2, actores, 100);
        Serie s = new Serie("Metraje Compartido Especial", 2019, generos, "Sinopsis", "url", "dir", 4.2, actores, 1, 10, 40, org.paloma.plottwist.model.Estado.FINALIZADA);

        peliculaIds.add(peliculaRepository.save(p).getId());
        serieIds.add(serieRepository.save(s).getId());

        // Act
        List<Metraje> result = metrajeService.obtenerTodosMetrajesFiltrados("Compartido", null, null, null);

        // Assert
        int count = 0;
        for (Metraje m : result) {
            if (m.getTitulo().contains("Compartido")) {
                count++;
            }
        }
        assertEquals(2, count, "Se esperaban 2 metrajes con el título 'Compartido'");
    }

    @Test
    public void testObtenerDestacados() {
        // Arrange
        List<Genero> generosAccion = new ArrayList<>();
        generosAccion.add(Genero.ACCION);
        List<Genero> generosDrama = new ArrayList<>();
        generosDrama.add(Genero.DRAMA);
        List<Genero> generosHorror = new ArrayList<>();
        generosHorror.add(Genero.HORROR);
        List<String> actoresPelicula = new ArrayList<>();
        actoresPelicula.add("act1");
        List<String> actoresSerie = new ArrayList<>();
        actoresSerie.add("act2");

        Pelicula pelicula1 = new Pelicula("Top Movie 1", 2020, generosAccion, "Sinopsis", "url", "dir1", 1000.0, actoresPelicula, 120);
        Pelicula pelicula2 = new Pelicula("Top Movie 2", 2022, generosAccion, "Sinopsis", "url", "dir3", 950.0, actoresPelicula, 110);
        Serie serie1 = new Serie("Top Serie 1", 2021, generosDrama, "Sinopsis", "url", "dir2", 999.0, actoresSerie, 2, 20, 45, org.paloma.plottwist.model.Estado.FINALIZADA);
        Serie serie2 = new Serie("Top Serie 2", 2023, generosDrama, "Sinopsis", "url", "dir4", 899.0, actoresSerie, 1, 10, 50, org.paloma.plottwist.model.Estado.FINALIZADA);

        peliculaIds.add(peliculaRepository.save(pelicula1).getId());
        peliculaIds.add(peliculaRepository.save(pelicula2).getId());
        serieIds.add(serieRepository.save(serie1).getId());
        serieIds.add(serieRepository.save(serie2).getId());

        // Act
        List<Metraje> result = metrajeService.obtenerDestacados(2);

        // Assert
        assertEquals(4, result.size(), "Se esperaban 4 metrajes destacados");

        boolean tieneValoracion1000 = false;
        boolean tieneValoracion999 = false;

        for (Metraje m : result) {
            if (m.getValoracion() == 1000.0) {
                tieneValoracion1000 = true;
            }
            if (m.getValoracion() == 999.0) {
                tieneValoracion999 = true;
            }
        }

        assertTrue(tieneValoracion1000, "No se encontró un metraje con valoración 1000.0");
        assertTrue(tieneValoracion999, "No se encontró un metraje con valoración 999.0");
    }

    @Test
    public void testObtenerDetalles() {
        // Arrange
        String idFijoEspecial = "id-estatico-prueba-123";
        List<Genero> generos = new ArrayList<>();
        generos.add(Genero.HORROR);
        List<String> actores = new ArrayList<>();

        Pelicula pelicula = new Pelicula("Movie Base Static", 2022, generos, "Sinopsis", "url", "dir", 4.0, actores, 90);
        pelicula.setId(idFijoEspecial);
        
        Serie serie = new Serie("Serie Ganadora Static", 2022, generos, "Sinopsis", "url", "dir", 4.0, actores, 1, 10, 45, org.paloma.plottwist.model.Estado.FINALIZADA);
        serie.setId(idFijoEspecial);

        peliculaRepository.save(pelicula);
        serieRepository.save(serie);
        peliculaIds.add(idFijoEspecial);
        serieIds.add(idFijoEspecial);

        // Act
        Metraje result = metrajeService.obtenerDetalles(idFijoEspecial);

        // Assert
        assertNotNull(result);
        assertEquals("Serie Ganadora Static", result.getTitulo(), "El título del metraje no coincide");
    }
}