document.addEventListener("DOMContentLoaded", () => {

    // ── Botones de navegación a las páginas de películas y series ──
    const botonPelicula = document.getElementById("pelicula-contenedor");
    const botonSerie = document.getElementById("serie-contenedor");

    // ── Controles del formulario de filtrado ──
    const selectValoracion = document.getElementById("valoracion");
    const selectGenero = document.getElementById("genero");
    const selectAnyo = document.getElementById("anyo");
    const buscador = document.getElementById("buscador");

    // ── Contenedores del DOM donde se renderizan las tarjetas ──
    const contenedores = {
        peliculas: document.getElementById("pelis"),
        series: document.getElementById("serie"),
        metrajes: document.getElementById("metrajes") // Usado cuando hay filtro activo
    };

    const BASE_URL = "http://98.84.88.91:8082/metrajes/obtenerTodosFiltrados";

    let temporizador; // Referencia al temporizador del debounce


    // ── Navegación al hacer clic en los botones de sección ──
    botonPelicula.addEventListener("click", () => {
        window.location.href = "paginas/peliculas.html";
    });

    botonSerie.addEventListener("click", () => {
        window.location.href = "paginas/serie.html";
    });


    /**
     * Crea y devuelve una tarjeta (div.poster) con la imagen y título
     * del elemento recibido, y añade navegación al detalle al hacer clic.
     * @param {Object} elemento - Objeto con los datos del metraje (id, imagenURL, titulo).
     * @returns {HTMLElement} El div de la tarjeta listo para insertar en el DOM.
     */
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

        // Al hacer clic se navega a la página de detalle pasando el id por query param
        divPoster.addEventListener("click", () => {
            window.location.href =
                `paginas/detalle-metraje.html?id=${elemento.id}`;
        });

        return divPoster;
    }

    /**
     * Limpia los contenedores, clasifica los datos en películas/series
     * y los renderiza en el contenedor correspondiente.
     * Si hay un filtro activo, todos los resultados se muestran juntos en
     * el contenedor genérico de metrajes.
     * @param {Array} data - Array de objetos metraje devueltos por la API.
     * @param {boolean} hayFiltro - Indica si hay algún filtro aplicado.
     */
    function clasificarYRenderizar(data, hayFiltro) {

        if (!Array.isArray(data)) return;

        // Vaciamos los tres contenedores antes de renderizar
        Object.values(contenedores).forEach(div => {
            if (div) div.innerHTML = "";
        });

        // Separamos películas (sin temporadas) de series (con temporadas)
        const peliculas = data.filter(item => !item.numTemporadas);
        const series = data.filter(item => item.numTemporadas);

        if (hayFiltro) {
            // Con filtro activo: todos los resultados van al contenedor único
            data.forEach(item => contenedores.metrajes?.appendChild(crearTarjeta(item)));
        } else {
            // Sin filtro: cada tipo va a su sección correspondiente
            peliculas.forEach(item => contenedores.peliculas?.appendChild(crearTarjeta(item)));
            series.forEach(item => contenedores.series?.appendChild(crearTarjeta(item)));
        }

        // Reinsertamos los botones de navegación al final de cada sección
        contenedores.peliculas.appendChild(botonPelicula);
        contenedores.series.appendChild(botonSerie);
    }

    /**
     * Construye la URL con los filtros activos y lanza la petición a la API.
     * Si no hay ningún filtro, solicita los metrajes destacados.
     * También muestra u oculta los botones de sección según corresponda.
     */
    async function filtrar() {

        let url = BASE_URL + "?";
        let filtroExiste = false;

        // Añadimos a la URL solo los parámetros que tengan valor
        if (buscador?.value) { url += `&nombre=${encodeURIComponent(buscador.value)}`; filtroExiste = true; }
        if (selectValoracion?.value) { url += `&valoracion=${selectValoracion.value}`; filtroExiste = true; }
        if (selectGenero?.value) {
            // Soporte para selección múltiple: unimos los valores con coma
            const generosSeleccionados = Array.from(selectGenero.selectedOptions)
                .map(option => option.value)
                .join(",");
            url += `&generos=${generosSeleccionados}`;
            filtroExiste = true;
        }
        if (selectAnyo?.value) { url += `&anyo=${selectAnyo.value}`; filtroExiste = true; }

        // Sin filtros activos usamos el endpoint de destacados
        if (!filtroExiste) {
            url = "http://98.84.88.91:8082/metrajes/obtenerDestacados?cantidad=7";
        }

        const res = await fetch(url);
        const data = await res.json();
        clasificarYRenderizar(data, filtroExiste);

        // Ocultamos los botones de sección cuando se está filtrando
        if (filtroExiste) {
            botonPelicula.style.display = "none";
            botonSerie.style.display = "none";
        } else {
            botonPelicula.style.display = "";
            botonSerie.style.display = "";
        }
    }

    /**
     * Aplica debounce a una función para evitar llamadas excesivas
     * (útil en eventos de escritura o selección múltiple).
     * @param {Function} funcion - Función a ejecutar tras el retardo.
     * @param {number} tiempo - Milisegundos de espera (por defecto 300).
     */
    function aplicarDebounce(funcion, tiempo = 300) {
        clearTimeout(temporizador);         // Cancela la ejecución pendiente anterior
        temporizador = setTimeout(() => {
            funcion();
        }, tiempo);
    }

    // ── Escuchadores de eventos para los filtros ──
    selectValoracion?.addEventListener("change", filtrar);
    selectAnyo?.addEventListener("change", filtrar);
    selectGenero?.addEventListener("change", () => aplicarDebounce(filtrar));   // Debounce para selección múltiple
    buscador?.addEventListener("input", () => aplicarDebounce(filtrar));        // Debounce para escritura

    // ── Carga inicial: se muestran los metrajes destacados sin filtro ──
    fetch("http://98.84.88.91:8082/metrajes/obtenerDestacados?cantidad=7")
        .then(res => res.json())
        .then(data => clasificarYRenderizar(data))
        .catch(error => console.error(error));

});
