package uniandes.edu.co.superandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import uniandes.edu.co.superandes.modelo.Categoria;
import uniandes.edu.co.superandes.repositorio.CategoriaRepository;


@Controller
public class CategoriaController {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    // Endpoint para crear una nueva categoría
    @PostMapping("/api/categorias")
    public ResponseEntity<String> crearCategoria(@RequestBody Categoria categoria) {
        categoriaRepository.insertarCategoria(
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.getCaracteristicasAlmacenamiento()); // Nuevo campo
        return ResponseEntity.ok("Categoría creada con éxito");
    }

    // Endpoint para actualizar una categoría existente
    @PutMapping("/api/categorias/{id}")
    public ResponseEntity<String> actualizarCategoria(@PathVariable("id") int id, @RequestBody Categoria categoria) {
        categoriaRepository.actualizarCategoria(
                id,
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.getCaracteristicasAlmacenamiento()); // Nuevo campo
        return ResponseEntity.ok("Categoría actualizada con éxito");
    }

    // Endpoint para obtener una categoría por ID
    @GetMapping("/api/categorias/{id}")
    public ResponseEntity<Categoria> obtenerCategoria(@PathVariable("id") int id) {
        Categoria categoria = categoriaRepository.darCategoria(id);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

