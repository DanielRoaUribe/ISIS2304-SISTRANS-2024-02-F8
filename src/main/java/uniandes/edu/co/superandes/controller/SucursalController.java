package uniandes.edu.co.superandes.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.superandes.modelo.Sucursal;
import uniandes.edu.co.superandes.repositorio.CiudadRepository;
import uniandes.edu.co.superandes.repositorio.ProductoRepository.RespuestaProductosRequiereOrdenCompra;
import uniandes.edu.co.superandes.repositorio.SucursalRepository;
import uniandes.edu.co.superandes.repositorio.SucursalRepository.RespuestaPorcentajes;

@Controller
public class SucursalController {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private CiudadRepository ciudadRepository; // Agregar la dependencia de CiudadRepository

    @GetMapping("/sucursales")
    public String sucursales(Model model) {
        model.addAttribute("sucursales", sucursalRepository.darSucursales());
        return "sucursales";
    }

    @GetMapping("/sucursales/new")
    public String sucursalForm(Model model) {
        model.addAttribute("sucursal", new Sucursal());
        model.addAttribute("ciudades", ciudadRepository.darCiudades()); // Obtener la lista de ciudades
        return "sucursalNuevo";
    }

    @PostMapping("/sucursales/new/save")
    public String sucursalGuardar(@ModelAttribute Sucursal sucursal) {
        sucursalRepository.insertarSucursal(
                sucursal.getNombre(),
                sucursal.getTamaño(),
                sucursal.getDireccion(),
                sucursal.getTelefono(),
                sucursal.getIdCiudad().getId());
        return "redirect:/sucursales";
    }

    @GetMapping("/sucursales/{id}/edit")
    public String sucursalEditarForm(@PathVariable("id") int id, Model model) {
        Sucursal sucursal = sucursalRepository.darSucursal(id);
        if (sucursal != null) {
            model.addAttribute("sucursal", sucursal);
            model.addAttribute("ciudades", ciudadRepository.darCiudades()); // Obtener la lista de ciudades
            return "sucursalEditar";
        } else {
            return "redirect:/sucursales";
        }
    }

    @PostMapping("/sucursales/{id}/edit/save")
    public String sucursalEditarGuardar(@PathVariable("id") int id, @ModelAttribute Sucursal sucursal) {
        sucursalRepository.actualizarSucursal(
                id,
                sucursal.getNombre(),
                sucursal.getTamaño(),
                sucursal.getDireccion(),
                sucursal.getTelefono(),
                sucursal.getIdCiudad().getId());
        return "redirect:/sucursales";
    }

    @GetMapping("/sucursales/{id}/delete")
    public String sucursalEliminar(@PathVariable("id") int id) {
        sucursalRepository.eliminarSucursal(id);
        return "redirect:/sucursales";
    }

    // Endpoint para obtener una sucursal por ID
    @GetMapping("/api/sucursales/{id}")
    public ResponseEntity<Sucursal> obtenerSucursal(@PathVariable("id") int id) {


        Sucursal sucursal = sucursalRepository.darSucursal(id);
        if (sucursal != null) {
            return ResponseEntity.ok(sucursal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    /* 
    // Requerimiento funcional 1
    @GetMapping("sucursales/porcentajeOcupacion")
    public Collection<RespuestaPorcentajes> darProductosRequierenOrdenCompra(
        @RequestParam String nombre,
        @RequestParam Integer id_sucursal) {
    return sucursalRepository.porcentajeOcupacion(nombre, id_sucursal);

    
}
    */
}