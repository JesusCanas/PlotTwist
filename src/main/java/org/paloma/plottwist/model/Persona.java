package org.paloma.plottwist.model;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Clase que representa una persona (actor o director).
 * Contiene información personal como nombre, apellido, fecha de nacimiento,
 * nacionalidad, biografía e imagen. También almacena referencias a los metrajes
 * en los que la persona ha participado.
 * Esta clase se mapea a la colección "persona" en la base de datos MongoDB.
 * 
 * @author MiguelSg77
 * @version 1.0
 */
@Document(collection = "persona")
public class Persona {
    
    @Id
    private String id;
    private String nombre;
    @Field("FechaNacimiento")
    private LocalDate fechaDeNacimiento;
    private String nacionalidad;
    @Field("imagen")
    private String imagenURL;
    private String apellido; 
    private String biografia;
    @Field("idMetrajes")
    private List<String> metrajesId;
    private List<Metraje> metrajes;

    /**
     * Constructor parametrizado de Persona.
     * 
     * @param nombre Nombre de la persona
     * @param apellido Apellido de la persona
     * @param biografia Biografía de la persona
     * @param fechaDeNacimiento Fecha de nacimiento
     * @param nacionalidad Nacionalidad de la persona
     * @param metrajesId Lista de IDs de metrajes en los que participa
     */
    public Persona(String nombre, String apellido, String biografia, LocalDate fechaDeNacimiento, String nacionalidad,
            List<String> metrajesId) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.biografia = biografia;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.nacionalidad = nacionalidad;
        this.metrajesId = metrajesId;
    }

    /**
     * Constructor vacío de Persona.
     */
    public Persona() {

    }

    // Getters and Setters
    /**
     * Obtiene el identificador único de la persona.
     * 
     * @return ID de la persona
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador único de la persona.
     * 
     * @param id ID de la persona
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la persona.
     * 
     * @return Nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la persona.
     * 
     * @param nombre Nombre de la persona
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la fecha de nacimiento de la persona.
     * 
     * @return Fecha de nacimiento
     */
    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    /**
     * Establece la fecha de nacimiento de la persona.
     * 
     * @param fechaDeNacimiento Fecha de nacimiento
     */
    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    /**
     * Obtiene la nacionalidad de la persona.
     * 
     * @return Nacionalidad de la persona
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Establece la nacionalidad de la persona.
     * 
     * @param nacionalidad Nacionalidad de la persona
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Obtiene la URL de la imagen de perfil de la persona.
     * 
     * @return URL de la imagen
     */
    public String getImagenURL() {
        return imagenURL;
    }

    /**
     * Establece la URL de la imagen de perfil de la persona.
     * 
     * @param imagenURL URL de la imagen
     */
    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    /**
     * Obtiene el apellido de la persona.
     * 
     * @return Apellido de la persona
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido de la persona.
     * 
     * @param apellido Apellido de la persona
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene la biografía de la persona.
     * 
     * @return Biografía de la persona
     */
    public String getBiografia() {
        return biografia;
    }

    /**
     * Establece la biografía de la persona.
     * 
     * @param biografia Biografía de la persona
     */
    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    /**
     * Obtiene la lista de IDs de metrajes en los que participa la persona.
     * 
     * @return Lista de IDs de metrajes
     */
    public List<String> getMetrajesId() {
        return metrajesId;
    }

    /**
     * Establece la lista de IDs de metrajes en los que participa la persona.
     * 
     * @param metrajesId Lista de IDs de metrajes
     */
    public void setMetrajesId(List<String> metrajesId) {
        this.metrajesId = metrajesId;
    }

    /**
     * Obtiene la lista de objetos Metraje en los que participa la persona.
     * 
     * @return Lista de metrajes
     */
    public List<Metraje> getMetrajes() {
        return metrajes;
    }

    /**
     * Establece la lista de objetos Metraje en los que participa la persona.
     * 
     * @param metrajes Lista de metrajes
     */
    public void setMetrajes(List<Metraje> metrajes) {
        this.metrajes = metrajes;
    }

}
