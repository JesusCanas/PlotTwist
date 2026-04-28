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
        +Metraje()
        +Metraje(id, titulo, anyo, genero, creador, valoracion)
        +getters y setters...
    }

    class Pelicula {
        -int duracion
        +Pelicula()
        +Pelicula(id, titulo, anyo, genero, creador, valoracion, duracionMinutos)
        +getDuracion() int
        +setDuracion(int)
    }

    class Serie {
        -int numTemporadas
        -int numEpisodios
        -int duracionEpisodio
        -Enum estado
        +Serie()
        +Serie(id, titulo, anyo, genero, creador, valoracion, ...)
        +getters y setters...
    }

    class  MetrajeDAO {
        -List~Metraje~ metrajes
        +MetrajeDAO()
        +obtenerRandom() List~Metraje~
        +obtenerPorTitulo(String) List~Metraje~
        +obtenerPorGenero(String) List~Metraje~
    }

    class MetrajeController {
        <<RestController>>
        -ContenidoDAO contenidoDAO
        +ContenidoController()
        +obtenerRandom() List~Metraje~
        +obtenerPorTitulo(String) List~Metraje~
        +obtenerPorGenero(String) List~Metraje~
    }

    class MetrajeApiApplication {
        <<SpringBootApplication>>
        +main(String[] args)
    }

    Metraje <|-- Pelicula
    Metraje <|-- Serie 
    MetrajeDAO --> Metraje 
    MetrajeController --> MetrajeDAO 
    MetrajeApiApplication ..> MetrajeController 
```