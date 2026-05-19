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

    function clasificarYRenderizar(data, esFiltrado = false) {
        if (!Array.isArray(data)) return;

        if (divPelis) {
            while (divPelis.firstChild) divPelis.removeChild(divPelis.firstChild);
            if (boton_pelicula) divPelis.appendChild(boton_pelicula);
        }
        if (divSeries) {
            while (divSeries.firstChild) divSeries.removeChild(divSeries.firstChild);
            if (boton_serie) divSeries.appendChild(boton_serie);
        }

        data.forEach(item => {
            const tarjeta = crearTarjeta(item);
            if (item.tipo === "SERIE" || item.director === undefined) { 
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
        if (buscadorVal) url += `&nombre=${buscadorVal}`;
        if (valoracionVal) url += `&valoracion=${valoracionVal}`;
        if (generoVal) url += `&generos=${generoVal}`;
        if (anyoVal) url += `&anyo=${anyoVal}`;

        try {
            const res = await fetch(url);
            const data = await res.json();
            clasificarYRenderizar(data, true);
        } catch (error) {
            console.error(error);
        }
    }

    function debounce(funcion, tiempo) {
        clearTimeout(temporizador);
        temporizador = setTimeout(funcion, tiempo);
    }

    if (selectValoracion) selectValoracion.addEventListener("change", filtrar);
    if (selectGenero) selectGenero.addEventListener("change", () => debounce(filtrar, 2000));
    if (selectAnyo) selectAnyo.addEventListener("change", filtrar);
    if (buscador) buscador.addEventListener("input", () => debounce(filtrar, 2000));

    fetch("http://localhost:8082/metrajes/obtenerDestacados?cantidad=4")
        .then(res => res.json())
        .then(data => clasificarYRenderizar(data, false))
        .catch(error => console.error(error));
});