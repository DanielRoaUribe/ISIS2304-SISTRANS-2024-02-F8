package uniandes.edu.co.superandes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.superandes.modelo.Bodega;

public interface BodegaRepository extends JpaRepository<Bodega, Integer> {

    @Query(value = "SELECT * FROM bodegas", nativeQuery = true)
    Collection<Bodega> darBodegas();

    @Query(value = "SELECT * FROM bodegas WHERE id = :id", nativeQuery = true)
    Bodega darBodega(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO bodegas (nombre, tamaño, costo_promedio, id_sucursal) VALUES (:nombre, :tamaño, :costoPromedio, :idSucursal)", nativeQuery = true)
    void insertarBodega(@Param("nombre") String nombre,
                        @Param("tamaño") Integer tamaño,
                        @Param("costoPromedio") Integer costoPromedio,
                        @Param("idSucursal") Integer idSucursal);

    @Modifying
    @Transactional
    @Query(value = "UPDATE bodegas SET nombre = :nombre, tamaño = :tamaño, costo_promedio = :costoPromedio, id_sucursal = :idSucursal WHERE id = :id", nativeQuery = true)
    void actualizarBodega(@Param("id") int id,
                          @Param("nombre") String nombre,
                          @Param("tamaño") Integer tamaño,
                          @Param("costoPromedio") Integer costoPromedio,
                          @Param("idSucursal") Integer idSucursal);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM bodegas WHERE id = :id", nativeQuery = true)
    void eliminarBodega(@Param("id") int id);
}
