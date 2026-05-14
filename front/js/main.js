const urlPeliculas = 'http://98.84.88.91:8082/metrajes/obtenerTipo?tipoMetraje=PELICULA';

document.addEventListener("DOMContentLoaded", () => {
    
    const boton_pelicula = document.getElementById('pelicula-contenedor');
    const boton_serie = document.getElementById('serie-contenedor');
    
    if (boton_pelicula && boton_serie) {
        boton_pelicula.addEventListener('click', () => {
            window.location.href = 'paginas/peliculas.html';
        });
        boton_serie.addEventListener('click', () => {
            window.location.href = 'paginas/serie.html';
        });
    }

});