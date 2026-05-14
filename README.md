# 🎬 PlotTwist

**PlotTwist** es una aplicación web para la gestión y visualización de películas y series. Permite explorar catálogos de contenido audiovisual, aplicar filtros por género, año y valoración, y obtener información detallada sobre actores y directores.

## 🛠 Tecnologías Usadas

- **Backend**: Spring Boot 4.0.6
- **Base de Datos**: MongoDB
- **Lenguaje**: Java 21
- **Framework Web**: Spring Web MVC
- **ORM**: Spring Data MongoDB
- **Frontend**: HTML5, CSS3, JavaScript
- **Testing**: JUnit 5, Spring Boot Test
- **Build Tool**: Maven

## 📋 Requisitos Previos

- **Java**: JDK 21 o superior
- **Maven**: 3.6+ (viene incluido con Spring Boot)
- **MongoDB**: Instancia local o remota de MongoDB
- **Navegador Web**: Cualquier navegador moderno

## 🚀 Instalación y Configuración

### 1. Clonar el repositorio
```bash
git clone <url-del-repositorio>
cd plottwist
```

### 2. Configurar MongoDB
La aplicación se conecta a **MongoDB Atlas** (base de datos en la nube) mediante la configuración personalizada en la clase `MongoConfig.java`. No requiere configuración adicional local.

### 3. Ejecutar la aplicación
```bash
# Con Maven Wrapper (recomendado)
./mvnw spring-boot:run

# O con Maven instalado
mvn spring-boot:run
```

### 4. Acceder a la aplicación
- **Backend API**: http://localhost:8082
- **Frontend**: Abre `front/index.html` en un servidor web local (ej: Live Server en VS Code)

## 📁 Estructura del Proyecto

```
plottwist/
├── mvnw & mvnw.cmd          # Maven Wrapper
├── pom.xml                  # Configuración Maven
├── README.md                # Este archivo
├── analisis/                # Documentación de análisis
│   └── casosdeUso.md
├── bd/                      # Datos de ejemplo
│   ├── contraseña_sql.txt
│   ├── peliculas.json
│   ├── personas.json
│   ├── series.json
│   └── usuarios.json
├── diseño/                  # Documentación de diseño
│   ├── diagrama_clase.md
│   └── modelo_no_sql_metrajes.md
├── front/                   # Frontend de la aplicación
│   ├── index.html
│   ├── assets/
│   │   └── img/
│   ├── css/
│   │   ├── metraje.css
│   │   ├── persona.css
│   │   └── styles.css
│   ├── js/
│   │   ├── detalle.js
│   │   ├── main.js
│   │   ├── peliculas.js
│   │   ├── personas.js
│   │   └── series.js
│   └── paginas/
│       ├── metraje.html
│       ├── peliculas.html
│       ├── personas.html
│       └── serie.html
└── src/
    ├── main/
    │   ├── java/org/paloma/plottwist/
    │   │   ├── PlottwistApplication.java    # Clase principal
    │   │   ├── WebConfig.java               # Configuración CORS
    │   │   ├── controller/                  # Controladores REST
    │   │   │   ├── MetrajesController.java
    │   │   │   └── PersonasController.java
    │   │   ├── model/                       # Modelos de datos
    │   │   │   ├── Estado.java
    │   │   │   ├── Genero.java
    │   │   │   ├── Metraje.java
    │   │   │   ├── OrdenPorFecha.java
    │   │   │   ├── OrdenPorValoracion.java
    │   │   │   ├── Pelicula.java
    │   │   │   ├── Persona.java
    │   │   │   ├── Serie.java
    │   │   │   ├── TipoMetraje.java
    │   │   │   └── Usuario.java
    │   │   ├── MongoConfig/                 # Configuración MongoDB
    │   │   │   └── MongoConfig.java
    │   │   ├── repository/                  # Repositorios de datos
    │   │   │   ├── PeliculaRepository.java
    │   │   │   ├── PersonaRepository.java
    │   │   │   └── SerieRepository.java
    │   │   └── service/                     # Lógica de negocio
    │   │       ├── MetrajeService.java
    │   │       └── PersonaService.java
    │   └── resources/
    │       └── application.properties       # Configuración de la app
    └── test/
        └── java/org/paloma/plottwist/       # Tests
            ├── MetrajeServiceIntegrationTest.java
            ├── PeliculaRepositoryIntegrationTest.java
            └── PlottwistApplicationTests.java
```

