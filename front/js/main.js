document.addEventListener("DOMContentLoaded", () => {
    const boton_pelicula = document.getElementById('pelicula-contenedor');
    const boton_serie = document.getElementById('serie-contenedor');
    const selectValoracion = document.getElementById("valoracion");
    const selectGenero = document.getElementById("genero");
    const selectAnyo = document.getElementById("anyo");
    const buscador = document.getElementById("buscador");
    
    const divPelis = document.getElementById("pelis");
    const divSeries = document.getElementById("serie");
    
    const BASE_URL = "http://localhost:8082/metrajes/obtenerTodosFiltrados";
    let temporizador;

    if (boton_pelicula && boton_serie) {
        boton_pelicula.addEventListener('click', () => {
            window.location.href = 'paginas/peliculas.html';
        });
        boton_serie.addEventListener('click', () => {
            window.location.href = 'paginas/serie.html';
        });
    }

    function crearTarjeta(element) {
        const divPoster = document.createElement('div');
        divPoster.classList.add("poster");

        const img = document.createElement('img');
        img.src = element.imagenURL;
        img.alt = element.titulo;

        const titulo = document.createElement('p');
        titulo.textContent = element.titulo;
        titulo.classList.add("titulo-texto");

        divPoster.appendChild(img);
        divPoster.appendChild(titulo);

        divPoster.addEventListener('click', () => {
            window.location.href = `paginas/detalle-metraje.html?id=${element.id}`;
        });

        return divPoster;
    }

    function clasificarYRenderizar(data) {
        if (!Array.isArray(data)) return;

        if (divPelis) divPelis.innerHTML = "";
        if (divSeries) divSeries.innerHTML = "";

        // Si existe el botón de películas se añade; si no, se crea un espacio del mismo tamaño
        if (divPelis) {
            if (boton_pelicula) {
                divPelis.appendChild(boton_pelicula);
            } else {
                const placeholder = document.createElement('div');
                placeholder.classList.add("poster-placeholder");
                divPelis.appendChild(placeholder);
            }
        }

        // Si falta el botón de series, inyectamos un bloque vacío para sostener el diseño CSS
        if (divSeries) {
            if (boton_serie) {
                divSeries.appendChild(boton_serie);
            } else {
                const placeholder = document.createElement('div');
                placeholder.style.width = "100px"; // Ajusta al ancho de tu botón original si es necesario
                placeholder.style.flexShrink = "0";
                divSeries.appendChild(placeholder);
            }
        }

        data.forEach(item => {
            const tarjeta = crearTarjeta(item);
            const esSerie = item.tipo === "SERIE" || item.director === undefined;

            if (esSerie) {
                if (divSeries) divSeries.appendChild(tarjeta);
            } else {
                if (divPelis) divPelis.appendChild(tarjeta);
            }
        });
    }

    async function filtrar() {
        const buscadorVal = buscador ? buscador.value : "";
        const valoracionVal = selectValoracion ? selectValoracion.value : "";
        const anyoVal = selectAnyo ? selectAnyo.value : "";
        
        let generoVal = "";
        if (selectGenero) {
            generoVal = Array.from(selectGenero.selectedOptions)
                .map(option => option.value)
                .filter(val => val !== "")
                .join(",");
        }

        let url = BASE_URL + "?";
        if (buscadorVal) url += `&nombre=${encodeURIComponent(buscadorVal)}`;
        if (valoracionVal) url += `&valoracion=${valoracionVal}`;
        if (generoVal) url += `&generos=${generoVal}`;
        if (anyoVal) url += `&anyo=${anyoVal}`;

        const res = await fetch(url);
        const data = await res.json();
        clasificarYRenderizar(data);
    }

    function aplicarDebounce(funcion, tiempo = 300) {
        clearTimeout(temporizador);
        temporizador = setTimeout(funcion, tiempo);
    }

    selectValoracion?.addEventListener("change", filtrar);
    selectAnyo?.addEventListener("change", filtrar);
    selectGenero?.addEventListener("change", () => aplicarDebounce(filtrar, 300));
    buscador?.addEventListener("input", () => aplicarDebounce(filtrar, 300));

    fetch("http://localhost:8082/metrajes/obtenerDestacados?cantidad=4")
        .then(res => res.json())
        .then(data => clasificarYRenderizar(data));
});