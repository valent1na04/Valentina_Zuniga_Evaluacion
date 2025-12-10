package cl.ubiobio.tareita.services;

import cl.ubiobio.tareita.models.Variante;
import cl.ubiobio.tareita.repositories.VarianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VarianteService {

    @Autowired
    private VarianteRepository varianteRepository;

    public List<Variante> getAllVariantes() {
        return varianteRepository.findAll();
    }

    public Optional<Variante> getVarianteById(Integer id) {
        return varianteRepository.findById(id);
    }

    public Variante createVariante(Variante variante) {
        return varianteRepository.save(variante);
    }

    public Variante updateVariante(Integer id, Variante varianteDetails) {
        Variante variante = varianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Variante no encontrada con id: " + id));

        variante.setNombre(varianteDetails.getNombre());
        variante.setDescripcion(varianteDetails.getDescripcion());
        variante.setAumentoPrecio(varianteDetails.getAumentoPrecio());

        return varianteRepository.save(variante);
    }

    public void deleteVariante(Integer id) {
        Variante variante = varianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Variante no encontrada con id: " + id));
        varianteRepository.delete(variante);
    }
}
