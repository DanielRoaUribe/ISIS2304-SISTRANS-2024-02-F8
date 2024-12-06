package uniandes.edu.co.superandes.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.superandes.modelo.Bodega;
import uniandes.edu.co.superandes.modelo.Producto;

public interface BodegaRepository extends MongoRepository<Bodega, Integer> {
    
    // Insertar una nueva bodega (se hace automáticamente con save)
    @Query("{ $insert: { _id: ?0, nombre: ?1, tamaño: ?2, idSucursal: ?3, productos: ?4 } }")
    void insertarBodega(int id, String nombre, Integer tamaño, String idSucursal, List<Producto> productos);

    // Método para borrar una bodega por su ID
    void deleteById(Integer id);
}