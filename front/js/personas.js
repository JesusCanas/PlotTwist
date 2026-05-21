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
        imagen.alt= elemento.nombre +" " +element.apellido;
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
    }
    function crearPoster(elemento) {
        const seccion_poster = document.querySelector(".persona-metrajes");
        const li = document.createElement("li");
        const img = document.createElement("img");
        const span = document.createElement("span");
        li.className = "metraje-card";
        img.src = metraje.imagenURL;
        img.alt = metraje.titulo;
        span.textContent = metraje.titulo;
        li.addEventListener("click", () => {
            window.location.href = `detalle-metraje.html?id=${metraje.id}`;
        });
        const peliculas = data.filter(item => !item.numTemporadas);
        const series = data.filter(item => item.numTemporadas);
        if(peliculas){
            tipoMetraje.textContent="PELICULAS";
        }
        else {
            tipoMetraje.textContent="SERIES";
        }
        seccion_poster.appendChild(tipoMetraje);
        li.appendChild(img);
        li.appendChild(span);
        seccion_poster.appendChild(li);
    }

    function crearPersona(elemento){
        crearPersonaFoto(elemento);
        crearPersonaBiografia(elemento);
        crearPoster(elemento);
    }
    fetch(URL_PERSONA)
    .then (res => res.json())
    .then (data => crearPersona(data));
});