package uniandes.edu.co.superandes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingreso_productos")
public class IngresoProductos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String fechaIngreso;
    private Integer sucursal;
    private Integer bodega;
    private Integer proveedor;
    private String nombreProducto;
    private Integer cantidad;
    private Integer precio;

    // Constructor completo
    public IngresoProductos(String fechaIngreso, Integer sucursal, Integer bodega, Integer proveedor, String nombreProducto, Integer cantidad, Integer precio) {
        this.fechaIngreso = fechaIngreso;
        this.sucursal = sucursal;
        this.bodega = bodega;
        this.proveedor = proveedor;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
    }

    // Constructor vacío
    public IngresoProductos() {
        ;
    }

    
    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getSucursal() {
        return sucursal;
    }

    public void setSucursal(Integer sucursal) {
        this.sucursal = sucursal;
    }

    public Integer getBodega() {
        return bodega;
    }

    public void setBodega(Integer bodega) {
        this.bodega = bodega;
    }

    public Integer getProveedor() {
        return proveedor;
    }

    public void setProveedor(Integer proveedor) {
        this.proveedor = proveedor;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
