package uniandes.edu.co.superandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import uniandes.edu.co.superandes.modelo.Proveedor;
import uniandes.edu.co.superandes.repositorio.ProveedorRepository;

@Controller
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Mostrar todos los proveedores
    @GetMapping("/proveedores")
    public String proveedores(Model model) {
        model.addAttribute("proveedores", proveedorRepository.darProveedores());
        return "proveedores";
    }

    // Mostrar formulario para agregar un nuevo proveedor
    @GetMapping("/proveedores/new")
    public String proveedorForm(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "proveedorNuevo";
    }

    // Guardar un nuevo proveedor
    @PostMapping("/proveedores/new/save")
    public String proveedorGuardar(@ModelAttribute Proveedor proveedor) {
        proveedorRepository.insertarProveedor(
                proveedor.getNit(),
                proveedor.getNombre(),
                proveedor.getDireccion(),
                proveedor.getPersonaContacto(),
                proveedor.getTelefonoContacto());
        return "redirect:/proveedores";
    }

    // Mostrar formulario para editar un proveedor existente
    @GetMapping("/proveedores/{id}/edit")
    public String proveedorEditarForm(@PathVariable("id") int id, Model model) {
        Proveedor proveedor = proveedorRepository.darProveedor(id);
        if (proveedor != null) {
            model.addAttribute("proveedor", proveedor);
            return "proveedorEditar";
        } else {
            return "redirect:/proveedores";
        }
    }

    // Guardar los cambios al editar un proveedor
    @PostMapping("/proveedores/{id}/edit/save")
    public String proveedorEditarGuardar(@PathVariable("id") int id, @ModelAttribute Proveedor proveedor) {
        proveedorRepository.actualizarProveedor(
                id,
                proveedor.getNit(),
                proveedor.getNombre(),
                proveedor.getDireccion(),
                proveedor.getPersonaContacto(),
                proveedor.getTelefonoContacto());
        return "redirect:/proveedores";
    }

    // Eliminar un proveedor por ID
    @GetMapping("/proveedores/{id}/delete")
    public String proveedorEliminar(@PathVariable("id") int id) {
        proveedorRepository.eliminarProveedor(id);
        return "redirect:/proveedores";
    }

    @PostMapping("/api/proveedores")
    public ResponseEntity<String> crearProveedor(@RequestBody Proveedor proveedor) {
        proveedorRepository.insertarProveedor(
                proveedor.getNit(),
                proveedor.getNombre(),
                proveedor.getDireccion(),
                proveedor.getPersonaContacto(),
                proveedor.getTelefonoContacto());
        return ResponseEntity.ok("Proveedor creado con éxito");
    }

    @PutMapping("/api/proveedores/{id}")
    public ResponseEntity<String> actualizarProveedor(@PathVariable("id") int id, @RequestBody Proveedor proveedor) {
        proveedorRepository.actualizarProveedor(
                id,
                proveedor.getNit(),
                proveedor.getNombre(),
                proveedor.getDireccion(),
                proveedor.getPersonaContacto(),
                proveedor.getTelefonoContacto());
        return ResponseEntity.ok("Proveedor actualizado con éxito");
    }

    @GetMapping("/api/proveedores/{id}")
    public ResponseEntity<Proveedor> obtenerProveedor(@PathVariable("id") int id) {
        Proveedor proveedor = proveedorRepository.darProveedor(id);
        if (proveedor != null) {
            return ResponseEntity.ok(proveedor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    


}
