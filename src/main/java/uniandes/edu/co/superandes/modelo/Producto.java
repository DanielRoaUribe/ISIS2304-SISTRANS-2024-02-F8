package uniandes.edu.co.superandes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombre;
    private Integer costoBodega;
    private Integer precio;
    private String presentacion;
    private Integer cantidadPresentacion;
    private String unidadMedida;
    private String especificacionesEmpacado;
    private Integer volumenEmpaque;
    private String fechaExpiracion;
    private String codigoBarras;
    private Integer nivelReorden;

    @ManyToOne
    @JoinColumn(name = "id_bodega", nullable = false) // Clave foránea hacia Bodega
    private Bodega idBodega;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false) // Clave foránea hacia Categoria
    private Categoria idCategoria;

    @ManyToOne
    @JoinColumn(name = "id_orden_compra", nullable = true) // Clave foránea hacia OrdenCompra
    private OrdenCompra idOrdenCompra;

    public Producto() {
        // Constructor vacío
    }

    public Producto(String nombre, Integer costoBodega, Integer precio, String presentacion, Integer cantidadPresentacion,
                    String unidadMedida, String especificacionesEmpacado, Integer volumenEmpaque, String fechaExpiracion,
                    String codigoBarras, Integer nivelReorden, Bodega idBodega, Categoria idCategoria, OrdenCompra idOrdenCompra) {
        this.nombre = nombre;
        this.costoBodega = costoBodega;
        this.precio = precio;
        this.presentacion = presentacion;
        this.cantidadPresentacion = cantidadPresentacion;
        this.unidadMedida = unidadMedida;
        this.especificacionesEmpacado = especificacionesEmpacado;
        this.volumenEmpaque = volumenEmpaque;
        this.fechaExpiracion = fechaExpiracion;
        this.codigoBarras = codigoBarras;
        this.nivelReorden = nivelReorden;
        this.idBodega = idBodega;
        this.idCategoria = idCategoria;
        this.idOrdenCompra = idOrdenCompra;
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

    public Integer getCostoBodega() {
        return costoBodega;
    }

    public void setCostoBodega(Integer costoBodega) {
        this.costoBodega = costoBodega;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public Integer getCantidadPresentacion() {
        return cantidadPresentacion;
    }

    public void setCantidadPresentacion(Integer cantidadPresentacion) {
        this.cantidadPresentacion = cantidadPresentacion;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getEspecificacionesEmpacado() {
        return especificacionesEmpacado;
    }

    public void setEspecificacionesEmpacado(String especificacionesEmpacado) {
        this.especificacionesEmpacado = especificacionesEmpacado;
    }

    public Integer getVolumenEmpaque() {
        return volumenEmpaque;
    }

    public void setVolumenEmpaque(Integer volumenEmpaque) {
        this.volumenEmpaque = volumenEmpaque;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Integer getNivelReorden() {
        return nivelReorden;
    }

    public void setNivelReorden(Integer nivelReorden) {
        this.nivelReorden = nivelReorden;
    }

    public Integer getIdBodega() {
        return idBodega.getId();
    }

    public void setIdBodega(Bodega idBodega) {
        this.idBodega = idBodega;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdOrdenCompra() {
        return idOrdenCompra.getId();
    }

    public void setIdOrdenCompra(OrdenCompra idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }
}
