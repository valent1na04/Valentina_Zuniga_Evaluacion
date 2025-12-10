package cl.ubiobio.tareita.repositories;

import cl.ubiobio.tareita.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    List<Venta> findByEstado(String estado);
    List<Venta> findByMetodoPago(String metodoPago);
}
