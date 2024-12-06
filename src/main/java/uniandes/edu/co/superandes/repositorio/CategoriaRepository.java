package uniandes.edu.co.superandes.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uniandes.edu.co.superandes.modelo.Categoria;

public interface CategoriaRepository extends MongoRepository<Categoria, Integer> {

    // Si necesitas una consulta personalizada, puedes agregar un @Query
    // Ejemplo de consulta personalizada
    @Query("{ $insert: { _id: ?0, nombre: ?1, descripcion: ?2, caracteristicasAlmacenamiento: ?3 } }")
    void insertarCategoria(int id, String nombre, String descripcion, String caracteristicasAlmacenamiento);

    // Método predeterminado para guardar una categoría
    default void guardarCategoria(Categoria categoria) {
        save(categoria);
    }
}
