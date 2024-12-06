package uniandes.edu.co.superandes.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "producto")
@ToString
public class Producto {

    @Id
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
    private String idCategoria;

    public Producto() {
        // Constructor vacío
    }

    public Producto(String nombre, Integer costoBodega, Integer precio, String presentacion, Integer cantidadPresentacion,
                    String unidadMedida, String especificacionesEmpacado, Integer volumenEmpaque,
                    String fechaExpiracion, String codigoBarras, Integer nivelReorden, String idCategoria) {
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
        this.idCategoria = idCategoria;
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

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }
}
