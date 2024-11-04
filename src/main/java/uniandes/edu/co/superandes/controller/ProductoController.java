package uniandes.edu.co.superandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.superandes.modelo.Producto;
import uniandes.edu.co.superandes.repositorio.ProductoRepository;

@RestController
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // Endpoint para crear un nuevo producto
    @PostMapping("/api/productos")
    public ResponseEntity<String> crearProducto(@RequestBody Producto producto) {
        productoRepository.insertarProducto(
                producto.getNombre(),
                producto.getCostoBodega(),
                producto.getPrecio(),
                producto.getPresentacion(),
                producto.getCantidadPresentacion(),
                producto.getUnidadMedida(),
                producto.getEspecificacionesEmpacado(),
                producto.getVolumenEmpaque(),
                producto.getFechaExpiracion(),
                producto.getCodigoBarras(),
                producto.getNivelReorden(),
                producto.getIdBodega(),        // Relación con idBodega
                producto.getIdCategoria().getId(),     // Relación con idCategoria
                producto.getIdOrdenCompra());  // Relación con idOrdenCompra
        return ResponseEntity.ok("Producto creado con éxito");
    }

    // Endpoint para actualizar un producto existente
    @PutMapping("/api/productos/{id}")
    public ResponseEntity<String> actualizarProducto(@PathVariable("id") int id, @RequestBody Producto producto) {
        productoRepository.actualizarProducto(
                id,
                producto.getNombre(),
                producto.getCostoBodega(),
                producto.getPrecio(),
                producto.getPresentacion(),
                producto.getCantidadPresentacion(),
                producto.getUnidadMedida(),
                producto.getEspecificacionesEmpacado(),
                producto.getVolumenEmpaque(),
                producto.getFechaExpiracion(),
                producto.getCodigoBarras(),
                producto.getNivelReorden(),
                producto.getIdBodega(),        // Relación con idBodega
                producto.getIdCategoria().getId(),     // Relación con idCategoria
                producto.getIdOrdenCompra());  // Relación con idOrdenCompra
        return ResponseEntity.ok("Producto actualizado con éxito");
    }

    // Endpoint para obtener un producto por ID
    @GetMapping("/api/productos/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable("id") int id) {
        Producto producto = productoRepository.darProducto(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
