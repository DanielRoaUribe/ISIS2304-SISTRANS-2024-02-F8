package uniandes.edu.co.superandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.superandes.modelo.OrdenCompra;
import uniandes.edu.co.superandes.repositorio.OrdenCompraRepository;

@RestController
@RequestMapping("/ordenCompra")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    // Crear una nueva orden de compra
    @PostMapping("/new/save")
    public ResponseEntity<String> crearOrdenCompra(@RequestBody OrdenCompra ordenCompra) {
        try {
            ordenCompraRepository.save(ordenCompra);
            return new ResponseEntity<>("Orden de compra creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de compra: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Leer una orden de compra por id
    @GetMapping("/read/{id}")
    public ResponseEntity<OrdenCompra> leerOrdenCompra(@PathVariable Integer id) {
        try {
            OrdenCompra ordenCompra = ordenCompraRepository.findById(id).orElse(null);
            if (ordenCompra != null) {
                return new ResponseEntity<>(ordenCompra, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una orden de compra (incluyendo la lista de productos)
    @PutMapping("/update/{id}")
    public ResponseEntity<String> actualizarOrdenCompra(@PathVariable Integer id, @RequestBody OrdenCompra ordenCompra) {
        try {
            if (ordenCompraRepository.existsById(id)) {
                ordenCompra.setId(id);  // Asegura que el id se mantenga
                ordenCompraRepository.save(ordenCompra);
                return new ResponseEntity<>("Orden de compra actualizada exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Orden de compra no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la orden de compra: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una orden de compra
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarOrdenCompra(@PathVariable Integer id) {
        try {
            if (ordenCompraRepository.existsById(id)) {
                ordenCompraRepository.deleteById(id);
                return new ResponseEntity<>("Orden de compra eliminada exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Orden de compra no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la orden de compra: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
