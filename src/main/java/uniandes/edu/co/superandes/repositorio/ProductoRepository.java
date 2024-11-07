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

        public interface RespuestaInformacionProductosEnBodega{
                String getNOMBRE();
                int getCANTIDAD_ACTUAL();
                int getCANTIDAD_MINIMA();
                int getCOSTO_PROMEDIO();
                
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
                        "AND P.fecha_expiracion < :fechaVencimiento " +
                        "AND S.id = :sucursalId " +
                        "AND P.id_Categoria = :categoriaId", nativeQuery = true)
        Collection<Producto> darProductoPorCaracteristica(
                        @Param("precioMayor") Integer precioMayor,
                        @Param("precioMenor") Integer precioMenor,
                        @Param("fechaVencimiento") Date fechaVencimiento,
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

}