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

    public interface RespuestaPorcentajes{
        String getNOMBRE();

        Integer getPORCENTAJE();
    }


    @Query(value = "SELECT b.nombre AS nombre, (CAST(COUNT(p.id) AS DECIMAL) / b.tamaño) * 100 AS porcentaje " +
                "FROM bodegas b " +
                "JOIN productos p ON p.id_bodega = b.id " +
                "JOIN sucursales s ON s.id = b.id_sucursal " + 
                "WHERE p.nombre = :producto AND s.id = :idSucursal " +
                "GROUP BY b.id")
    Collection<RespuestaPorcentajes> porcentajeOcupacion(@Param("producto") String producto, @Param("idSucursal") Integer idSucursal);
}
