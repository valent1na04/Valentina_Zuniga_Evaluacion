package cl.ubiobio.tareita.repositories;

import cl.ubiobio.tareita.models.Variante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VarianteRepository extends JpaRepository<Variante, Integer> {
}
