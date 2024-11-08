package uniandes.edu.co.superandes.servicios;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
