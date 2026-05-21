document.addEventListener("DOMContentLoaded", () => {
    const parametros = new URLSearchParams(window.location.search);
    const id = parametros.get("id");
    const URL_DETALLE = `http://98.84.88.91:8082/metrajes/obtenerDetalles?metraje=${id}`;
    const esSerie = id && id.startsWith("serie");

    function crear(element) {
        crearPoster(element);
        crearSinopsis(element);
        crearInfo(element);
    }

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
            const duracion = document.createElement("p");
            duracion.innerHTML = '<strong>Duración:</strong> ' + element.duracion + ' minutos';
            seccion_info.appendChild(duracion);
        }
    }

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

    function crearSinopsis(element) {
        const seccion_sinopsis = document.querySelector(".detalle-sinopsis");
        const p_sinopsis = document.createElement("p");
        p_sinopsis.textContent = element.sinopsis;
        seccion_sinopsis.appendChild(p_sinopsis);
    }

    function crearInfo(element) {
        const seccion_info = document.querySelector(".detalle-info");
        const genero = document.getElementById("genero-detalle");
        const valoracion = document.getElementById("valoracion-detalle");
        const anyo_detalle = document.getElementById("anyo-detalle");
        const director = document.getElementById("director-detalle");
        const actor = document.getElementById("actor-detalle");

        element.generos.forEach(g => {
            const span_genero = document.createElement('span');
            span_genero.textContent = g;
            span_genero.classList.add('badge-genero');
            genero.appendChild(span_genero);
        });

        valoracion.innerHTML = '<strong>Valoración</strong> ' + element.valoracion;
        anyo_detalle.innerHTML = '<strong>Año</strong> ' + element.anyo;

        if (director && element.director) {
            director.innerHTML = "<strong>Director</strong>";
            const span_director = document.createElement("span");
            span_director.textContent = element.director.nombre + " " + element.director.apellido;
            span_director.classList.add("badge-actor");
            span_director.addEventListener("click", () => {
                window.location.href = `persona.html?id=${element.director.id}`;
            });
            director.appendChild(span_director);
        }

        if (actor && Array.isArray(element.actores)) {
            actor.innerHTML = "<strong>Actores</strong>";
            element.actores.forEach(a => {
                const span_actor = document.createElement("span");
                span_actor.textContent = a.nombre + " " + a.apellido;
                span_actor.classList.add("badge-actor");
                span_actor.addEventListener("click", () => {
                    window.location.href = `persona.html?id=${a.id}`;
                });
                actor.appendChild(span_actor);
            });
        }

        comprobarTipoMetraje(esSerie, element, seccion_info);
    }

    fetch(URL_DETALLE)
        .then(res => res.json())
        .then(data => crear(data));
});