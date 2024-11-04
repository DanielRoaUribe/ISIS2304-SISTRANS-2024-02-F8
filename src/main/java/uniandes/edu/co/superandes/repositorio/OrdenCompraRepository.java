package uniandes.edu.co.superandes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.superandes.modelo.OrdenCompra;

public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Integer> {

    @Query(value = "SELECT * FROM ordenes_compra", nativeQuery = true)
    Collection<OrdenCompra> darOrdenesCompra();

    @Query(value = "SELECT * FROM ordenes_compra WHERE id = :id", nativeQuery = true)
    OrdenCompra darOrdenCompra(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ordenes_compra (fecha_creacion, fecha_entrega, estado, precio, cantidad, id_proveedor, id_sucursal, id_producto) "
            + "VALUES (:fechaCreacion, :fechaEntrega, :estado, :precio, :cantidad, :idProveedor, :idSucursal, :idProducto)", nativeQuery = true)
    void insertarOrdenCompra(@Param("fechaCreacion") String fechaCreacion,
                             @Param("fechaEntrega") String fechaEntrega,
                             @Param("estado") String estado,
                             @Param("precio") Integer precio,
                             @Param("cantidad") Integer cantidad,
                             @Param("idProveedor") int idProveedor,
                             @Param("idSucursal") int idSucursal,
                             @Param("idProducto") int idProducto);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ordenes_compra SET fecha_creacion = :fechaCreacion, fecha_entrega = :fechaEntrega, "
            + "estado = :estado, precio = :precio, cantidad = :cantidad, id_proveedor = :idProveedor, "
            + "id_sucursal = :idSucursal, id_producto = :idProducto WHERE id = :id", nativeQuery = true)
    void actualizarOrdenCompra(@Param("id") int id,
                               @Param("fechaCreacion") String fechaCreacion,
                               @Param("fechaEntrega") String fechaEntrega,
                               @Param("estado") String estado,
                               @Param("precio") Integer precio,
                               @Param("cantidad") Integer cantidad,
                               @Param("idProveedor") int idProveedor,
                               @Param("idSucursal") int idSucursal,
                               @Param("idProducto") int idProducto);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ordenes_compra WHERE id = :id", nativeQuery = true)
    void eliminarOrdenCompra(@Param("id") int id);
}
