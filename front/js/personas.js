document.addEventListener("DOMContentLoaded", () => {
    const parametros = new URLSearchParams(window.location.search);
    const id = parametros.get("id");
    const URL_PERSONA = `http://localhost:8082/personas/mostrarDestacados?cantidad=3&idPersona=id{}`
});