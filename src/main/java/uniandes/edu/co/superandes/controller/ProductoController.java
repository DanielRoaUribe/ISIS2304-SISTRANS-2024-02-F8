package uniandes.edu.co.superandes.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.superandes.modelo.Producto;
import uniandes.edu.co.superandes.repositorio.ProductoRepository;

import uniandes.edu.co.superandes.repositorio.ProductoRepository.RespuestaInformacionProductosEnBodega;
import uniandes.edu.co.superandes.repositorio.ProductoRepository.RespuestaProductosRequiereOrdenCompra;

@RestController
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // Requerimiento Funcional 2  
    @GetMapping("productos/productosPorCaracteristicas")
    public Collection<Producto> getProductosPorCaracteristica(
            @RequestParam("precioMenor") Double precioMenor,
            @RequestParam("precioMayor") Double precioMayor,
            @RequestParam("fechaVencimiento") String fechaVencimiento,
            @RequestParam("sucursalId") Integer sucursalId,
            @RequestParam("categoriaId") Integer categoriaId) {

        return productoRepository.darProductoPorCaracteristica(precioMayor, precioMenor, fechaVencimiento, sucursalId, categoriaId);
    }

    // Requerimiento Funcional 3
    @GetMapping("productos/productosEnBodega")
    public Collection<RespuestaInformacionProductosEnBodega> darInventarioProductosEnBodega(
            @RequestParam Integer sucursalId,
            @RequestParam Integer bodegaId) {
        return productoRepository.darInventarioProductosEnBodega(sucursalId, bodegaId);
    }

    // Requerimiento Funcional 4
        @GetMapping("productos/sucursalesDisponibilidad")
        public ResponseEntity<Collection<String>> darSucursalesDisponibilidad(
                @RequestParam(value = "nombre", required = false) String nombre,
                @RequestParam(value = "id", required = false) Integer id) {
    
            // Verificamos que al menos uno de los parámetros esté presente
            if (nombre == null && id == null) {
                return ResponseEntity.badRequest().body(null);  // Error si no se pasa ninguno
            }
    
            // Consultamos las sucursales a través del repositorio
            Collection<String> sucursales = productoRepository.darSucursalesDisponibilidad(nombre, id);
    
            if (sucursales.isEmpty()) {
                return ResponseEntity.noContent().build();  // Si no se encuentran resultados
            }
    
            return ResponseEntity.ok(sucursales);  // Retornamos las sucursales
        }

    // Requerimiento Funcional 5
    @GetMapping("/productos/productosRequeridos")
    public Collection<RespuestaProductosRequiereOrdenCompra> darProductosRequierenOrdenCompra(
        @RequestParam Integer sucursalId,
        @RequestParam Integer bodegaId) {
    return productoRepository.darProductosRequierenOrdenCompra(sucursalId, bodegaId);
}


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
                producto.getIdBodega(), // Relación con idBodega
                producto.getIdCategoria().getId(), // Relación con idCategoria
                producto.getIdOrdenCompra()); // Relación con idOrdenCompra
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
                producto.getIdBodega(), // Relación con idBodega
                producto.getIdCategoria().getId(), // Relación con idCategoria
                producto.getIdOrdenCompra()); // Relación con idOrdenCompra
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
