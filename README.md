# PlotTwist

PlotTwist es un proyecto web para explorar información de películas, series, actores y directores. Incluye una API REST en Spring Boot y un frontend estático con HTML, CSS y JavaScript.

## Descripción

PlotTwist está diseñado como un sitio informativo de entretenimiento donde se pueden consultar:
- Películas
- Series
- Personas (actores y directores)
- Detalles de metrajes

El proyecto combina:
- Backend en Java con Spring Boot
- Base de datos MongoDB
- Frontend estático con HTML/CSS/JS

## Tecnologías utilizadas

- Java 21
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data MongoDB
- MongoDB Atlas (conexión remota configurada en `MongoConfig.java`)
- Maven
- HTML5 / CSS3 / JavaScript vanilla

## Estructura del proyecto

### Backend

- `src/main/java/org/paloma/plottwist/PlottwistApplication.java` - Aplicación principal de Spring Boot
- `src/main/java/org/paloma/plottwist/controller/` - Controladores REST
  - `PeliculasController.java`
  - `SeriesController.java`
- `src/main/java/org/paloma/plottwist/dao/` - DAO de ejemplo en memoria
  - `PeliculasDAO.java`
  - `SeriesDAO.java`
- `src/main/java/org/paloma/plottwist/model/` - Modelos de dominio
  - `Metraje.java`, `Pelicula.java`, `Serie.java`, `Persona.java`, `Usuario.java`
  - `Genero.java`, `Estado.java`
  - `OrdenPorFecha.java`, `OrdenPorValoracion.java`
- `src/main/java/org/paloma/plottwist/repository/` - Repositorios MongoDB
  - `PeliculaRepository.java`, `SerieRepository.java`, `PersonaRepository.java`
- `src/main/java/org/paloma/plottwist/MongoConfig/MongoConfig.java` - Configuración de MongoDB
- `src/main/resources/application.properties` - Configuración de la aplicación

### Frontend

- `front/index.html` - Página principal
- `front/paginas/` - Páginas adicionales de navegación
  - `detalle.html`
  - `peliculas.html`
  - `personas.html`
  - `serie.html`
- `front/css/` - Estilos
  - `styles.css`
  - `detalle.css`
- `front/js/` - Lógica de cliente (pendiente de implementar)
  - `api.js`
  - `peliculas.js`
  - `series.js`
  - `personas.js`
  - `detalle.js`
- `front/assets/img/` - Imágenes y recursos estáticos

### Datos de ejemplo

- `bd/peliculas.json`
- `bd/series.json`
- `bd/personas.json`
- `bd/usuarios.json`

Estas colecciones ofrecen datos de ejemplo para cargar información de metrajes y personas.

## Instalación y ejecución

### Requisitos previos

- Java 21
- Maven
- MongoDB Atlas o instancia MongoDB accesible

### Ejecutar backend

1. Abrir terminal en el directorio raíz del proyecto.
2. Ejecutar:

```bash
./mvnw spring-boot:run
```

En Windows:

```powershell
mvnw.cmd spring-boot:run
```

El backend se iniciará en `http://localhost:8082`.

### Ejecutar frontend

El frontend es una aplicación estática. Puedes abrir `front/index.html` directamente en el navegador o servirla desde un servidor web local.

Para un servidor ligero de desarrollo, puedes usar Python si lo tienes instalado:

```bash
cd front
python -m http.server 8000
```

Luego abre `http://localhost:8000`.

## API REST

### Endpoints de películas

- `GET /peliculas/mostrarTodo` - Devuelve todas las películas del DAO de ejemplo
- `GET /peliculas/mostrarFiltrado` - Filtra películas por parámetros opcionales:
  - `nombre`
  - `valoracion`
  - `anyo`
  - `generos`
- `GET /peliculas/obtenerDestacados?cantidad={n}` - Devuelve las `n` películas mejor valoradas
- `GET /peliculas/obtenerPorFecha?cantidad={n}` - Devuelve las `n` películas más recientes
- `GET /peliculas/obtenerPeliculaPorId?id={id}` - Devuelve una película por su id
- `GET /peliculas/mostrarBDTodo` - Devuelve todas las películas almacenadas en MongoDB

### Endpoints de series

- `GET /series/mostrarTodo` - Devuelve todas las series del DAO de ejemplo
- `GET /series/mostrarFiltrado` - Filtra series por parámetros opcionales:
  - `nombre`
  - `valoracion`
  - `anyo`
  - `generos`
- `GET /series/obtenerDestacados?cantidad={n}` - Devuelve las `n` series mejor valoradas
- `GET /series/obtenerPorFecha?cantidad={n}` - Devuelve las `n` series más recientes
- `GET /series/obtenerSeriePorId?id={id}` - Devuelve una serie por su id

## Estado actual del proyecto

- Backend en Spring Boot funcional con controladores REST y modelos bien definidos.
- DAO de ejemplo en memoria para películas y series.
- Repositorios MongoDB presentes, con integración de Spring Data.
- Frontend estructurado en HTML/CSS/JS, pero los archivos JavaScript necesitan conexión al backend.
- La página principal y las páginas de detalle ya existen, aunque requieren implementación de la lógica cliente.

## Consideraciones importantes

### Seguridad

- La configuración actual incluye credenciales y conexión MongoDB en código en `MongoConfig.java`; esto debe migrarse a variables de entorno o a `application.properties` seguro.
- Los datos de usuarios en `bd/usuarios.json` incluyen contraseñas de ejemplo. Es recomendable usar hashing seguro y no almacenar contraseñas en claro.

### Mejoras recomendadas

- Implementar la conexión entre frontend y backend con `fetch` o AJAX.
- Completar `front/js/api.js`, `front/js/peliculas.js`, `front/js/series.js`, `front/js/personas.js` y `front/js/detalle.js`.
- Añadir autenticación de usuarios y manejo de sesiones.
- Implementar comentarios, valoraciones y listas personalizadas.
- Añadir paginación y búsqueda avanzada.
- Migrar datos de `bd/*.json` a MongoDB de forma automática o mediante scripts de carga.

## Estructura de datos clave

### Película
- `id`
- `titulo`
- `anyo`
- `generos`
- `sinopsis`
- `imagenURL`
- `duracion`
- `valoracion`
- `idDirector`
- `idsActores`

### Serie
- `id`
- `titulo`
- `anyo`
- `generos`
- `sinopsis`
- `imagenURL`
- `numTemporadas`
- `numEpisodios`
- `duracionEpisodio`
- `estado`
- `valoracion`
- `idDirector`
- `idsActores`

### Persona
- `id`
- `nombre`
- `apellido`
- `fechaNacimiento`
- `nacionalidad`
- `biografia`
- `imagenURL`
- `metrajesId`

## Contribuir

Si deseas ampliar PlotTwist, estos son los puntos ideales para empezar:

1. Completar el frontend y enlazarlo con la API.
2. Añadir autenticación e inicio de sesión.
3. Implementar gestión de usuarios, comentarios y valoraciones.
4. Crear scripts de carga de datos a MongoDB.
5. Añadir pruebas unitarias y de integración en el backend.

---

Gracias por usar PlotTwist. Este README recoge el estado actual del proyecto y las recomendaciones para su evolución.
