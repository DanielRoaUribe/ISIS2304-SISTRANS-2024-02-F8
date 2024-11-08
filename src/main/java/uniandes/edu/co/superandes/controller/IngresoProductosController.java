package uniandes.edu.co.superandes.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.superandes.servicios.DocumentoIngresoDTO;
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


    // Endpoint para RFC6 - Nivel de aislamiento SERIALIZABLE
    @GetMapping("/serializable/{idSucursal}/{idBodega}")
    public ResponseEntity<?> consultarIngresosSerializable(@PathVariable Integer idSucursal, @PathVariable Integer idBodega) {
        try {
            Collection<DocumentoIngresoDTO> ingresos = ingresoProductosServicio.consultarIngresosSerializable(idSucursal, idBodega);
            return ResponseEntity.ok(ingresos);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    // Endpoint para RFC7 - Nivel de aislamiento READ_COMMITTED
    @GetMapping("/read-committed/{idSucursal}/{idBodega}")
    public ResponseEntity<?> consultarIngresosReadCommitted(@PathVariable Integer idSucursal, @PathVariable Integer idBodega) {
        try {
            Collection<DocumentoIngresoDTO> ingresos = ingresoProductosServicio.consultarIngresosReadCommitted(idSucursal, idBodega);
            return ResponseEntity.ok(ingresos);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
