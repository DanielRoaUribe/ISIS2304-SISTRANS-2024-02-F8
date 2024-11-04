package uniandes.edu.co.superandes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.superandes.modelo.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    // Obtener todos los proveedores
    @Query(value = "SELECT * FROM proveedores", nativeQuery = true)
    Collection<Proveedor> darProveedores();

    // Obtener un proveedor por ID
    @Query(value = "SELECT * FROM proveedores WHERE id = :id", nativeQuery = true)
    Proveedor darProveedor(@Param("id") int id);

    // Insertar un nuevo proveedor
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO proveedores (id, nit, nombre, direccion, persona_contacto, telefono_contacto) " +
                   "VALUES (proveedores_seq.nextval, :nit, :nombre, :direccion, :personaContacto, :telefonoContacto)", 
           nativeQuery = true)
    void insertarProveedor(
        @Param("nit") String nit,
        @Param("nombre") String nombre,
        @Param("direccion") String direccion,
        @Param("personaContacto") String personaContacto,
        @Param("telefonoContacto") String telefonoContacto
    );

    // Actualizar un proveedor
    @Modifying
    @Transactional
    @Query(value = "UPDATE proveedores SET nit = :nit, nombre = :nombre, direccion = :direccion, " +
                   "persona_contacto = :personaContacto, telefono_contacto = :telefonoContacto WHERE id = :id", 
           nativeQuery = true)
    void actualizarProveedor(
        @Param("id") int id,
        @Param("nit") String nit,
        @Param("nombre") String nombre,
        @Param("direccion") String direccion,
        @Param("personaContacto") String personaContacto,
        @Param("telefonoContacto") String telefonoContacto
    );

    // Eliminar un proveedor por ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM proveedores WHERE id = :id", nativeQuery = true)
    void eliminarProveedor(@Param("id") int id);
}
