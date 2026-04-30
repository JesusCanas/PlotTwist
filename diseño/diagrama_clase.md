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
        -<List Persona> personas
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
        - String lugarNacimiento
        - String rol
        - List<Metraje>  metrajes
        +getters
        +setters
    }
    class  MetrajeDAO {
        -List~Metraje~ metrajes
        +MetrajeDAO()
        +obtenerRandom() 
        +obtenerPorTitulo(String) 
        +obtenerPorGenero(String)
        +mostrarDestacados(List<Metraje>)
        +obtenerPorGenero(String) : List<Contenido>
        +obtenerPeliculas() : List<Pelicula>
        +obtenerSeries() : List<Serie>
        +obtenerPersonas() : List<Personas>
        +obtenerPorValoracion(int)
    }

    class MetrajeController {
        <<RestController>>
        -ContenidoDAO contenidoDAO
        +ContenidoController()
        +obtenerRandom()
        +obtenerPorTitulo(String)
        +obtenerPorGenero(String) 
        +mostrarDestacados(List<Metraje>)
        +obtenerPeliculas() : List<Pelicula>
        +obtenerSeries() : List<Serie>
        +obtenerPersonas() : List<Personas>
        +obtenerPorValoracion(int)
    }

    class MetrajeApiApplication {
        <<SpringBootApplication>>
        +main(String[] args)
    }
    Metraje <|-- Pelicula
    Metraje <|-- Serie 
    Metraje <--> Persona
    MetrajeDAO --> Metraje 
    MetrajeController --> MetrajeDAO 
    MetrajeApiApplication ..> MetrajeController 
```
