document.addEventListener("DOMContentLoaded", () => {
    // 1. Buscamos el contenedor principal UNA sola vez
    const divSerie = document.getElementById("seri");

    fetch('http://98.84.88.91:8082/metrajes/obtenerTipo?tipoMetraje=SERIE')
        .then(response => {
            if (!response.ok) throw new Error("Error en la red");
            return response.json();
        })
        .then(peliculas => {
            console.log(peliculas);
            
            peliculas.forEach(element => {
                const divPosterSerie = document.createElement('div');
                divPosterSerie.classList.add("poster");

                const imgSerie = document.createElement('img');
                imgSerie.src = element.imagen;
                imgSerie.alt = element.nombre; 

                divPosterSerie.appendChild(imgSerie);
                divSerie.appendChild(divPosterSerie);
            });
        })
        .catch(error => console.error("Error al obtener pelis:", error));
});