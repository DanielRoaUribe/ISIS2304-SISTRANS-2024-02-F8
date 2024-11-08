package uniandes.edu.co.superandes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.superandes.modelo.Sucursal;

public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {

    @Query(value = "SELECT * FROM sucursales", nativeQuery = true)
    Collection<Sucursal> darSucursales();

    @Query(value = "SELECT * FROM sucursales WHERE id = :id", nativeQuery = true)
    Sucursal darSucursal(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO sucursales (id, nombre, tamaño, direccion, telefono, id_ciudad) "
            + "VALUES (sucursales_sequence.nextval, :nombre, :tamaño, :direccion, :telefono, :idCiudad)", nativeQuery = true)
    void insertarSucursal(@Param("nombre") String nombre,
            @Param("tamaño") Double tamaño,
            @Param("direccion") String direccion,
            @Param("telefono") String telefono,
            @Param("idCiudad") int idCiudad);

    @Modifying
    @Transactional
    @Query(value = "UPDATE sucursales SET nombre = :nombre, tamaño = :tamaño, direccion = :direccion, "
            + "telefono = :telefono, id_ciudad = :idCiudad WHERE id = :id", nativeQuery = true)
    void actualizarSucursal(@Param("id") int id,
            @Param("nombre") String nombre,
            @Param("tamaño") Double tamaño,
            @Param("direccion") String direccion,
            @Param("telefono") String telefono,
            @Param("idCiudad") int idCiudad);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM sucursales WHERE id = :id", nativeQuery = true)
    void eliminarSucursal(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "SELECT nombre, (cantidad / NULLIF(tamaño, 0)) * 100 AS porcentaje " +
                   "FROM ( " +
                   "    SELECT b.nombre AS nombre, " +
                   "           COUNT(p.id) AS cantidad, " +
                   "           b.tamaño AS tamaño " +
                   "    FROM bodegas b " +
                   "    JOIN productos p ON p.id_bodega = b.id " +
                   "    JOIN sucursales s ON s.id = b.id_sucursal " +
                   "    WHERE p.nombre = :producto " +
                   "      AND s.id = :idSucursal " +
                   "      AND b.tamaño > 0 " +
                   "    GROUP BY b.id, b.nombre, b.tamaño " +
                   ")",
           nativeQuery = true)
    Collection<RespuestaPorcentajes> porcentajeOcupacion(@Param("producto") String producto, @Param("idSucursal") Integer idSucursal);

    public interface RespuestaPorcentajes {
        String getNOMBRE();
        Double getPORCENTAJE();
    }
}
