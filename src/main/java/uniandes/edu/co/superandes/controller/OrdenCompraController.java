package uniandes.edu.co.superandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.superandes.modelo.OrdenCompra;
import uniandes.edu.co.superandes.repositorio.OrdenCompraRepository;
import uniandes.edu.co.superandes.repositorio.ProductoRepository;
import uniandes.edu.co.superandes.repositorio.ProveedorRepository;
import uniandes.edu.co.superandes.repositorio.SucursalRepository;

import java.util.Collection;

@Controller
public class OrdenCompraController {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/ordenes_compra")
    public String listarordenes_compra(Model model) {
        Collection<OrdenCompra> ordenes = ordenCompraRepository.darOrdenesCompra();
        model.addAttribute("ordenes_compra", ordenes);
        return "ordenes_compra"; // Nombre de la vista
    }

    @GetMapping("/ordenes_compra/new")
    public String nuevaOrdenCompraForm(Model model) {
        model.addAttribute("ordenCompra", new OrdenCompra());
        model.addAttribute("proveedores", proveedorRepository.findAll()); // Obtener la lista de proveedores
        model.addAttribute("sucursales", sucursalRepository.findAll()); // Obtener la lista de sucursales
        model.addAttribute("productos", productoRepository.findAll()); // Obtener la lista de productos
        return "ordenCompraNuevo"; // Nombre de la vista para crear una nueva orden
    }

    @PostMapping("/ordenes_compra/new/save")
    public String guardarNuevaOrdenCompra(@ModelAttribute OrdenCompra ordenCompra) {
        ordenCompraRepository.insertarOrdenCompra(
                ordenCompra.getFechaCreacion(),
                ordenCompra.getFechaEntrega(),
                ordenCompra.getEstado(),
                ordenCompra.getPrecio(),
                ordenCompra.getCantidad(),
                ordenCompra.getIdProveedor().getId(),
                ordenCompra.getIdSucursal().getId(),
                ordenCompra.getIdProducto().getId() // Agregar la relación con Producto
        );
        return "redirect:/ordenes_compra"; // Redirigir a la lista de órdenes de compra
    }

    @GetMapping("/ordenes_compra/{id}/edit")
    public String editarOrdenCompraForm(@PathVariable("id") int id, Model model) {
        OrdenCompra ordenCompra = ordenCompraRepository.darOrdenCompra(id);
        if (ordenCompra != null) {
            model.addAttribute("ordenCompra", ordenCompra);
            model.addAttribute("proveedores", proveedorRepository.findAll()); // Obtener la lista de proveedores
            model.addAttribute("sucursales", sucursalRepository.findAll()); // Obtener la lista de sucursales
            model.addAttribute("productos", productoRepository.findAll()); // Obtener la lista de productos
            return "ordenCompraEditar"; // Nombre de la vista para editar una orden
        } else {
            return "redirect:/ordenes_compra"; // Redirigir si no se encuentra la orden
        }
    }

    @PostMapping("/ordenes_compra/{id}/edit/save")
    public String OrdenCompraEditarGuardar(@PathVariable("id") int id, @ModelAttribute OrdenCompra ordenCompra) {
        ordenCompraRepository.actualizarOrdenCompra(
                id,
                ordenCompra.getFechaCreacion(),
                ordenCompra.getFechaEntrega(),
                ordenCompra.getEstado(),
                ordenCompra.getPrecio(),
                ordenCompra.getCantidad(),
                ordenCompra.getIdProveedor().getId(),
                ordenCompra.getIdSucursal().getId(),
                ordenCompra.getIdProducto().getId() // Agregar la relación con Producto
        );
        return "redirect:/ordenes_compra"; // Redirigir a la lista de órdenes de compra
    }

    @GetMapping("/ordenes_compra/{id}/delete")
    public String eliminarOrdenCompra(@PathVariable("id") int id) {
        ordenCompraRepository.eliminarOrdenCompra(id);
        return "redirect:/ordenes_compra"; // Redirigir a la lista de órdenes de compra
    }

    @PostMapping("/api/ordenes_compra")
    public ResponseEntity<String> crearOrdenCompra(@RequestBody OrdenCompra ordenCompra) {
        ordenCompraRepository.insertarOrdenCompra(
                ordenCompra.getFechaCreacion(),
                ordenCompra.getFechaEntrega(),
                ordenCompra.getEstado(),
                ordenCompra.getPrecio(),
                ordenCompra.getCantidad(),
                ordenCompra.getIdProveedor().getId(), // Asegúrate de que el objeto Proveedor tiene el ID
                ordenCompra.getIdSucursal().getId(), // Asegúrate de que el objeto Sucursal tiene el ID
                ordenCompra.getIdProducto().getId() // Asegúrate de que el objeto Producto tiene el ID
        );
        return ResponseEntity.ok("Orden de compra creada con éxito");
    }

    @PutMapping("/api/ordenes_compra/{id}")
    public ResponseEntity<String> actualizarOrdenCompra(@PathVariable("id") int id,
            @RequestBody OrdenCompra ordenCompra) {
        ordenCompraRepository.actualizarOrdenCompra(
                id,
                ordenCompra.getFechaCreacion(),
                ordenCompra.getFechaEntrega(),
                ordenCompra.getEstado(),
                ordenCompra.getPrecio(),
                ordenCompra.getCantidad(),
                ordenCompra.getIdProveedor().getId(), // Asegúrate de que el objeto Proveedor tiene el ID
                ordenCompra.getIdSucursal().getId(), // Asegúrate de que el objeto Sucursal tiene el ID
                ordenCompra.getIdProducto().getId() // Asegúrate de que el objeto Producto tiene el ID
        );
        return ResponseEntity.ok("Orden de compra actualizada con éxito");
    }

    @GetMapping("/api/ordenes_compra/{id}")
    public ResponseEntity<OrdenCompra> obtenerOrdenCompra(@PathVariable("id") int id) {
        OrdenCompra ordenCompra = ordenCompraRepository.darOrdenCompra(id);
        if (ordenCompra != null) {
            return ResponseEntity.ok(ordenCompra);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/ordenes_compra/listar")
    public ResponseEntity<Collection<OrdenCompra>> obtenerTodasLasOrdenesCompra() {
        Collection<OrdenCompra> ordenesCompra = ordenCompraRepository.findAll();
        return ResponseEntity.ok(ordenesCompra);
    }

}
