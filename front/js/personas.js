document.addEventListener("DOMContentLoaded", () => {

    //  Lectura del id de la persona desde la query string de la URL 
    const parametros = new URLSearchParams(window.location.search);
    const idPersona = parametros.get("id");
    const URL_PERSONA = `http://98.84.88.91:8082/personas/obtenerDetalles?cantidad=3&idPersona=${idPersona}`;

    // Botón del logo: vuelve a la página principal 
    const boton_principio = document.getElementById("logo");
    boton_principio.addEventListener("click", () => {
        window.location.href = "../index.html";
    });

    /**
     * Renderiza la sección de foto y datos biográficos básicos de la persona
     * (imagen, nombre completo, fecha de nacimiento y nacionalidad).
     * Los campos opcionales muestran "No disponible" si la API no los devuelve.
     * @param {Object} elemento - Datos de la persona devueltos por la API.
     */
    function crearPersonaFoto(elemento) {

        const seccion_personaFoto = document.querySelector(".persona-foto");
        const imagen = document.createElement("img");
        const lista = document.createElement("ul");
        const nombrePersona = document.createElement("h1");
        const puntoFecha = document.createElement("li");
        const puntoNacionalidad = document.createElement("li");

        imagen.src = elemento.imagenURL || "";
        imagen.alt = (elemento.nombre || "") + " " + (elemento.apellido || "");

        nombrePersona.textContent =
            (elemento.nombre || "") + " " + (elemento.apellido || "");

        puntoFecha.innerHTML =
            "<strong>Fecha de nacimiento:</strong> " +
            (elemento.fechaDeNacimiento || "No disponible");

        puntoNacionalidad.innerHTML =
            "<strong>Nacionalidad:</strong> " +
            (elemento.nacionalidad || "No disponible");

        seccion_personaFoto.appendChild(imagen);
        seccion_personaFoto.appendChild(nombrePersona);
        seccion_personaFoto.appendChild(lista);

        lista.appendChild(puntoFecha);
        lista.appendChild(puntoNacionalidad);
    }

    /**
     * Inserta el texto de la biografía en la sección ".persona-biografia".
     * Si la API no devuelve biografía, muestra un mensaje por defecto.
     * @param {Object} elemento - Datos de la persona devueltos por la API.
     */
    function crearPersonaBiografia(elemento) {

        const seccion_biografia = document.querySelector(".persona-biografia");
        const parrafo_biografia = document.createElement("p");

        parrafo_biografia.textContent =
            elemento.biografia || "Biografía no disponible.";

        seccion_biografia.appendChild(parrafo_biografia);
    }

    /**
     * Crea y devuelve un elemento <li> con la imagen y el título de un metraje.
     * Al hacer clic navega a la página de detalle de ese metraje.
     * @param {Object} item - Objeto metraje con id, imagenURL y titulo.
     * @returns {HTMLElement} El <li> listo para insertar en una lista.
     */
    function crearTarjeta(item) {

        const lista = document.createElement("li");
        const img = document.createElement("img");
        const titulo = document.createElement("span");

        lista.className = "metraje-card";

        img.src = item.imagenURL || "";
        img.alt = item.titulo || "";
        titulo.textContent = item.titulo || "";

        // Clic en la tarjeta navega al detalle del metraje
        lista.addEventListener("click", () => {
            window.location.href = `detalle-metraje.html?id=${item.id}`;
        });

        lista.appendChild(img);
        lista.appendChild(titulo);

        return lista;
    }

    /**
     * Construye la sección ".persona-metrajes" separando la filmografía
     * en dos listas: películas (sin numTemporadas) y series (con numTemporadas).
     * Cada sección solo se añade al DOM si contiene al menos un elemento.
     * @param {Object} elemento - Datos de la persona, incluido el array de metrajes.
     */
    function crearPoster(elemento) {

        const seccion_poster = document.querySelector(".persona-metrajes");
        const todosLosMetrajes = elemento.metrajes || [];

        // Clasificación por tipo usando la presencia del campo numTemporadas
        const peliculas = todosLosMetrajes.filter(item => !item.numTemporadas);
        const series    = todosLosMetrajes.filter(item =>  item.numTemporadas);

        const encabezado_pelis   = document.createElement("h2");
        const encabezado_series  = document.createElement("h2");
        encabezado_pelis.textContent  = "Películas";
        encabezado_series.textContent = "Series";

        const lista_pelis   = document.createElement("ul");
        const lista_series  = document.createElement("ul");
        lista_pelis.className  = "metrajes-lista";
        lista_series.className = "metrajes-lista";

        // Solo se muestra el bloque si hay contenido para evitar encabezados vacíos
        if (peliculas.length > 0) {
            seccion_poster.appendChild(encabezado_pelis);
            seccion_poster.appendChild(lista_pelis);
        }

        if (series.length > 0) {
            seccion_poster.appendChild(encabezado_series);
            seccion_poster.appendChild(lista_series);
        }

        peliculas.forEach(item => lista_pelis.appendChild(crearTarjeta(item)));
        series.forEach(item => lista_series.appendChild(crearTarjeta(item)));
    }

    /**
     * Hace la construcción completa de la página de persona
     * llamando a las tres funciones de renderizado.
     * @param {Object} elemento - Datos completos de la persona devueltos por la API.
     */
    function crearPersona(elemento) {
        crearPersonaFoto(elemento);
        crearPersonaBiografia(elemento);
        crearPoster(elemento);
    }

    // Carga de datos: solo se lanza la petición si el id está presente en la URL
    if (idPersona) {
        fetch(URL_PERSONA)
            .then(res => {
                // Se lanza un error explícito para que el catch lo capture con el código HTTP
                if (!res.ok) throw new Error(`Error: ${res.status}`);
                return res.json();
            })
            .then(data => crearPersona(data))
            .catch(error => console.error(error));
    } else {
        // Sin id en la URL no tiene sentido lanzar la petición
        console.error("No se encontró el ID en la URL.");
    }

});
