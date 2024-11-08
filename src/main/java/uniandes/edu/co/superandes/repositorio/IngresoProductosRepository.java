package uniandes.edu.co.superandes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.superandes.modelo.IngresoProductos;

public interface IngresoProductosRepository extends JpaRepository<IngresoProductos, Integer> {

    // Método para obtener todos los ingresos de productos
    @Query(value = "SELECT * FROM ingreso_productos", nativeQuery = true)
    Collection<IngresoProductos> darIngresosProductos();

    // Método para obtener un ingreso de productos específico por su id
    @Query(value = "SELECT * FROM ingreso_productos WHERE id = :id", nativeQuery = true)
    IngresoProductos darIngresoProducto(@Param("id") int id);

    // Método para insertar un nuevo ingreso de producto (sin especificar el id)
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ingreso_productos (fecha_ingreso, sucursal, bodega, proveedor, nombre_producto, cantidad, precio) "
            + "VALUES (:fechaIngreso, :sucursal, :bodega, :proveedor, :nombreProducto, :cantidad, :precio)", nativeQuery = true)
    void insertarIngresoProducto(
            @Param("fechaIngreso") String fechaIngreso,
            @Param("sucursal") Integer sucursal,
            @Param("bodega") Integer bodega,
            @Param("proveedor") Integer proveedor,
            @Param("nombreProducto") String nombreProducto,
            @Param("cantidad") Integer cantidad,
            @Param("precio") Integer precio
    );

    // Método para actualizar un ingreso de productos
    @Modifying
    @Transactional
    @Query(value = "UPDATE ingreso_productos SET fecha_ingreso = :fechaIngreso, sucursal = :sucursal, bodega = :bodega, proveedor = :proveedor, "
            + "nombre_producto = :nombreProducto, cantidad = :cantidad, precio = :precio WHERE id = :id", nativeQuery = true)
    void actualizarIngresoProducto(
            @Param("id") int id,
            @Param("fechaIngreso") String fechaIngreso,
            @Param("sucursal") Integer sucursal,
            @Param("bodega") Integer bodega,
            @Param("proveedor") Integer proveedor,
            @Param("nombreProducto") String nombreProducto,
            @Param("cantidad") Integer cantidad,
            @Param("precio") Integer precio
    );

    // Método para eliminar un ingreso de productos por su id
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ingreso_productos WHERE id = :id", nativeQuery = true)
    void eliminarIngresoProducto(@Param("id") int id);

    //Ingreso de productos en los ultimos 30 dias
    @Query(value = "SELECT ip.id AS numeroDocumento, ip.fecha_ingreso AS fechaIngreso, " +
    "       ip.proveedor AS nombreProveedor, ip.sucursal AS nombreSucursal, ip.bodega AS nombreBodega " +
    "FROM ingreso_productos ip " +
    "WHERE ip.sucursal = :idSucursal " +
    "  AND ip.bodega = :idBodega " +
    "  AND ip.fecha_ingreso >= CURRENT_DATE - INTERVAL '30' DAY", nativeQuery = true)
Collection<Object[]> obtenerDocumentosIngreso(@Param("idSucursal") Integer idSucursal, @Param("idBodega") Integer idBodega);

}
