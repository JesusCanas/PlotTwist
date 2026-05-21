document.addEventListener("DOMContentLoaded", () => {
    const selectValoracion = document.getElementById("valoracion");
    const selectGenero = document.getElementById("genero");
    const selectAnyo = document.getElementById("anyo");
    const buscador = document.getElementById("buscador");

    const contenedor = document.getElementById("pelis") || document.getElementById("seri");

    if (!contenedor) return;

    const esPelicula = contenedor.id === "pelis";
    const TIPO_METRAJE = esPelicula ? 'PELICULA' : 'SERIE';
    const BASE_URL = `http://98.84.88.91:8082/metrajes/obtenerFiltrados?tipoMetraje=${TIPO_METRAJE}`;

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
        let url = BASE_URL;

        if (buscador?.value) url += `&nombre=${encodeURIComponent(buscador.value)}`;
        if (selectValoracion?.value) url += `&valoracion=${selectValoracion.value}`;
        if (selectGenero?.value) {
            const generosSeleccionados = Array.from(selectGenero.selectedOptions)
                .map(option => option.value)
                .join(",");
            url += `&generos=${generosSeleccionados}`;
        }
        if (selectAnyo?.value) url += `&anyo=${selectAnyo.value}`;
        if (url == BASE_URL) url = `http://98.84.88.91:8082/metrajes/obtenerPorFecha?tipoMetraje=${TIPO_METRAJE}`;
        const res = await fetch(url);
        const data = await res.json();
        contenedor.innerHTML = "";
        data.forEach(item => contenedor.appendChild(crearTarjeta(item)));
    }

    let temporizador;
    function aplicarDebounce(funcion, tiempo = 300) {
        clearTimeout(temporizador);
        temporizador = setTimeout(funcion, tiempo);
    }

    selectValoracion?.addEventListener("change", filtrar);
    selectAnyo?.addEventListener("change", filtrar);
    selectGenero?.addEventListener("change", () => aplicarDebounce(filtrar, 300));
    buscador?.addEventListener("input", () => aplicarDebounce(filtrar, 300));

    filtrar();
});