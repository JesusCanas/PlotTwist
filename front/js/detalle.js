document.addEventListener("DOMContentLoaded", () => {

    // Lectura del id del metraje desde la query string de la URL
    const parametros = new URLSearchParams(window.location.search);
    const id = parametros.get("id");
    const URL_DETALLE = `http://98.84.88.91:8082/metrajes/obtenerDetalles?metraje=${id}`;

    // Los ids de series tienen el prefijo "serie_" para distinguirlos de películas
    const esSerie = id && id.startsWith("serie_");

    //  Botón del logo: vuelve a la página principal 
    const boton_principio = document.getElementById("logo");
    boton_principio.addEventListener("click", () => {
        window.location.href = "../index.html";
    });

    /**
     * Es la construcción completa de la página de detalle
     * llamando a las tres funciones de renderizado,en las que crean cada seccion.
     * @param {Object} element - Objeto metraje devuelto por la API.
     */
    function crear(element) {
        crearPoster(element);
        crearSinopsis(element);
        crearInfo(element);
    }

    /**
     * Añade a la sección de información los campos específicos
     * según si el metraje es una serie o una película.
     * - Serie:   temporadas, episodios, duración por episodio y estado.
     * - Película: duración total.
     * @param {boolean} esSerie     - true si el metraje es una serie.
     * @param {Object}  element     - Datos del metraje.
     * @param {HTMLElement} seccion_info - Contenedor donde se insertan los elementos.
     */
    function comprobarTipoMetraje(esSerie, element, seccion_info) {
        if (esSerie) {
            const temporadas = document.createElement("p");
            temporadas.innerHTML = '<strong>Temporadas:</strong> ' + element.numTemporadas;

            const episodios = document.createElement("p");
            episodios.innerHTML = '<strong>Episodios:</strong> ' + element.numEpisodios;

            const duracionEpi = document.createElement("p");
            duracionEpi.innerHTML = '<strong>Duración Episodio:</strong> ' + element.duracionEpisodio + ' minutos';

            const estado = document.createElement("p");
            estado.innerHTML = '<strong>Estado:</strong> ' + element.estado;

            seccion_info.appendChild(temporadas);
            seccion_info.appendChild(episodios);
            seccion_info.appendChild(duracionEpi);
            seccion_info.appendChild(estado);
        } else {
            // Para películas solo se muestra la duración total
            const duracion = document.createElement("p");
            duracion.innerHTML = '<strong>Duración:</strong> ' + element.duracion + ' minutos';
            seccion_info.appendChild(duracion);
        }
    }

    /**
     * Crear el póster (imagen) y el título del metraje
     * en la sección ".detalle-poster".
     * @param {Object} element - Datos del metraje.
     */
    function crearPoster(element) {
        const seccion_poster = document.querySelector(".detalle-poster");
        const img = document.createElement('img');
        const titulo = document.createElement("h1");
        img.src = element.imagenURL;
        img.alt = element.titulo;
        titulo.textContent = element.titulo;
        titulo.id = "detalle-titulo";
        seccion_poster.appendChild(img);
        seccion_poster.appendChild(titulo);
    }

    /**
     * Inserta el texto de la sinopsis en la sección ".detalle-sinopsis".
     * @param {Object} element - Datos del metraje.
     */
    function crearSinopsis(element) {
        const seccion_sinopsis = document.querySelector(".detalle-sinopsis");
        const p_sinopsis = document.createElement("p");
        p_sinopsis.textContent = element.sinopsis;
        seccion_sinopsis.appendChild(p_sinopsis);
    }

    /**
     * Rellena la sección ".detalle-info" con géneros, valoración, año,
     * director, actores y los campos propios del tipo de metraje.
     * Géneros, director y actores son clicables y navegan a su página de detalle.
     * @param {Object} element - Datos del metraje.
     */
    function crearInfo(element) {
        const seccion_info = document.querySelector(".detalle-info");
        const genero = document.getElementById("genero-detalle");
        const valoracion = document.getElementById("valoracion-detalle");
        const anyo_detalle = document.getElementById("anyo-detalle");
        const director = document.getElementById("director-detalle");
        const actor = document.getElementById("actor-detalle");

        // Cada género se representa como un badge independiente
        element.generos.forEach(g => {
            const span_genero = document.createElement('span');
            span_genero.textContent = g;
            span_genero.classList.add('badge-genero');
            genero.appendChild(span_genero);
        });

        valoracion.innerHTML = '<strong>Valoración</strong> ' + element.valoracion;
        anyo_detalle.innerHTML = '<strong>Año</strong> ' + element.anyo;

        // Director: solo se renderiza si el elemento existe en el DOM y la API lo devuelve
        if (director && element.director) {
            director.innerHTML = "<strong>Director</strong>";
            const span_director = document.createElement("span");
            span_director.textContent = element.director.nombre + " " + element.director.apellido;
            span_director.classList.add("badge-actor");
            // Clic en el director navega a su página de persona
            span_director.addEventListener("click", () => {
                window.location.href = `persona.html?id=${element.director.id}`;
            });
            director.appendChild(span_director);
        }

        // Actores: se itera el array solo si el contenedor y el dato existen
        if (actor && Array.isArray(element.actores)) {
            actor.innerHTML = "<strong>Actores</strong>";
            element.actores.forEach(a => {
                const span_actor = document.createElement("span");
                span_actor.textContent = a.nombre + " " + a.apellido;
                span_actor.classList.add("badge-actor");
                // Clic en el actor navega a su página de persona
                span_actor.addEventListener("click", () => {
                    window.location.href = `persona.html?id=${a.id}`;
                });
                actor.appendChild(span_actor);
            });
        }

        // Añade los campos específicos de serie o película
        comprobarTipoMetraje(esSerie, element, seccion_info);
    }

    // Carga inicial: se obtienen los detalles del metraje y se construye la página 
    fetch(URL_DETALLE)
        .then(res => res.json())
        .then(data => crear(data));
});
