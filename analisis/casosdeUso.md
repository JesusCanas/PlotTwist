```mermaid
flowchart TD
    actor((🧍))
    
    %% Relaciones
    actor --- Inicio["(1) Ver peliculas y series destacadas al inicio"]
    actor --- BuscarNombre["(2) Buscar tiutlos en barra de busqueda"]
    actor --- DatosPortada["(3) Ver peliculas y series con portada y titulo"]
    actor --- VerInfo["(4) Entrar a detalles de las peliculas/series"]
    actor --- FiltrarEtiquetas["(5) Filtrar la búsqueda por etiquetas"]
    actor --- VerBiografía["(6) Ver la biografia de director/actor"]
    actor --- CrearCuenta["(7) Tener una cuenta en la pagina"]
    actor --- InsertarDatos["(8) Insertar una pelicula/serie"]
    actor --- CrearLista["(9) Crear lista propia de series y peliculas"]
    actor --- Sesion["(10) Iniciar sesion"]
    actor --- Comentarios["(11) Poner comentarios en las peliculas/series"]


    %% Estilos
    classDef useCaseStyle stroke:#818cf8,fill:#eef2ff,color:#1e1b4b
    classDef actorStyle stroke:#2dd4bf,fill:#f0fdfa,color:#1e1b4b

    class actor actorStyle
    class Inicio,BuscarNombre,DatosPortada,VerInfo,FiltrarEtiquetas,VerBiografía,CrearCuenta,InsertarDatos,CrearLista,Sesion,Comentarios useCaseStyle