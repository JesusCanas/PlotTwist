document.addEventListener("DOMContentLoaded", () => {
    const divPelicula = document.getElementById("pelis");

    fetch('http://98.84.88.91:8082/metrajes/obtenerTipo?tipoMetraje=PELICULA')
        .then(response => {
            if (!response.ok) throw new Error("Error en la red");
            return response.json();
        })
        .then(peliculas => {
            console.log(peliculas);
            
            peliculas.forEach(element => {
                const divPosterPelis = document.createElement('div');
                divPosterPelis.classList.add("poster");

                const imgPeliculas = document.createElement('img');
                imgPeliculas.src = element.imagen;
                imgPeliculas.alt = element.nombre; 
                const tituloPelicula = document.createElement('p'); 
                tituloPelicula.textContent = element.nombre;
                tituloPelicula.classList.add("titulo-texto");
                divPosterPelis.appendChild(imgPeliculas);
                divPosterPelis.appendChild(tituloPelicula);
                divPosterPelis.appendChild(imgPeliculas);
                divPelicula.appendChild(divPosterPelis);
            });
        })
        .catch(error => console.error("Error al obtener pelis:", error));
});