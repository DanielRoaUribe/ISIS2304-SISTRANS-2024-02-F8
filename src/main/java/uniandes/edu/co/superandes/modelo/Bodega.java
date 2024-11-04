package uniandes.edu.co.superandes.modelo;

import jakarta.persistence.*;


@Entity
@Table(name = "bodegas")
public class Bodega {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombre;
    private Integer tamaño; // En algún tipo de medida (por ejemplo, metros cuadrados)
    private Integer costoPromedio;



    @ManyToOne
    @JoinColumn(name = "id_sucursal", nullable = false) // Clave foránea hacia Sucursal
    private Sucursal idSucursal;

    public Bodega() {
        // Constructor vacío
    }

    public Bodega(String nombre, Integer tamaño, Integer costoPromedio, Sucursal idSucursal) {
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.costoPromedio = costoPromedio;
        this.idSucursal = idSucursal;
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

    public Integer getCostoPromedio() {
        return costoPromedio;
    }

    public void setCostoPromedio(Integer costoPromedio) {
        this.costoPromedio = costoPromedio;
    }


    public Sucursal getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursal idSucursal) {
        this.idSucursal = idSucursal;
    }
}
