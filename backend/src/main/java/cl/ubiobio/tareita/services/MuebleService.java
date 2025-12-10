package cl.ubiobio.tareita.services;

import cl.ubiobio.tareita.models.Mueble;
import cl.ubiobio.tareita.repositories.MuebleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MuebleService {

    @Autowired
    private MuebleRepository muebleRepository;

    public List<Mueble> getAllMuebles() {
        return muebleRepository.findAll();
    }

    public Optional<Mueble> getMuebleById(Integer id) {
        return muebleRepository.findById(id);
    }

    public List<Mueble> getMueblesByEstado(Boolean estado) {
        return muebleRepository.findByEstado(estado);
    }

    public List<Mueble> getMueblesByTipo(String tipo) {
        return muebleRepository.findByTipo(tipo);
    }

    public List<Mueble> getMueblesByMaterial(String material) {
        return muebleRepository.findByMaterial(material);
    }

    public Mueble createMueble(Mueble mueble) {
        return muebleRepository.save(mueble);
    }

    public Mueble updateMueble(Integer id, Mueble muebleDetails) {
        Mueble mueble = muebleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mueble no encontrado con id: " + id));

        mueble.setNombre(muebleDetails.getNombre());
        mueble.setTipo(muebleDetails.getTipo());
        mueble.setPrecioBase(muebleDetails.getPrecioBase());
        mueble.setStock(muebleDetails.getStock());
        mueble.setEstado(muebleDetails.getEstado());
        mueble.setTamanio(muebleDetails.getTamanio());
        mueble.setMaterial(muebleDetails.getMaterial());

        return muebleRepository.save(mueble);
    }

    public void deleteMueble(Integer id) {
        Mueble mueble = muebleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mueble no encontrado con id: " + id));
        muebleRepository.delete(mueble);
    }
}
