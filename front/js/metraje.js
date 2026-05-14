document.addEventListener("DOMContentLoaded", () => {

    const divPelicula = document.getElementById("pelis");
    const divSerie = document.getElementById("seri");
    const filtros = document.getElementsByClassName("filtros")
    const valoracion = document.getElementById("valoracion");
    const anyo = document.getElementById("anyo");
    const genero = document.getElementById("genero");

    function crearTarjeta(element, index, tipo) {
        const divPoster = document.createElement('div');
        divPoster.classList.add("poster");

        const img = document.createElement('img');
        const numeroCarpeta = index + 1;
        
        img.src = `https://proyecto-plottwist.s3.us-east-1.amazonaws.com/metrajes/${tipo}/${numeroCarpeta}/poster.jpg`;
        img.alt = element.titulo; 

        const titulo = document.createElement('p'); 
        titulo.textContent = element.titulo;
        titulo.classList.add("titulo-texto");

        divPoster.appendChild(img);
        divPoster.appendChild(titulo);
        return divPoster;
    }
    if (divPelicula) {
        fetch('http://98.84.88.91:8082/metrajes/obtenerTipo?tipoMetraje=PELICULA')
            .then(res => res.json())
            .then(data => {
                data.forEach((item, i) => divPelicula.appendChild(crearTarjeta(item, i, 'peliculas')));
            });
        fetch('http://98.84.88.91:8082/metrajes/obtenerTipo?tipoMetraje=PELICULA')
    }
    if (divSerie) {
        fetch('http://98.84.88.91:8082/metrajes/obtenerTipo?tipoMetraje=SERIE')
            .then(res => res.json())
            .then(data => {
                data.forEach((item, i) => divSerie.appendChild(crearTarjeta(item, i, 'series')));
            });
    }
});