package cl.ubiobio.tareita.services;

import cl.ubiobio.tareita.models.Venta;
import cl.ubiobio.tareita.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> getVentaById(Integer id) {
        return ventaRepository.findById(id);
    }

    public List<Venta> getVentasByEstado(String estado) {
        return ventaRepository.findByEstado(estado);
    }

    public List<Venta> getVentasByMetodoPago(String metodoPago) {
        return ventaRepository.findByMetodoPago(metodoPago);
    }

    public Venta createVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public Venta updateVenta(Integer id, Venta ventaDetails) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + id));

        venta.setCotizacion(ventaDetails.getCotizacion());
        venta.setFecha(ventaDetails.getFecha());
        venta.setMetodoPago(ventaDetails.getMetodoPago());
        venta.setTotal(ventaDetails.getTotal());
        venta.setEstado(ventaDetails.getEstado());

        return ventaRepository.save(venta);
    }

    public void deleteVenta(Integer id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + id));
        ventaRepository.delete(venta);
    }
}
