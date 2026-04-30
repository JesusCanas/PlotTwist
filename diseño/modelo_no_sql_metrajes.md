# MODELO DE BASE DE DATOS NO RELACIONAL


## 📌 Descripción general
Este modelo de base de datos no relacional está diseñado para gestionar información de **películas y series** utilizando una única colección llamada `metrajes`.

Se trata de un modelo polimórfico, donde todos los documentos comparten una estructura base, pero pueden incluir atributos adicionales dependiendo del tipo de contenido.

---

## 🧩 Colección: `metrajes`
Cada documento representa un contenido audiovisual (película o serie) y contiene los siguientes atributos comunes:

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
- `video`: URL del vídeo almacenado en S3, normalmente un tráiler o contenido asociado al metraje.

---

## 🎬 Películas
Si el tipo de metraje es **película**, el documento incluirá el siguiente atributo adicional:

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
  "tipo": "pelicula",
  "imagen": "https://s3.amazonaws.com/bucket/metrajes/peliculas/2/poster.jpg",
  "video": "https://s3.amazonaws.com/bucket/metrajes/peliculas/2/video.mp4",
  "duracion": 148
}
```

---

## 📺 Series
Si el tipo de metraje es **serie**, el documento incluirá los siguientes atributos adicionales:

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
  "tipo": "serie",
  "numTemporadas": 3,
  "numEpisodios": 26,
  "duracionEpisodio": 50,
  "estado": "FINALIZADA"
}
```

---

## ⚙️ Consideraciones de diseño

- Se utiliza una **única colección** para simplificar consultas.
- El campo `tipo` actúa como **discriminador** para diferenciar entre películas y series.
- Los atributos específicos se incluyen solo cuando aplican.
- No se almacenan campos nulos innecesarios.

---

