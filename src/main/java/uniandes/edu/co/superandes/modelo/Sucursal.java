package uniandes.edu.co.superandes.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "sucursal")
@ToString
public class Sucursal {

    @Id
    private Integer id;
    private String nombre;
    private Double tamaño;
    private String direccion;
    private String telefono;
    private String idCiudad;

    public Sucursal() {
        // Constructor vacío
    }

    public Sucursal(String nombre, Double tamaño, String direccion, String telefono, String idCiudad) {
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.direccion = direccion;
        this.telefono = telefono;
        this.idCiudad = idCiudad;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getTamaño() {
        return tamaño;
    }

    public void setTamaño(Double tamaño) {
        this.tamaño = tamaño;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(String idCiudad) {
        this.idCiudad = idCiudad;
    }
}
