```mermaid
flowchart TD
    actor((🧍))
    
    %% Relaciones
    actor --- Inicio["(1) Ver peliculas y series destacadas al inicio"]
    actor --- BuscarNombre["(2) Buscar películas y series por título en barra de busqueda"]
    actor --- DatosPortada["(3) Ver portada y titulo de los metrajes en las páginas de búsqueda"]
    actor --- VerInfo["(4) Ver detalles de los metrajes"]
    actor --- FiltrarEtiquetas["(5) Filtrar la búsqueda por valoración, año y género"]
    actor --- VerBiografía["(6) Ver la biografia de director/actor"]
    actor --- CrearCuenta["(7) Crear una cuenta en la pagina"]
    actor --- Sesion["(10) Iniciar sesion"]
    actor --- InsertarDatos["(8) Insertar una pelicula/serie"]
    actor --- InsertarDatos["(8) Modificar los datos una pelicula/serie"]
    actor --- CrearLista["(9) Crear lista propia de series y peliculas"]
    actor --- Comentarios["(11) Poner comentarios en las peliculas/series"]
    actor --- Comentarios["(11) Poner valoración en las peliculas/series"]


    %% Estilos
    classDef useCaseStyle stroke:#818cf8,fill:#eef2ff,color:#1e1b4b
    classDef actorStyle stroke:#2dd4bf,fill:#f0fdfa,color:#1e1b4b

    class actor actorStyle
    class Inicio,BuscarNombre,DatosPortada,VerInfo,FiltrarEtiquetas,VerBiografía,CrearCuenta,InsertarDatos,CrearLista,Sesion,Comentarios useCaseStyle