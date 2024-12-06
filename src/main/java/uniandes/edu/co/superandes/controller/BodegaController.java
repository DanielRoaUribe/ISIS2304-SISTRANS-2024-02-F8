package uniandes.edu.co.superandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.superandes.modelo.Bodega;
import uniandes.edu.co.superandes.repositorio.BodegaRepository;

@RestController
@RequestMapping("/bodega")
public class BodegaController {

    @Autowired
    private BodegaRepository bodegaRepository;

    // Crear una nueva bodega
    @PostMapping("/new/save")
    public ResponseEntity<String> crearBodega(@RequestBody Bodega bodega) {
        try {
            bodegaRepository.save(bodega);
            return new ResponseEntity<>("Bodega creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la bodega: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una bodega por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> borrarBodega(@PathVariable Integer id) {
        try {
            bodegaRepository.deleteById(id);
            return new ResponseEntity<>("Bodega eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la bodega: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}