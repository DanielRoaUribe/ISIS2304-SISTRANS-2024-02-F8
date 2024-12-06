package uniandes.edu.co.superandes.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uniandes.edu.co.superandes.modelo.OrdenCompra;
import uniandes.edu.co.superandes.modelo.Producto;

public interface OrdenCompraRepository extends MongoRepository<OrdenCompra, Integer> {

    // Método para insertar una nueva OrdenCompra
    @Query("{ $insert: { _id: ?0, fechaCreacion: ?1, fechaEntrega: ?2, estado: ?3, idProveedor: ?4, idSucursal: ?5, productos: ?6 } }")
    void insertarOrdenCompra(Integer id, String fechaCreacion, String fechaEntrega, String estado,
                             String idProveedor, String idSucursal, List<Producto> productos);

    // Método para eliminar una orden de compra por su ID
    void deleteById(Integer id);

    // El método save() de MongoRepository ya maneja las actualizaciones si el id ya existe
}
