package uniandes.edu.co.superandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.superandes.servicios.IngresoProductosServicio;

@RestController

public class IngresoProductosController {

    @Autowired
    private IngresoProductosServicio ingresoProductosServicio;

    @PostMapping("/api/ingresoProductos/crear")
    public ResponseEntity<String> crearIngresoProductos(@RequestParam int idOrdenCompra, @RequestParam int idBodega) {
        try {
            ingresoProductosServicio.crearIngresoProductos(idOrdenCompra, idBodega);
            return ResponseEntity.ok("Ingreso de productos creado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear el ingreso de productos: " + e.getMessage());
        }
    }
}
