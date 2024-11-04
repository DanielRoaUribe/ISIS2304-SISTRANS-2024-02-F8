package uniandes.edu.co.superandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import uniandes.edu.co.superandes.modelo.Bodega;
import uniandes.edu.co.superandes.repositorio.BodegaRepository;

@Controller
public class BodegaController {

    @Autowired
    private BodegaRepository bodegaRepository;

    // Endpoint para crear una nueva bodega
    @PostMapping("/api/bodegas")
    public ResponseEntity<String> crearBodega(@RequestBody Bodega bodega) {
        bodegaRepository.insertarBodega(
                bodega.getNombre(),
                bodega.getTamaño(),
                bodega.getCostoPromedio(),
                bodega.getIdSucursal().getId());  // Relación con idSucursal
        return ResponseEntity.ok("Bodega creada con éxito");
    }

    // Endpoint para actualizar una bodega existente
    @PutMapping("/api/bodegas/{id}")
    public ResponseEntity<String> actualizarBodega(@PathVariable("id") int id, @RequestBody Bodega bodega) {
        bodegaRepository.actualizarBodega(
                id,
                bodega.getNombre(),
                bodega.getTamaño(),
                bodega.getCostoPromedio(),
                bodega.getIdSucursal().getId());  // Relación con idSucursal
        return ResponseEntity.ok("Bodega actualizada con éxito");
    }

    // Endpoint para obtener una bodega por ID
    @GetMapping("/api/bodegas/{id}")
    public ResponseEntity<Bodega> obtenerBodega(@PathVariable("id") int id) {
        Bodega bodega = bodegaRepository.darBodega(id);
        if (bodega != null) {
            return ResponseEntity.ok(bodega);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para eliminar una bodega por ID
@DeleteMapping("/api/bodegas/{id}")
public ResponseEntity<String> eliminarBodega(@PathVariable("id") int id) {
    try {
        bodegaRepository.eliminarBodega(id);
        return ResponseEntity.ok("Bodega eliminada con éxito");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la bodega");
    }
}
}
