package uniandes.edu.co.superandes.repositorio;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.superandes.modelo.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

        // Para la Respuesta de Informacion Productos En Bodega
        public interface RespuestaInformacionProductosEnBodega {
                String getNOMBRE();

                int getCANTIDAD_ACTUAL();

                int getCANTIDAD_MINIMA();

                int getCOSTO_PROMEDIO();

        }

        public interface RespuestaProductosRequiereOrdenCompra {
                String getNOMBRE();

                int getID();

                String getBODEGA();

                String getSUCURSAL();

                int getCANTIDAD_ACTUAL();

                String getPROVEEDOR();
        }

        @Query(value = "SELECT * FROM productos", nativeQuery = true)
        Collection<Producto> darProductos();

        @Query(value = "SELECT * FROM productos WHERE id = :id", nativeQuery = true)
        Producto darProducto(@Param("id") int id);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO productos (nombre, costo_bodega, precio, presentacion, cantidad_presentacion, unidad_medida, "
                        + "especificaciones_empacado, volumen_empaque, fecha_expiracion, codigo_barras, nivel_reorden, "
                        + "id_bodega, id_categoria, id_orden_compra) VALUES (:nombre, :costoBodega, :precio, :presentacion, "
                        + ":cantidadPresentacion, :unidadMedida, :especificacionesEmpacado, :volumenEmpaque, :fechaExpiracion, "
                        + ":codigoBarras, :nivelReorden, :idBodega, :idCategoria, :idOrdenCompra)", nativeQuery = true)
        void insertarProducto(@Param("nombre") String nombre,
                        @Param("costoBodega") Integer costoBodega,
                        @Param("precio") Integer precio,
                        @Param("presentacion") String presentacion,
                        @Param("cantidadPresentacion") Integer cantidadPresentacion,
                        @Param("unidadMedida") String unidadMedida,
                        @Param("especificacionesEmpacado") String especificacionesEmpacado,
                        @Param("volumenEmpaque") Integer volumenEmpaque,
                        @Param("fechaExpiracion") String fechaExpiracion,
                        @Param("codigoBarras") String codigoBarras,
                        @Param("nivelReorden") Integer nivelReorden,
                        @Param("idBodega") Integer idBodega,
                        @Param("idCategoria") Integer idCategoria,
                        @Param("idOrdenCompra") Integer idOrdenCompra);

        @Modifying
        @Transactional
        @Query(value = "UPDATE productos SET nombre = :nombre, costo_bodega = :costoBodega, precio = :precio, "
                        + "presentacion = :presentacion, cantidad_presentacion = :cantidadPresentacion, unidad_medida = :unidadMedida, "
                        + "especificaciones_empacado = :especificacionesEmpacado, volumen_empaque = :volumenEmpaque, "
                        + "fecha_expiracion = :fechaExpiracion, codigo_barras = :codigoBarras, nivel_reorden = :nivelReorden, "
                        + "id_bodega = :idBodega, id_categoria = :idCategoria, id_orden_compra = :idOrdenCompra WHERE id = :id", nativeQuery = true)
        void actualizarProducto(@Param("id") int id,
                        @Param("nombre") String nombre,
                        @Param("costoBodega") Integer costoBodega,
                        @Param("precio") Integer precio,
                        @Param("presentacion") String presentacion,
                        @Param("cantidadPresentacion") Integer cantidadPresentacion,
                        @Param("unidadMedida") String unidadMedida,
                        @Param("especificacionesEmpacado") String especificacionesEmpacado,
                        @Param("volumenEmpaque") Integer volumenEmpaque,
                        @Param("fechaExpiracion") String fechaExpiracion,
                        @Param("codigoBarras") String codigoBarras,
                        @Param("nivelReorden") Integer nivelReorden,
                        @Param("idBodega") Integer idBodega,
                        @Param("idCategoria") Integer idCategoria,
                        @Param("idOrdenCompra") Integer idOrdenCompra);

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM productos WHERE id = :id", nativeQuery = true)
        void eliminarProducto(@Param("id") int id);

        // Requerimiento Funcional 2
        @Query(value = "SELECT DISTINCT P.* " +
                        "FROM productos P " +
                        "INNER JOIN bodegas B ON P.id_Bodega = B.id " +
                        "INNER JOIN sucursales S ON B.id_Sucursal = S.id " +
                        "WHERE P.precio BETWEEN :precioMenor AND :precioMayor " +
                        "AND TO_DATE(P.fecha_expiracion, 'YYYY-MM-DD') < TO_DATE(:fechaVencimiento, 'YYYY-MM-DD') " + // Conversión
                                                                                                                      // a
                                                                                                                      // fecha
                        "AND S.id = :sucursalId " +
                        "AND P.id_Categoria = :categoriaId", nativeQuery = true)
        Collection<Producto> darProductoPorCaracteristica(
                        @Param("precioMenor") Double precioMenor,
                        @Param("precioMayor") Double precioMayor,
                        @Param("fechaVencimiento") String fechaVencimiento, // Mantén la fecha como String
                        @Param("sucursalId") Integer sucursalId,
                        @Param("categoriaId") Integer categoriaId);

        // Requerimiento Funcional 3
        @Query(value = "SELECT P.nombre, " +
                        "       COUNT(P.id) AS CANTIDAD_ACTUAL, " +
                        "       P.nivel_reorden AS CANTIDAD_MINIMA, " +
                        "       AVG(P.precio) AS COSTO_PROMEDIO " +
                        "FROM productos P " +
                        "INNER JOIN bodegas B ON P.id_bodega = B.id " +
                        "INNER JOIN sucursales S ON B.id_Sucursal = S.id " +
                        "WHERE S.id = :sucursalId AND B.id = :bodegaId " +
                        "GROUP BY P.nombre, P.nivel_reorden", nativeQuery = true)
        Collection<RespuestaInformacionProductosEnBodega> darInventarioProductosEnBodega(
                        @Param("sucursalId") Integer sucursalId,
                        @Param("bodegaId") Integer bodegaId);

        // Requerimiento Funcional 4
        @Query(value = "SELECT S.nombre " +
        "FROM productos P " +
        "INNER JOIN bodegas B ON P.id_bodega = B.id " +
        "INNER JOIN sucursales S ON B.id_sucursal = S.id " +
         "WHERE P.nombre = :nombre OR P.id = :id", nativeQuery = true)
        Collection<String> darSucursalesDisponibilidad(@Param("nombre") String nombre, @Param("id") Integer id);





        // Requerimiento Funcional 5
        @Query(value = "SELECT P.nombre AS NOMBRE, " +
                        "       P.id AS ID, " +
                        "       B.nombre AS BODEGA, " +
                        "       S.nombre AS SUCURSAL, " +
                        "       COUNT(P.id) AS CANTIDAD_ACTUAL, " +
                        "       'Proveedor Actualizado' AS PROVEEDOR " +
                        "FROM productos P " +
                        "INNER JOIN bodegas B ON P.id_bodega = B.id " +
                        "INNER JOIN sucursales S ON B.id_sucursal = S.id " +
                        "INNER JOIN ordenes_compra OC ON OC.id_sucursal = S.id " + // Relacionamos la sucursal con las
                                                                                   // ordenes de compra
                        "INNER JOIN proveedores PR ON OC.id_proveedor = PR.id " + // Relacionamos las ordenes de compra
                                                                                  // con proveedores
                        "WHERE S.id = :sucursalId " +
                        "  AND B.id = :bodegaId " +
                        "  AND PR.id = 1 " +
                        "GROUP BY P.nombre, P.id, B.nombre, S.nombre, P.nivel_reorden " +
                        "HAVING COUNT(P.id) < P.nivel_reorden", nativeQuery = true)
        Collection<RespuestaProductosRequiereOrdenCompra> darProductosRequierenOrdenCompra(
                        @Param("sucursalId") Integer sucursalId,
                        @Param("bodegaId") Integer bodegaId);

}