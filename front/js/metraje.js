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

    function crearTarjeta(element) {
        const divPoster = document.createElement('div');
        divPoster.classList.add("poster");

        const img = document.createElement('img');
        img.src = element.imagenURL;
        img.alt = element.titulo;
        divPoster.dataset.id = element.id; 

        const titulo = document.createElement('p');
        titulo.textContent = element.titulo;
        titulo.classList.add("titulo-texto");

        divPoster.appendChild(img);
        divPoster.appendChild(titulo);
        divPoster.addEventListener('click', () => {
            window.location.href = `detalle-metraje.html?id=${element.id}`;
        });
        return divPoster;
    }

    async function filtrar() {
        const buscadorVal = buscador.value;
        const valoracionVal = selectValoracion.value;
        const generoVal = selectGenero.value;
        const anyoVal = selectAnyo.value;
        let url;
        if(divPelicula){
            url=BASE_PELIS;
        }
        if(divSerie){
            url=BASE_SERIES
        }

        if (buscadorVal) {
            url += `&nombre=${buscadorVal}`;
        }
        if (valoracionVal) {
            url += `&valoracion=${valoracionVal}`;
        }
        if (generoVal) {
            url += `&generos=${generoVal}`;
        }
        if (anyoVal) {
            url += `&anyo=${anyoVal}`;   
        }

        if (divPelicula) {
            while (divPelicula.firstChild) {
                divPelicula.removeChild(divPelicula.firstChild);
            }
            const resPelis = await fetch(url);
            const dataPelis = await resPelis.json();
            dataPelis.forEach(item => divPelicula.appendChild(crearTarjeta(item, 'peliculas')));
        }

        if (divSerie) {
            while (divSerie.firstChild) {
                divSerie.removeChild(divSerie.firstChild);
            }
            const resSeries = await fetch(url);
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
            data.forEach(item => divPelicula.appendChild(crearTarjeta(item)));
        }
        if (divSerie) {
            const res = await fetch(BASE_SERIES);
            const data = await res.json();
            data.forEach(item => divSerie.appendChild(crearTarjeta(item)));
        }
    }

    cargarInicial();
});