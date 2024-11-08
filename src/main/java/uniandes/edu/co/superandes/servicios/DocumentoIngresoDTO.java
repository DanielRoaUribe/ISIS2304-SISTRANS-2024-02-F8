package uniandes.edu.co.superandes.servicios;

public class DocumentoIngresoDTO {
    private Integer numeroDocumento;
    private String fechaIngreso;
    private String nombreProveedor;
    private String nombreSucursal;
    private String nombreBodega;

    public DocumentoIngresoDTO(Integer numeroDocumento, String fechaIngreso, String nombreProveedor, String nombreSucursal, String nombreBodega) {
        this.numeroDocumento = numeroDocumento;
        this.fechaIngreso = fechaIngreso;
        this.nombreProveedor = nombreProveedor;
        this.nombreSucursal = nombreSucursal;
        this.nombreBodega = nombreBodega;
    }

    public Integer getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getNombreBodega() {
        return nombreBodega;
    }

    public void setNombreBodega(String nombreBodega) {
        this.nombreBodega = nombreBodega;
    }

}
