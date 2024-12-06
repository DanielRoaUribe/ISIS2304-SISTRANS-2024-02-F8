package uniandes.edu.co.superandes.modelo;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ordenCompra")
public class OrdenCompra {

    @Id
    private Integer id;
    private String fechaCreacion;
    private String fechaEntrega;
    private String estado;
    private String idProveedor;
    private String idSucursal;
    private List<Producto> productos;  // Lista de productos

    // Constructor vacío
    public OrdenCompra() {}

    // Constructor con parámetros
    public OrdenCompra(Integer id, String fechaCreacion, String fechaEntrega, String estado,
                       String idProveedor, String idSucursal, List<Producto> productos) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.idProveedor = idProveedor;
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

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
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
