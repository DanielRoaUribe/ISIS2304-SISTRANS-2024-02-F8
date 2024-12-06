package uniandes.edu.co.superandes.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uniandes.edu.co.superandes.modelo.Producto;

public interface ProductoRepository extends MongoRepository<Producto, Integer> {

    // Ejemplo de consulta personalizada para insertar un producto
    @Query("{ $insert: { _id: ?0, nombre: ?1, costoBodega: ?2, precio: ?3, presentacion: ?4, cantidadPresentacion: ?5, unidadMedida: ?6, especificacionesEmpacado: ?7, volumenEmpaque: ?8, fechaExpiracion: ?9, codigoBarras: ?10, nivelReorden: ?11, idCategoria: ?12 } }")
    void insertarProducto(int id, String nombre, Integer costoBodega, Integer precio, String presentacion,
                          Integer cantidadPresentacion, String unidadMedida, String especificacionesEmpacado,
                          Integer volumenEmpaque, String fechaExpiracion, String codigoBarras, Integer nivelReorden,
                          String idCategoria);

    // Método predeterminado para guardar un producto
    default void guardarProducto(Producto producto) {
        save(producto);
    }
}