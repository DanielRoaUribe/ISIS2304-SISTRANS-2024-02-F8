package uniandes.edu.co.superandes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.superandes.modelo.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Query(value = "SELECT * FROM categorias", nativeQuery = true)
    Collection<Categoria> darCategorias();

    @Query(value = "SELECT * FROM categorias WHERE id = :id", nativeQuery = true)
    Categoria darCategoria(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO categorias (id, nombre, descripcion, caracteristicas_almacenamiento) VALUES (ISEQ$$_665718.nextval, :nombre, :descripcion, :caracteristicasAlmacenamiento)", nativeQuery = true)
    void insertarCategoria(@Param("nombre") String nombre,
                           @Param("descripcion") String descripcion,
                           @Param("caracteristicasAlmacenamiento") String caracteristicasAlmacenamiento);

    @Modifying
    @Transactional
    @Query(value = "UPDATE categorias SET nombre = :nombre, descripcion = :descripcion, caracteristicas_almacenamiento = :caracteristicasAlmacenamiento WHERE id = :id", nativeQuery = true)
    void actualizarCategoria(@Param("id") int id,
                             @Param("nombre") String nombre,
                             @Param("descripcion") String descripcion,
                             @Param("caracteristicasAlmacenamiento") String caracteristicasAlmacenamiento);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM categorias WHERE id = :id", nativeQuery = true)
    void eliminarCategoria(@Param("id") int id);
}
