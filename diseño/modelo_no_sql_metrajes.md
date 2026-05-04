# MODELO DE BASE DE DATOS NO RELACIONAL


## 📌 Descripción general
Este modelo de base de datos no relacional está diseñado para gestionar información de **películas y series** utilizando una única colección llamada `metrajes`.

Se trata de un modelo polimórfico, donde todos los documentos comparten una estructura base, pero pueden incluir atributos adicionales dependiendo del tipo de contenido.

---

## 🎬 Películas
### 🔹 Atributos base (Metraje)
- `id`: Identifica al metraje.
- `titulo`: Nombre del contenido
- `anyo`: Año de lanzamiento
- `genero`: Género del contenido
- `creador`: Autor o director
- `valoracion`: Puntuación o rating
- `sinopsis`: Descripción breve
- `tipo`: Indica si es "pelicula" o "serie"
- `imagen`: URL del poster o imagen principal del metraje almacenada en Amazon S3. Este atributo no guarda el archivo directamente, sino la dirección (URL) que permite acceder a la imagen desde el frontend.
- `duracion`: Duración total en minutos

### ✔️ Ejemplo de documento (película)
```json
{
  "id":2,
  "titulo": "Inception",
  "anyo": 2010,
  "genero": "Ciencia ficción",
  "creador": "Christopher Nolan",
  "valoracion": 8.8,
  "sinopsis": "Un ladrón que roba secretos a través de los sueños.",
  "imagen": "https://s3.amazonaws.com/bucket/metrajes/peliculas/2/poster.jpg",
  "duracion": 148
}
```

---

## 📺 Series
- `id`: Identifica al metraje.
- `titulo`: Nombre del contenido
- `anyo`: Año de lanzamiento
- `genero`: Género del contenido
- `creador`: Autor o director
- `valoracion`: Puntuación o rating
- `sinopsis`: Descripción breve
- `tipo`: Indica si es "pelicula" o "serie"
- `imagen`: URL del poster o imagen principal del metraje almacenada en Amazon S3. Este atributo no guarda el archivo directamente, sino la dirección (URL) que permite acceder a la imagen desde el frontend.
- `numTemporadas`: Número de temporadas
- `numEpisodios`: Número total de episodios
- `duracionEpisodio`: Duración media de cada episodio
- `estado`: Estado de la serie (por ejemplo: "EN_EMISION", "FINALIZADA")

### ✔️ Ejemplo de documento (serie)
```json
{
  "id":4,
  "titulo": "Dark",
  "anyo": 2017,
  "genero": "Ciencia ficción",
  "creador": "Baran bo Odar",
  "valoracion": 8.7,
  "sinopsis": "La desaparición de niños revela secretos en un pueblo.",
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
id: Identificador único
nombre: Nombre completo
fechaNacimiento: Fecha de nacimiento
nacionalidad: País de origen
rol: Tipo de persona (actor o director)
### ✔️ Ejemplo
```json
{
  "id": 1,
  "nombre": "Quentin Tarantino",
  "fechaNacimiento": "1963-03-27",
  "nacionalidad": "Estados Unidos",
  "rol": "director"
}
```
---
## 🧩 Colección : `Usuarios`
Esta colección almacena la información de los usuarios de la aplicación.

### 🔹 Atributos
id: Identificador único del usuario
nombre: Nombre del usuario
email: Correo electrónico (único)
password: Contraseña cifrada
fechaRegistro: Fecha de creación de la cuenta
### ✔️ Ejemplo
```json
{
  "id": 10,
  "nombre": "usuario1",
  "email": "usuario1@email.com",
  "password": "hash_seguro",
  "fechaRegistro": "2026-04-30"
}
```
---

---

