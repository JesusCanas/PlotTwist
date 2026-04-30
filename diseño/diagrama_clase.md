# Ver los peliculas y series
```mermaid
classDiagram
    class Metraje{
        -int id
        -String titulo
        -date anyo
        -String genero
        -String creador
        -double valoracion
        -List <Persona> personas
        +getters
        +setters
    }

    class Pelicula {
        -int duracion
        +getDuracion() int
        +setDuracion(int)
    }

    class Serie {
        -int numTemporadas
        -int numEpisodios
        -int duracionEpisodio
        -Enum estado
        +getters
        +setters
    }
    class Persona {
        - String nombre
        - String apellido
        - int edad
        - String Nacionalidad
        - String rol
        - List<Metraje> metrajes
        +getters
        +setters
    }

    class PeliculaDAO {
        -List<Pelicula> peliculas 
        +obtenerPorTitulo(String titulo) : List<Pelicula>
        +mostrarDestacados(int num) : List<Pelicula>
        +obtenerPorGenero(String genero) : List<Pelicula>
        +obtenerPersonas() : List<Persona>
        +obtenerPorValoracion(int valoracion) : List<Pelicula>
    }

    class SerieDAO {
        -List<Serie> series 
        +obtenerPorTitulo(String titulo) : List<Serie>
        +mostrarDestacados(int num) : List<Serie>
        +obtenerPorGenero(String genero) : List<Serie>
        +obtenerPersonas() : List<Persona>
        +obtenerPorValoracion(int valoracion) : List<Serie>
    }

    class PeliculaController {
        <<RestController>>
        -PeliculaDAO peliDao
        +obtenerPorTitulo(String titulo) : List<Pelicula>
        +mostrarDestacados(int num) : List<Pelicula>
        +obtenerPorGenero(String genero) : List<Pelicula>
        +obtenerPersonas() : List<Persona>
        +obtenerPorValoracion(int valoracion) : List<Pelicula>
    }

      class SerieController {
        <<RestController>>
        -List<Serie> series 
        +obtenerPorTitulo(String titulo) : List<Serie>
        +mostrarDestacados(int num) : List<Serie>
        +obtenerPorGenero(String genero) : List<Serie>
        +obtenerPersonas() : List<Persona>
        +obtenerPorValoracion(int valoracion) : List<Serie>
    }

    class MetrajeApiApplication {
        <<SpringBootApplication>>
        +main(String[] args)
    }

    class Usuario {
        - String nombre
        - String contraseña
        - String correo
        - Date fechaRegistro 
        - List<Metraje> metrajes
        +getters
        +setters
    }

    Metraje <|-- Pelicula
    Metraje <|-- Serie 
    Metraje <--> Persona
    SerieDAO --> Serie 
    SerieController --> SerieDAO 
    PeliculaDAO --> Pelicula 
    PeliculaController --> PeliculaDAO 
    MetrajeApiApplication ..> PeliculaController 
    MetrajeApiApplication ..> SerieController 
    Usuario --> Metraje


    
```
