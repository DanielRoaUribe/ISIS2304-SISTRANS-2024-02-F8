package uniandes.edu.co.superandes.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.superandes.modelo.IngresoProductos;
import uniandes.edu.co.superandes.modelo.OrdenCompra;
import uniandes.edu.co.superandes.modelo.Proveedor;
import uniandes.edu.co.superandes.modelo.Sucursal;
import uniandes.edu.co.superandes.repositorio.IngresoProductosRepository;
import uniandes.edu.co.superandes.repositorio.OrdenCompraRepository;

@Service
public class IngresoProductosServicio {
    
    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Autowired
    private IngresoProductosRepository ingresoProductosRepository;
    //RF10
    @Transactional
    public void crearIngresoProductos(int idOrdenCompra, Integer idBodega) {

        

        // Agregar las cosas a la tabla
        String today = LocalDate.now().toString();
        Integer sucursal = ordenCompraRepository.darOrdenCompra(idOrdenCompra).getIdSucursal().getId();
        //
        Integer proveedor = ordenCompraRepository.darOrdenCompra(idOrdenCompra).getIdProveedor().getId();
        String nombreProducto = ordenCompraRepository.darOrdenCompra(idOrdenCompra).getIdProducto().getNombre();
        Integer cantidad = ordenCompraRepository.darOrdenCompra(idOrdenCompra).getCantidad();
        Integer precio = ordenCompraRepository.darOrdenCompra(idOrdenCompra).getIdProducto().getPrecio();
        ingresoProductosRepository.insertarIngresoProducto(today, sucursal, idBodega, proveedor, nombreProducto, cantidad, precio);

        // Cambiar orden de compra a ENTREGADA
        OrdenCompra ordenCompra = ordenCompraRepository.darOrdenCompra(idOrdenCompra);
        ordenCompraRepository.actualizarOrdenCompra(idOrdenCompra, ordenCompra.getFechaCreacion(), ordenCompra.getFechaEntrega(), "Entregada", ordenCompra.getPrecio(), ordenCompra.getCantidad(), ordenCompra.getIdProveedor().getId(), ordenCompra.getIdSucursal().getId(), ordenCompra.getIdProducto().getId());
    }

        // RFC6 - Nivel de aislamiento SERIALIZABLE y rollback en caso de error
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Collection<IngresoProductos> consultarIngresosSerializable(Integer idSucursal, Integer idBodega) {
        try {
            Thread.sleep(30000);  // Temporizador de 30 segundos
            Collection<IngresoProductos> resultados = ingresoProductosRepository.darIngresosProductos();
            return resultados;
        } catch (Exception e) {
            throw new RuntimeException("Error al realizar la consulta serializable: " + e.getMessage());
        }
    }

    // RFC7 - Nivel de aislamiento READ_COMMITTED y rollback en caso de error
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Collection<IngresoProductos> consultarIngresosReadCommitted(Integer idSucursal, Integer idBodega) {
        try {
            Thread.sleep(30000);  // Temporizador de 30 segundos
            Collection<IngresoProductos> resultados = ingresoProductosRepository.darIngresosProductos();
            return resultados;
        } catch (Exception e) {
            throw new RuntimeException("Error al realizar la consulta Read Committed: " + e.getMessage());
        }
    }

    // Método para mapear los resultados a una lista de DTOs
    private Collection<DocumentoIngresoDTO> mapToDTO(Collection<Object[]> resultados) {
        return resultados.stream()
            .map(obj -> new DocumentoIngresoDTO(
                (Integer) obj[0], // numeroDocumento
                (String) obj[1],  // fechaIngreso
                (String) obj[2],  // nombreProveedor
                (String) obj[3],  // nombreSucursal
                (String) obj[4]   // nombreBodega
            ))
            .collect(Collectors.toList());
    }

}
