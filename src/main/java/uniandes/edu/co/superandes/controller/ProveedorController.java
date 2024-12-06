package uniandes.edu.co.superandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.superandes.modelo.Proveedor;
import uniandes.edu.co.superandes.repositorio.ProveedorRepository;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Crear un nuevo proveedor
    @PostMapping("/new/save")
    public ResponseEntity<String> crearProveedor(@RequestBody Proveedor proveedor) {
        try {
            proveedorRepository.save(proveedor);
            return new ResponseEntity<>("Proveedor creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el proveedor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un proveedor por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> borrarProveedor(@PathVariable Integer id) {
        try {
            proveedorRepository.deleteById(id);
            return new ResponseEntity<>("Proveedor eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el proveedor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        // Actualizar un proveedor
        @PutMapping("/update/{id}")
        public ResponseEntity<String> actualizarProveedor(@PathVariable Integer id, @RequestBody Proveedor proveedor) {
            try {
                // Verificamos si el proveedor existe
                if (proveedorRepository.existsById(id)) {
                    proveedor.setId(id); // Establecemos el id para garantizar la actualización correcta
                    proveedorRepository.save(proveedor); // Guarda o actualiza el proveedor
                    return new ResponseEntity<>("Proveedor actualizado exitosamente", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Proveedor no encontrado", HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity<>("Error al actualizar el proveedor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
}
