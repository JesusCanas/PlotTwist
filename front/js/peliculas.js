document.addEventListener("DOMContentLoaded", () => {
    const divPelicula = document.getElementById("pelis");

    fetch('http://98.84.88.91:8082/metrajes/obtenerTipo?tipoMetraje=PELICULA')
        .then(response => {
            if (!response.ok) throw new Error("Error en la red");
            return response.json();
        })
        .then(peliculas => {
            console.log("Datos recibidos:", peliculas);
            
            peliculas.forEach((element, index) => {
                const divPosterPelis = document.createElement('div');
                divPosterPelis.classList.add("poster");

                const imgPeliculas = document.createElement('img');
                const numeroCarpeta = index + 1;
                
                // Generamos la URL dinámica
                const urlImagen = `https://proyecto-plottwist.s3.us-east-1.amazonaws.com/metrajes/peliculas/${numeroCarpeta}/poster.jpg`;
                
                // Esto te permite ver en la consola qué URL está fallando
                console.log(`Cargando imagen ${numeroCarpeta}:`, urlImagen);

                imgPeliculas.src = urlImagen;
                imgPeliculas.alt = element.titulo; 

                const tituloPelicula = document.createElement('p'); 
                tituloPelicula.textContent = element.titulo;
                tituloPelicula.classList.add("titulo-texto");

                divPosterPelis.appendChild(imgPeliculas);
                divPosterPelis.appendChild(tituloPelicula);
                
                if(divPelicula) {
                    divPelicula.appendChild(divPosterPelis);
                }
            });
        })
        .catch(error => console.error("Error al obtener pelis:", error));
});