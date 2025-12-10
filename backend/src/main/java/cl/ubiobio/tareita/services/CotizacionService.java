package cl.ubiobio.tareita.services;

import cl.ubiobio.tareita.models.Cotizacion;
import cl.ubiobio.tareita.repositories.CotizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CotizacionService {

    @Autowired
    private CotizacionRepository cotizacionRepository;

    public List<Cotizacion> getAllCotizaciones() {
        return cotizacionRepository.findAll();
    }

    public Optional<Cotizacion> getCotizacionById(Integer id) {
        return cotizacionRepository.findById(id);
    }

    public List<Cotizacion> getCotizacionesByCliente(String cliente) {
        return cotizacionRepository.findByCliente(cliente);
    }

    public List<Cotizacion> getCotizacionesByEstado(String estado) {
        return cotizacionRepository.findByEstado(estado);
    }

    public Cotizacion createCotizacion(Cotizacion cotizacion) {
        return cotizacionRepository.save(cotizacion);
    }

    public Cotizacion updateCotizacion(Integer id, Cotizacion cotizacionDetails) {
        Cotizacion cotizacion = cotizacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cotizacion no encontrada con id: " + id));

        cotizacion.setFecha(cotizacionDetails.getFecha());
        cotizacion.setCliente(cotizacionDetails.getCliente());
        cotizacion.setTotal(cotizacionDetails.getTotal());
        cotizacion.setEstado(cotizacionDetails.getEstado());

        return cotizacionRepository.save(cotizacion);
    }

    public void deleteCotizacion(Integer id) {
        Cotizacion cotizacion = cotizacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cotizacion no encontrada con id: " + id));
        cotizacionRepository.delete(cotizacion);
    }
}
