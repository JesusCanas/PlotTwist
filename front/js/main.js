document.addEventListener("DOMContentLoaded", () => {

    const botonPelicula = document.getElementById("pelicula-contenedor");
    const botonSerie = document.getElementById("serie-contenedor");

    const selectValoracion = document.getElementById("valoracion");
    const selectGenero = document.getElementById("genero");
    const selectAnyo = document.getElementById("anyo");
    const buscador = document.getElementById("buscador");

    const contenedores = {
        peliculas: document.getElementById("pelis"),
        series: document.getElementById("serie")
    };

    const BASE_URL = "http://98.84.88.91:8082/metrajes/obtenerTodosFiltrados";

    let temporizador;


        botonPelicula.addEventListener("click", () => {
            window.location.href = "paginas/peliculas.html";
        });
    
        botonSerie.addEventListener("click", () => {
            window.location.href = "paginas/serie.html";
        });
    

    function crearTarjeta(elemento) {

        const divPoster = document.createElement("div");
        divPoster.classList.add("poster");

        const img = document.createElement("img");
        img.src = elemento.imagenURL;
        img.alt = elemento.titulo;

        const titulo = document.createElement("p");
        titulo.textContent = elemento.titulo;
        titulo.classList.add("titulo-texto");

        divPoster.appendChild(img);
        divPoster.appendChild(titulo);

        divPoster.addEventListener("click", () => {
            window.location.href =
                `paginas/detalle-metraje.html?id=${elemento.id}`;
        });

        return divPoster;
    }

    function clasificarYRenderizar(data) {

        if (!Array.isArray(data)) return;

        Object.values(contenedores).forEach(div => {
            if (div) div.innerHTML = "";
        });
        
        data.forEach(item => {
            const tarjeta = crearTarjeta(item);
            const esSerie = item.id.startsWith("serie_");
            let destino;
            if (esSerie) {
                destino = contenedores.series;
            } else {
                destino = contenedores.peliculas;
            }
            destino?.appendChild(tarjeta);
        });
        contenedores.peliculas.appendChild(botonPelicula);
        contenedores.series.appendChild(botonSerie);
        
    }

    async function filtrar() {

        let url = BASE_URL + "?";
        if (buscador?.value) {
            url += `nombre=${encodeURIComponent(buscador.value)}&`;
        }
        if (selectValoracion?.value) {
            url += `valoracion=${selectValoracion.value}&`;
        }
        if (selectGenero?.value) {
            url += `generos=${selectGenero.value}&`;
        }
        if (selectAnyo?.value) {
            url += `anyo=${selectAnyo.value}&`;
        }
        const res = await fetch(url);
        const data = await res.json();
        clasificarYRenderizar(data);
    }
    function aplicarDebounce(funcion, tiempo = 300) {
        clearTimeout(temporizador);
        temporizador = setTimeout(() => {
            funcion();
        }, tiempo);
    }

    selectValoracion?.addEventListener("change", filtrar);
    selectAnyo?.addEventListener("change", filtrar);
    selectGenero?.addEventListener("change", () => aplicarDebounce(filtrar));
    buscador?.addEventListener("input", () => aplicarDebounce(filtrar));

    fetch("http://98.84.88.91:8082/metrajes/obtenerDestacados?cantidad=4")
        .then(res => res.json())
        .then(data => clasificarYRenderizar(data))
        .catch(error => console.error(error));

});