## 🔗 Endpoints

### Metrajes Controller (`/metrajes`)

#### `GET /obtenerTipo`
Obtiene todos los metrajes de un tipo específico (películas o series).

**Parámetros:**
- `tipoMetraje` (obligatorio): Tipo de metraje a obtener. Valores: `PELICULA` o `SERIE`

**Ejemplo:**
```
GET http://localhost:8082/metrajes/obtenerTipo?tipoMetraje=PELICULA
```

#### `GET /obtenerFiltrados`
Obtiene metrajes de un tipo específico aplicando filtros opcionales de búsqueda.

**Parámetros:**
- `tipoMetraje` (obligatorio): Tipo de metraje a obtener. Valores: `PELICULA` o `SERIE`
- `nombre` (opcional): Texto a buscar en el título del metraje
- `generos` (opcional): Lista de géneros separados por coma. Valores: `ACCION`, `DRAMA`, `COMEDIA`, `FANTASIA`, `HORROR`, `MISTERIO`, `ROMANCE`, `CIENCIA_FICCION`, `SUSPENSE`, `THRILLER`
- `anyo` (opcional): Año de estreno del metraje
- `valoracion` (opcional): Valoración numérica (1-5). Retorna metrajes con valoración entre x.0 y x.9

**Ejemplo:**
```
GET http://localhost:8082/metrajes/obtenerFiltrados?tipoMetraje=PELICULA&generos=ACCION,DRAMA&anyo=2020&valoracion=4
```

#### `GET /obtenerTodosFiltrados`
Obtiene películas y series combinadas aplicando los mismos filtros de búsqueda.

**Parámetros:**
- `nombre` (opcional): Texto a buscar en el título del metraje
- `generos` (opcional): Lista de géneros separados por coma
- `anyo` (opcional): Año de estreno del metraje
- `valoracion` (opcional): Valoración numérica (1-5)

**Ejemplo:**
```
GET http://localhost:8082/metrajes/obtenerTodosFiltrados?nombre=batman&generos=ACCION&valoracion=4
```

#### `GET /obtenerDestacados`
Obtiene los metrajes más destacados (mejor valorados) de cada tipo.

**Parámetros:**
- `cantidad` (obligatorio): Número de películas y de series a retornar (total = cantidad × 2)

**Ejemplo:**
```
GET http://localhost:8082/metrajes/obtenerDestacados?cantidad=5
```

#### `GET /obtenerDetalles`
Obtiene los detalles completos de un metraje específico, incluyendo información del director y actores.

**Parámetros:**
- `metraje` (obligatorio): ID del metraje a obtener

**Ejemplo:**
```
GET http://localhost:8082/metrajes/obtenerDetalles?metraje=507f1f77bcf86cd799439011
```

### Personas Controller (`/personas`)

#### `GET /mostrarDestacados`
Obtiene los metrajes más destacados de una persona específica (actor o director).

**Parámetros:**
- `cantidad` (obligatorio): Número de películas y de series a retornar de la persona
- `idPersona` (obligatorio): ID de la persona (actor o director) de la que se desean obtener los metrajes

**Ejemplo:**
```
GET http://localhost:8082/personas/mostrarDestacados?cantidad=3&idPersona=507f1f77bcf86cd799439012
```

## 🧪 Tests

El proyecto incluye tests unitarios e de integración:

### Tests Disponibles
- **PlottwistApplicationTests**: Test básico de carga del contexto de Spring
- **MetrajeServiceIntegrationTest**: Tests de integración para el servicio de metrajes

### Ejecutar Tests  
```bash
# Ejecutar todos los tests
./mvnw test

# Ejecutar tests específicos
./mvnw test -Dtest=MetrajeServiceIntegrationTest
```

### Cobertura de Tests
Los tests cubren:
- ✅ Lógica de negocio de servicios
- ✅ Consultas a base de datos
- ✅ Hidratación de objetos relacionados
- ✅ Filtros y búsquedas
- ✅ Ordenamiento por valoración

---

**Desarrollado por**: MiguelSg77, JesusCanas y AdrianStephano  
**Versión**: 1.0