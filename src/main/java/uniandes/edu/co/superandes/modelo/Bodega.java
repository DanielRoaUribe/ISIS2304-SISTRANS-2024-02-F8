package uniandes.edu.co.superandes.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "bodega")
@ToString
public class Bodega {

    @Id
    private Integer id;
    private String nombre;
    private Integer tamaño;
    private String idSucursal;
    private List<Producto> productos;

    // Constructor vacío
    public Bodega() {}

    // Constructor con parámetros
    public Bodega(String nombre, Integer tamaño, String idSucursal, List<Producto> productos) {
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.idSucursal = idSucursal;
        this.productos = productos;
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

    public Integer getTamaño() {
        return tamaño;
    }

    public void setTamaño(Integer tamaño) {
        this.tamaño = tamaño;
    }

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}