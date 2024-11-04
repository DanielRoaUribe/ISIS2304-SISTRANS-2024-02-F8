package uniandes.edu.co.superandes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sucursales")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private Double tamaño;
    private String direccion;
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "id_ciudad", nullable = false) // Clave foránea hacia Ciudad
    private Ciudad idCiudad;

    public Sucursal() {
        // Constructor vacío
    }

    public Sucursal(String nombre, Double tamaño, String direccion, String telefono, Ciudad idCiudad) {
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

    public Ciudad getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Ciudad idCiudad) {
        this.idCiudad = idCiudad;
    }
}
