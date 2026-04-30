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
        +getId() int
        +setId(int)
        +getTitulo() String
        +setTitulo(String)
        +getAnyo() date
        +setAnyo(date)
        +getGenero() String
        +setGenero(String)
        +getCreador() String
        +setCreador(String)
        +getValoracion() double
        +setValoracion(double)
        +getPersonas() List<Persona>
        +setPersonas(List<Persona>)
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
        +getNumTemporadas() int
        +setNumTemporadas(int)
        +getNumEpisodios() int
        +setNumEpisodios(int)
        +getDuracionEpisodio() int
        +setDuracionEpisodio(int)
        +getEstado() Enum
        +setEstado(Enum)
    }
    class Persona {
        - String nombre
        - String apellido
        - int edad
        - String Nacionalidad
        - String rol
        - List<Metraje> metrajes
        +getNombre() String
        +setNombre(String)
        +getApellido() String
        +setApellido(String)
        +getEdad() int
        +setEdad(int)
        +getNacionalidad() String
        +setNacionalidad(String)
        +getRol() String
        +setRol(String)
        +getMetrajes() List<Metraje>
        +setMetrajes(List<Metraje>)
    }

    class PeliculaDAO {
        -List<Pelicula> peliculas 
        +obtenerPorTitulo(String titulo)  List<Pelicula>
        +mostrarDestacados(int num) List<Pelicula>
        +obtenerPorGenero(String genero) List<Pelicula>
        +obtenerPersonas() List<Persona>
        +obtenerPorValoracion(int valoracion) List<Pelicula>
    }

    class SerieDAO {
        -List<Serie> series 
        +obtenerPorTitulo(String titulo) List<Serie>
        +mostrarDestacados(int num) List<Serie>
        +obtenerPorGenero(String genero)  List<Serie>
        +obtenerPersonas() List<Persona>
        +obtenerPorValoracion(int valoracion) List<Serie>
    }

    class PeliculaController {
        <<RestController>>
        -PeliculaDAO peliDao
        +obtenerPorTitulo(String titulo)  List<Pelicula>
        +mostrarDestacados(int num)  List<Pelicula>
        +obtenerPorGenero(String genero)  List<Pelicula>
        +obtenerPersonas()  List<Persona>
        +obtenerPorValoracion(int valoracion)  List<Pelicula>
    }

      class SerieController {
        <<RestController>>
        -SerieDAO serieDao
        +obtenerPorTitulo(String titulo) List<Serie>
        +mostrarDestacados(int num) List<Serie>
        +obtenerPorGenero(String genero)  List<Serie>
        +obtenerPersonas() List<Persona>
        +obtenerPorValoracion(int valoracion) List<Serie>
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
        +getNombre() String
        +setNombre(String)
        +getContraseña() String
        +setContraseña(String)
        +getCorreo() String
        +setCorreo(String)
        +getFechaRegistro() Date
        +setFechaRegistro(Date)
        +getMetrajes() List<Metraje>
        +setMetrajes(List<Metraje>)
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
