# MODELO DE BASE DE DATOS NO RELACIONAL


## 📌 Descripción general
Este modelo de base de datos no relacional está diseñado para gestionar información de **películas y series** utilizando una única colección llamada `metrajes`.

Se trata de un modelo polimórfico, donde todos los documentos comparten una estructura base, pero pueden incluir atributos adicionales dependiendo del tipo de contenido.

---

## 🎬 Películas
### 🔹 Atributos base (Metraje)
- `titulo`: Nombre del contenido
- `anyo`: Año de lanzamiento
- `genero`: Será una lista de los posibles géneros al que pertenece
- `idDirector`: Tendrá el _id del director
- `idActores`:Será un array de ids de los Actores
- `valoracion`: Puntuación o rating
- `sinopsis`: Descripción breve
- `imagen`: URL del poster o imagen principal del metraje almacenada en Amazon S3. Este atributo no guarda el archivo directamente, sino la dirección (URL) que permite acceder a la imagen desde el frontend.
- `duracion`: Duración total en minutos

### ✔️ Ejemplo de documento (película)
```json
{
  "titulo": "Inception",
  "anyo": 2010,
  "genero": ["ACCION","CIENCIA FICCION"],
  "idDirector":"ObjectId('928383838')" ,
  "idActores":["ObjectId('9283538')","ObjectId('92835433')"],
  "valoracion": 8.8,
  "sinopsis": "Un ladrón que roba secretos a través de los sueños.",
  "imagen": "https://s3.amazonaws.com/bucket/metrajes/peliculas/2/poster.jpg",
  "duracion": 148
}
```

---

## 📺 Series
- `titulo`: Nombre del contenido
- `anyo`: Año de lanzamiento
- `genero`: Será una lista de los posibles géneros al que pertenece
- `idDirector`: Tendrá el _id del director
- `idActores`:Será un array de ids de los Actores
- `valoracion`: Puntuación o rating
- `sinopsis`: Descripción breve
- `imagen`: URL del poster o imagen principal del metraje almacenada en Amazon S3. Este atributo no guarda el archivo directamente, sino la dirección (URL) que permite acceder a la imagen desde el frontend.
- `numTemporadas`: Número de temporadas
- `numEpisodios`: Número total de episodios
- `duracionEpisodio`: Duración media de cada episodio
- `estado`: Estado de la serie (por ejemplo: "EN_EMISION", "FINALIZADA")

### ✔️ Ejemplo de documento (serie)
```json
{
  "titulo": "Dark",
  "anyo": 2017,
  "genero": ["ACCION","CIENCIA FICCION"],
  "idDirector":"ObjectId('928383838')" ,
  "idActores":["ObjectId('9283538')","ObjectId('92835433')"],
  "valoracion": 8.8,
  "sinopsis": "Un ladrón que roba secretos a través de los sueños.",
  "imagen": "https://s3.amazonaws.com/bucket/metrajes/series/2/poster.jpg",
  "numTemporadas": 3,
  "numEpisodios": 26,
  "duracionEpisodio": 50,
  "estado": "FINALIZADA"
}
```
---
## 🧩 Colección: `Personas`
Esta colección almacena información sobre las personas relacionadas con los metrajes, como actores y directores.

Se utiliza un modelo polimórfico mediante un atributo discriminador rol, que indica el tipo de persona.

### 🔹 Atributos
- `nombre`: Nombre completo
- `apellido`: Apellido
- `fechaNacimiento`: Fecha de nacimiento
- `nacionalidad`: País de origen
- `imagen`: URL del poster o imagen principal del metraje almacenada en Amazon S3. Este atributo no guarda el archivo directamente, sino la dirección (URL) que permite acceder a la imagen desde el frontend.
- `idMetrajes`: metrajes que contiene ese director y acto
- 
### ✔️ Ejemplo
```json
{
  "nombre": "Quentin",
  "apellido":"Tarantino",
  "fechaNacimiento": "1963-03-27",
  "biografia":"Quentin Tarantino (Knoxville, 1963) es un aclamado director, guionista y productor estadounidense, conocido por revolucionar el cine independiente de los 90 con su estilo único: diálogos ingeniosos, narrativas no lineales y violencia estilizada. Autodidacta y ex-empleado de videoclub, saltó a la fama con Reservoir Dogs y Pulp Fiction",
  "nacionalidad": "Estados Unidos",
  "imagen":"https://s3.amazonaws.com/bucket/personas/director/2/poster.jpg",
}
```
---
## 🧩 Colección : `Usuarios`
Esta colección almacena la información de los usuarios de la aplicación.

### 🔹 Atributos
- `nombre`: Nombre del usuario
- `email`: Correo electrónico (único)
- `password`: Contraseña cifrada
- `fechaRegistro`: Fecha de creación de la cuentar
### ✔️ Ejemplo
```json
{
  "nombre": "usuario1",
  "email": "usuario1@email.com",
  "password": "hash_seguro",
  "fechaRegistro": "2026-04-30"
}
```
---

---

