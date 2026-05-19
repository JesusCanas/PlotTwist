document.addEventListener("DOMContentLoaded", () => {
    const selectValoracion = document.getElementById("valoracion");
    const selectGenero = document.getElementById("genero");
    const selectAnyo = document.getElementById("anyo");
    const buscador = document.getElementById("buscador");

    const contenedor = document.getElementById("pelis") || document.getElementById("seri");
    
    const esPelicula = contenedor && contenedor.id === "pelis";
    const TIPO_METRAJE = esPelicula ? 'PELICULA' : 'SERIE';
    const BASE_URL = `http://localhost:8082/metrajes/obtenerFiltrados?tipoMetraje=${TIPO_METRAJE}`;

    let temporizador;

    function crearTarjeta(element) {
        const divPoster = document.createElement('div');
        divPoster.classList.add("poster");
        divPoster.dataset.id = element.id; 

        const img = document.createElement('img');
        img.src = element.imagenURL;
        img.alt = element.titulo;

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
        if (!contenedor) return; 

        let url = BASE_URL;
        if (buscador.value) url += `&nombre=${encodeURIComponent(buscador.value)}`;
        if (selectValoracion.value) url += `&valoracion=${selectValoracion.value}`;
        if (selectGenero.value) url += `&generos=${selectGenero.value}`;
        if (selectAnyo.value) url += `&anyo=${selectAnyo.value}`;   

        contenedor.innerHTML = "";

        const res = await fetch(url);
        const data = await res.json();
        
       
        data.forEach(item => contenedor.appendChild(crearTarjeta(item)));
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