const urlPeliculas = 'http://98.84.88.91:8082/metrajes/obtenerTipo?tipoMetraje=PELICULA';
document.addEventListener("DOMContentLoaded",()=>{
    const divPelicula = document.getElementById("pelis");
    
    fetch(urlPeliculas)
        .then(response => response.json())
        .then(peliculas => {
            console.log(peliculas);
            peliculas.forEach(element => {
                const divPosterPelis = document.createElement('div');
                divPosterPelis.classList.add("poster");
                const imgPeliculas = document.createElement('img');
                imgPeliculas.src = element.imagen;
                divPosterPelis.appendChild(imgPeliculas);
                divPelicula.appendChild(divPosterPelis);
            });
        });
});