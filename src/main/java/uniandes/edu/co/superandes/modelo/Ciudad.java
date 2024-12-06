package uniandes.edu.co.superandes.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "ciudades")
@ToString
public class Ciudad {

    @Id
    private Integer id;
    private String nombre;

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }
    public Ciudad(){
        ;
    }
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

    
}
