document.addEventListener("DOMContentLoaded", () => {
    const selectValoracion = document.getElementById("valoracion");
    const selectGenero = document.getElementById("genero");
    const selectAnyo = document.getElementById("anyo");

    const divPelicula = document.getElementById("pelis");
    const divSerie = document.getElementById("seri");

    function crearTarjeta(element, tipo) {
        const divPoster = document.createElement('div');
        divPoster.classList.add("poster");

        const img = document.createElement('img');
        img.src = element.imagen;
        img.alt = element.titulo;

        const titulo = document.createElement('p');
        titulo.textContent = element.titulo;
        titulo.classList.add("titulo-texto");

        divPoster.appendChild(img);
        divPoster.appendChild(titulo);
        return divPoster;
    }

    function filtrar() {
        const valoracionVal = selectValoracion.value;
        const generoVal = selectGenero.value;
        const anyoVal = selectAnyo.value;

        let urlPeliculas = 'http://98.84.88.91:8082/metrajes/obtenerFiltrados?tipoMetraje=PELICULA';
        let urlSeries = 'http://98.84.88.91:8082/metrajes/obtenerFiltrados?tipoMetraje=SERIE';

        if (valoracionVal) {
            urlPeliculas += `&valoracion=${valoracionVal}`;
            urlSeries += `&valoracion=${valoracionVal}`;
        }
        if (generoVal) {
            urlPeliculas += `&generos=${generoVal}`;
            urlSeries += `&generos=${generoVal}`;
        }
        if (anyoVal) {
            urlPeliculas += `&anyo=${anyoVal}`;
            urlSeries += `&anyo=${anyoVal}`;
        }

        console.log(urlPeliculas);
        console.log(urlSeries);
    }

    selectValoracion.addEventListener("change", filtrar);
    selectGenero.addEventListener("change", filtrar);
    selectAnyo.addEventListener("change", filtrar);

    if (divPelicula) {
        fetch('http://98.84.88.91:8082/metrajes/obtenerTipo?tipoMetraje=PELICULA')
            .then(res => res.json())
            .then(data => {
                data.forEach((item) => {
                    divPelicula.appendChild(crearTarjeta(item, 'peliculas'));
                });
            })
            .catch(error => console.error("Error cargando películas:", error));
    }

    if (divSerie) {
        fetch('http://98.84.88.91:8082/metrajes/obtenerTipo?tipoMetraje=SERIE')
            .then(res => res.json())
            .then(data => {
                data.forEach((item) => {
                    divSerie.appendChild(crearTarjeta(item, 'series'));
                });
            })
            .catch(error => console.error("Error cargando series:", error));
    }
});