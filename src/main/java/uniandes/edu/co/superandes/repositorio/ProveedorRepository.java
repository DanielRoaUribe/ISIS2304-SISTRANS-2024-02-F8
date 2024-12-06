package uniandes.edu.co.superandes.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.superandes.modelo.Proveedor;

public interface ProveedorRepository extends MongoRepository<Proveedor, Integer> {

    // Insertar un nuevo proveedor (se hace automáticamente con save)
    @Query("{ $insert: { _id: ?0, nit: ?1, nombre: ?2, direccion: ?3, personaContacto: ?4, telefonoContacto: ?5 } }")
    void insertarProveedor(int id, String nit, String nombre, String direccion, String personaContacto, String telefonoContacto);

    // Método para borrar un proveedor por su ID
    void deleteById(Integer id);
}
