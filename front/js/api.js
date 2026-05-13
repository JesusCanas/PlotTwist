

document.addEventListener("DOMContentLoaded", () => {
    const boton_pelicula = document.getElementById('pelicula-contenedor');
    const boton_serie = document.getElementById('serie-contenedor');
    // Verificamos que el elemento exista para evitar errores en la consola
    if (boton_pelicula || boton_serie) {
        boton_pelicula.addEventListener('click', () => {
            window.location.href = 'paginas/peliculas.html';
        });
        boton_serie.addEventListener('click', () => {
            window.location.href = 'paginas/serie.html';
        });
    }
    
});