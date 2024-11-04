package uniandes.edu.co.superandes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordenes_compra")
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String fechaCreacion;
    private String fechaEntrega;
    private String estado;
    private Integer precio;
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false) // Clave foránea hacia Proveedor
    private Proveedor idProveedor;

    @ManyToOne
    @JoinColumn(name = "id_sucursal", nullable = false) // Clave foránea hacia Sucursal
    private Sucursal idSucursal;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false) // Clave foránea hacia Producto
    private Producto idProducto; // Nueva relación

    public OrdenCompra() {
        // Constructor vacío
    }

    public OrdenCompra(String fechaCreacion, String fechaEntrega, String estado, Integer precio, Integer cantidad, Proveedor idProveedor, Sucursal idSucursal, Producto idProducto) {
        this.fechaCreacion = fechaCreacion;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.precio = precio;
        this.cantidad = cantidad;
        this.idProveedor = idProveedor;
        this.idSucursal = idSucursal;
        this.idProducto = idProducto; // Inicializar la relación con Producto
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Sucursal getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursal idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }
}
