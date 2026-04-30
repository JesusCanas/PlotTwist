```mermaid
flowchart TD
    actor((🧍))
    
    %% Relaciones
    actor --- FiltrarEtiquetas["(1) Filtrar la búsqueda por etiquetas"]
    actor --- BuscarNombre["(2) Buscar por nombre"]
    actor --- DatosPortada["(3) Datos que se vean con portada y título"]
    actor --- VerInfo["(4) Poder ver información específica de la serie/película"]

    BuscarNombre -.-> DatosPortada
    FiltrarEtiquetas -.-> DatosPortada
    DatosPortada -.-> VerInfo

    %% Estilos
    classDef useCaseStyle stroke:#818cf8,fill:#eef2ff,color:#1e1b4b
    classDef actorStyle stroke:#2dd4bf,fill:#f0fdfa,color:#1e1b4b

    class actor actorStyle
    class FiltrarEtiquetas,BuscarNombre,DatosPortada,VerInfo useCaseStyle