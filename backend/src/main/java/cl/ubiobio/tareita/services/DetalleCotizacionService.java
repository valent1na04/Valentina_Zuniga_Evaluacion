package cl.ubiobio.tareita.services;

import cl.ubiobio.tareita.models.DetalleCotizacion;
import cl.ubiobio.tareita.repositories.DetalleCotizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DetalleCotizacionService {

    @Autowired
    private DetalleCotizacionRepository detalleCotizacionRepository;

    public List<DetalleCotizacion> getAllDetalles() {
        return detalleCotizacionRepository.findAll();
    }

    public Optional<DetalleCotizacion> getDetalleById(Integer id) {
        return detalleCotizacionRepository.findById(id);
    }

    public List<DetalleCotizacion> getDetallesByCotizacionId(Integer cotizacionId) {
        return detalleCotizacionRepository.findByCotizacionId(cotizacionId);
    }

    public DetalleCotizacion createDetalle(DetalleCotizacion detalle) {
        return detalleCotizacionRepository.save(detalle);
    }

    public DetalleCotizacion updateDetalle(Integer id, DetalleCotizacion detalleDetails) {
        DetalleCotizacion detalle = detalleCotizacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado con id: " + id));

        detalle.setCotizacion(detalleDetails.getCotizacion());
        detalle.setMueble(detalleDetails.getMueble());
        detalle.setVariante(detalleDetails.getVariante());
        detalle.setCantidad(detalleDetails.getCantidad());
        detalle.setPrecioUnitario(detalleDetails.getPrecioUnitario());
        detalle.setSubtotal(detalleDetails.getSubtotal());

        return detalleCotizacionRepository.save(detalle);
    }

    public void deleteDetalle(Integer id) {
        DetalleCotizacion detalle = detalleCotizacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado con id: " + id));
        detalleCotizacionRepository.delete(detalle);
    }
}
