package uniandes.edu.co.superandes.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.superandes.modelo.Sucursal;

public interface SucursalRepository extends MongoRepository<Sucursal, Integer> {
        @Query("{ $insert: { _id: ?0, nombre: ?1, tamaño: ?2, direccion: ?3, telefono: ?4, idCiudad: ?5 } }")
        void insertarSucursal(int id, String nombre, Double tamaño, String direccion, String telefono,
                        String idCiudad);

        default void guardarSucursal(Sucursal sucursal){
                save(sucursal);
        }
}