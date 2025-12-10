package cl.ubiobio.tareita.repositories;

import cl.ubiobio.tareita.models.Mueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MuebleRepository extends JpaRepository<Mueble, Integer> {
    List<Mueble> findByEstado(Boolean estado);
    List<Mueble> findByTipo(String tipo);
    List<Mueble> findByMaterial(String material);
}
