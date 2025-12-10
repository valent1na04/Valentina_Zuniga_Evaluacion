package cl.ubiobio.tareita.repositories;

import cl.ubiobio.tareita.models.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer> {
    List<Cotizacion> findByCliente(String cliente);
    List<Cotizacion> findByEstado(String estado);
}
