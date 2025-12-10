package cl.ubiobio.tareita.repositories;

import cl.ubiobio.tareita.models.DetalleCotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleCotizacionRepository extends JpaRepository<DetalleCotizacion, Integer> {
    List<DetalleCotizacion> findByCotizacionId(Integer cotizacionId);
}
