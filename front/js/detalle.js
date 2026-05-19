document.addEventListener("DOMContentLoaded", () => {
    const parametros = new URLSearchParams(window.location.search)
    const id = parametros.get("id");
   const URL_DETALLE = `http://localhost:8082/metrajes/obtenerDetalles?metraje=${id}`;

    function crear(element) {
        const seccion_poster = document.querySelector(".detalle-poster");
        const seccion_sinopsis = document.querySelector(".detalle-sinopsis");
        const seccion_info = document.querySelector(".detalle-info");
        const genero = document.getElementById(".genero-detalle");
        const valoracion = document.getElementById("valoracion-detalle");
        const director = document.getElementById("director-detalle");
        const actor = document.getElementById("actor-detalle");

        const img= document.createElement('img');
        const titulo = document.createElement("h1");
        img.src = element.imagenURL;
        img.alt = element.titulo;
        titulo.textContent = element.titulo;
        titulo.id="detalle-titulo";
        seccion_poster.appendChild(img);
        seccion_poster.appendChild(titulo);

        const p_sinopsis = document.createElement("p");
        p_sinopsis = element.sinopsis;
        
       

    }
});
