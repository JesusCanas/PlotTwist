document.addEventListener("DOMContentLoaded", () => {
    const parametros = new URLSearchParams(window.location.search);
    const idPersona = parametros.get("idPersona");
    const URL_PERSONA = `http://98.84.88.91:8082/personas/mostrarDestacados?cantidad=3&idPersona=${idPersona}`;

    function crearPersonaFoto(elemento){
        const seccion_personaFoto = document.querySelector(".persona-foto");
        const imagen = document.createElement("img");
        const lista = document.createElement("ul");
        const nombrePersona = document.createElement("h1");
        const puntoFecha = document.createElement("li");
        const puntoNacionalidad = document.createElement("li");
        imagen.src = elemento.imagenURL;
        imagen.alt= elemento.nombre + ' ' + elemento.apellido;
        nombrePersona.textContent=elemento.nombre + ' ' + elemento.apellido;
        puntoFecha.innerHTML='<strong>Fecha de nacimiento:</strong>' + elemento.fechaDeNacimiento;
        puntoNacionalidad.innerHTML = '<strong>Nacionalidad:</strong>' + elemento.nacionalidad;
        seccion_personaFoto.appendChild(imagen);
        seccion_personaFoto.appendChild(nombrePersona);
        seccion_personaFoto.appendChild(lista);
        lista.appendChild(puntoFecha);
        lista.appendChild(puntoNacionalidad);
    }

    function crearPersonaBiografia(elemento){
        const seccion_biografia = document.querySelector(".persona-biografia");
        const parrafo_biografia = document.createElement("p");  
        parrafo_biografia.textContent = elemento.biografia;
        seccion_biografia.appendChild(parrafo_biografia);
    }

    function crearTarjeta(item) {
        const lista = document.createElement("li");
        const img = document.createElement("img");
        const titulo = document.createElement("span");

        lista.className = "metraje-card";
        img.src = item.imagenURL;
        img.alt = item.titulo;
        titulo.textContent = item.titulo;

        lista.addEventListener("click", () => {
            window.location.href = `detalle-metraje.html?id=${item.id}`;
        });

        lista.appendChild(img);
        lista.appendChild(titulo);
        return lista;
    }

    function crearPoster(elemento) {
        const seccion_poster = document.querySelector(".persona-metrajes");
        const todosLosMetrajes = elemento.metrajes || []; 
        const peliculas = todosLosMetrajes.filter(item => !item.numTemporadas);
        const series = todosLosMetrajes.filter(item => item.numTemporadas);
        const encabezado_pelis = document.createElement("h2");

        encabezado_pelis.textContent = "Peliculas";
        const lista_pelis= document.createElement("ul");
        lista_pelis.className = "metrajes-lista";
        const encabezado_series = document.createElement("h2");
        encabezado_series.textContent = "Series";
        const lista_series = document.createElement("ul");
        lista_series.className = "metrajes-lista";

        if (peliculas.length > 0) {
            seccion_poster.appendChild(encabezado_pelis);
            seccion_poster.appendChild(lista_pelis);
        }
        if (series.length > 0) {
            seccion_poster.appendChild(encabezado_series);
            seccion_poster.appendChild(lista_series);
        }
        const contenedores = {
            peliculas: lista_pelis,
            series: lista_series
        };

        peliculas.forEach(item => contenedores.peliculas?.appendChild(crearTarjeta(item)));
        series.forEach(item => contenedores.series?.appendChild(crearTarjeta(item)));
    }

    function crearPersona(elemento){
        crearPersonaFoto(elemento);
        crearPersonaBiografia(elemento);
        crearPoster(elemento);
    }

   fetch(URL_PERSONA)
    .then (res => res.json())
    .then (elemento => crearPersona(elemento)) // Cambiado 'data =>' por 'elemento =>'
    .catch(err => console.error(err));
});
