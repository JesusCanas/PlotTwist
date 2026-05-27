document.addEventListener("DOMContentLoaded", () => {

    // Controles del formulario de filtrado
    const selectValoracion = document.getElementById("valoracion");
    const selectGenero = document.getElementById("genero");
    const selectAnyo = document.getElementById("anyo");
    const buscador = document.getElementById("buscador");

    // El mismo script sirve para la página de películas y la de series;
    // se detecta cuál es según el id del contenedor presente en el DOM
    const contenedor = document.getElementById("pelis") || document.getElementById("seri");
    const esPelicula = contenedor.id === "pelis";
    const TIPO_METRAJE = esPelicula ? 'PELICULA' : 'SERIE';

    // URL base compartida; el tipo de metraje ya va incluido como parámetro fijo
    const BASE_URL = `http://98.84.88.91:8082/metrajes/obtenerFiltrados?tipoMetraje=${TIPO_METRAJE}`;

    // Botón del logo: vuelve a la página principal
    const boton_principio = document.getElementById("logo");
    boton_principio.addEventListener("click", () => {
        window.location.href = "../index.html";
    });

    /**
     * Crea y devuelve una tarjeta (div.poster) con la imagen y el título
     * del metraje recibido. Al hacer clic navega a su página de detalle.
     * @param {Object} element - Objeto metraje con id, imagenURL y titulo.
     * @returns {HTMLElement} El div de la tarjeta listo para insertar en el DOM.
     */
    function crearTarjeta(element) {
        const divPoster = document.createElement('div');
        divPoster.classList.add("poster");
        divPoster.dataset.id = element.id; // Almacena el id en un atributo data- por si se necesita después

        const img = document.createElement('img');
        img.src = element.imagenURL;
        img.alt = element.titulo;

        const titulo = document.createElement('p');
        titulo.textContent = element.titulo;
        titulo.classList.add("titulo-texto");

        divPoster.appendChild(img);
        divPoster.appendChild(titulo);

        // Clic en la tarjeta navega al detalle del metraje
        divPoster.addEventListener('click', () => {
            window.location.href = `detalle-metraje.html?id=${element.id}`;
        });

        return divPoster;
    }

    /**
     * Construye la URL con los filtros activos y solicita los metrajes a la API.
     * Si no hay ningún filtro aplicado, usa el endpoint de resultados por fecha
     * como listado por defecto. Vacía el contenedor y renderiza las nuevas tarjetas.
     */
    async function filtrar() {
        let url = BASE_URL;

        // Se añade cada parámetro solo si el control tiene valor
        if (buscador?.value) url += `&nombre=${encodeURIComponent(buscador.value)}`;
        if (selectValoracion?.value) url += `&valoracion=${selectValoracion.value}`;
        if (selectGenero?.value) {
            // Soporte para selección múltiple: los géneros se unen con coma
            const generosSeleccionados = Array.from(selectGenero.selectedOptions)
                .map(option => option.value)
                .join(",");
            url += `&generos=${generosSeleccionados}`;
        }
        if (selectAnyo?.value) url += `&anyo=${selectAnyo.value}`;

        // Sin filtros activos se cae al endpoint de ordenación por fecha
        if (url === BASE_URL) {
            url = `http://98.84.88.91:8082/metrajes/obtenerPorFecha?tipoMetraje=${TIPO_METRAJE}`;
        }

        const res = await fetch(url);
        const data = await res.json();

        // Limpiamos el contenedor antes de renderizar los nuevos resultados
        contenedor.innerHTML = "";
        data.forEach(item => contenedor.appendChild(crearTarjeta(item)));
    }

    // Debounce: evita llamadas excesivas a la API mientras el usuario escribe o selecciona
    let temporizador;

    /**
     * Retrasa la ejecución de una función hasta que el usuario deje
     * de interactuar durante el tiempo indicado.
     * @param {Function} funcion - Función a ejecutar tras el retardo.
     * @param {number}   tiempo  - Milisegundos de espera (por defecto 300).
     */
    function aplicarDebounce(funcion, tiempo = 300) {
        clearTimeout(temporizador); // Cancela la llamada pendiente anterior
        temporizador = setTimeout(funcion, tiempo);
    }

    // Escuchadores de eventos para los filtros 
    selectValoracion?.addEventListener("change", filtrar);
    selectAnyo?.addEventListener("change", filtrar);
    selectGenero?.addEventListener("change", () => aplicarDebounce(filtrar, 300)); // Debounce para selección múltiple
    buscador?.addEventListener("input", () => aplicarDebounce(filtrar, 300));      // Debounce para escritura

    // Carga inicial: se ejecuta filtrar() sin argumentos para mostrar el listado por defecto
    filtrar();
});
