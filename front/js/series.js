document.addEventListener("DOMContentLoaded", () => {
    const divSerie = document.getElementById("seri");

    fetch('http://98.84.88.91:8082/metrajes/obtenerTipo?tipoMetraje=SERIE')
        .then(response => {
            if (!response.ok) throw new Error("Error en la red");
            return response.json();
        })
        .then(peliculas => {
            console.log("Datos recibidos:", series);
            
            series.forEach((element, index) => {
                const divPosterSerie= document.createElement('div');
                divPosterSeries.classList.add("poster");

                const imgSeries = document.createElement('img');
                const numeroCarpeta = index + 1;
                
                
                const urlImagen = `https://proyecto-plottwist.s3.us-east-1.amazonaws.com/metrajes/series/${numeroCarpeta}/poster.jpg`;
                
               
                console.log(`Cargando imagen ${numeroCarpeta}:`, urlImagen);

                imgPeliculas.src = urlImagen;
                imgPeliculas.alt = element.titulo; 

                const tituloSerie = document.createElement('p'); 
                tituloSerie.textContent = element.titulo;
                tituloSerie.classList.add("titulo-texto");

                divPosterSerie.appendChild(imgSeries);
                divPosterSerie.appendChild(tituloSerie);
                
                if(divSerie) {
                    divSerie.appendChild(divPosterSerie);
                }
            });
        })
        .catch(error => console.error("Error al obtener series:", error));
});