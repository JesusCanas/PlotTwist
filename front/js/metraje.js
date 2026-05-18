document.addEventListener("DOMContentLoaded", () => {
    const selectValoracion = document.getElementById("valoracion");
    const selectGenero = document.getElementById("genero");
    const selectAnyo = document.getElementById("anyo");
    const buscador = document.getElementById("buscador");

    const BASE_PELIS = 'http://localhost:8082/metrajes/obtenerFiltrados?tipoMetraje=PELICULA';
    const BASE_SERIES = 'http://localhost:8082/metrajes/obtenerFiltrados?tipoMetraje=SERIE';

    const divPelicula = document.getElementById("pelis");
    const divSerie = document.getElementById("seri");
    let temporizador;

    function crearTarjeta(element, tipo) {
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
        return divPoster;
    }

    async function filtrar() {
        const buscadorVal = buscador.value;
        const valoracionVal = selectValoracion.value;
        const generoVal = selectGenero.value;
        const anyoVal = selectAnyo.value;

        let urlPelis = BASE_PELIS;
        let urlSeries = BASE_SERIES;

        if (buscadorVal) {
            urlPelis += `&nombre=${buscadorVal}`;
            urlSeries += `&nombre=${buscadorVal}`;
        }
        if (valoracionVal) {
            urlPelis += `&valoracion=${valoracionVal}`;
            urlSeries += `&valoracion=${valoracionVal}`;
        }
        if (generoVal) {
            urlPelis += `&generos=${generoVal}`;
            urlSeries += `&generos=${generoVal}`;
        }
        if (anyoVal) {
            urlPelis += `&anyo=${anyoVal}`;
            urlSeries += `&anyo=${anyoVal}`;
        }

        if (divPelicula) {
            while (divPelicula.firstChild) {
                divPelicula.removeChild(divPelicula.firstChild);
            }
            const resPelis = await fetch(urlPelis);
            const dataPelis = await resPelis.json();
            dataPelis.forEach(item => divPelicula.appendChild(crearTarjeta(item, 'peliculas')));
        }

        if (divSerie) {
            while (divSerie.firstChild) {
                divSerie.removeChild(divSerie.firstChild);
            }
            const resSeries = await fetch(urlSeries);
            const dataSeries = await resSeries.json();
            dataSeries.forEach(item => divSerie.appendChild(crearTarjeta(item, 'series')));
        }
    }

    function debounce(funcion, tiempo) {
        clearTimeout(temporizador);
        temporizador = setTimeout(funcion, tiempo);
    }

    selectValoracion.addEventListener("change", filtrar);
    selectGenero.addEventListener("change", () => debounce(filtrar, 2000));
    selectAnyo.addEventListener("change", filtrar);
    buscador.addEventListener("input", () => debounce(filtrar, 2000));

    async function cargarInicial() {
        if (divPelicula) {
            const res = await fetch(BASE_PELIS);
            const data = await res.json();
            data.forEach(item => divPelicula.appendChild(crearTarjeta(item, 'peliculas')));
        }
        if (divSerie) {
            const res = await fetch(BASE_SERIES);
            const data = await res.json();
            data.forEach(item => divSerie.appendChild(crearTarjeta(item, 'series')));
        }
    }

    cargarInicial();
});