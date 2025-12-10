package cl.ubiobio.tareita.controllers;

import cl.ubiobio.tareita.dto.MuebleDTO;
import cl.ubiobio.tareita.mappers.MuebleMapper;
import cl.ubiobio.tareita.models.Mueble;
import cl.ubiobio.tareita.services.MuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller using DTO Pattern for data transfer
 */
@RestController
@RequestMapping("/api/muebles")
@CrossOrigin(origins = "*")
public class MuebleController {

    @Autowired
    private MuebleService muebleService;

    @Autowired
    private MuebleMapper muebleMapper;

    @GetMapping
    public ResponseEntity<List<MuebleDTO>> getAllMuebles() {
        List<Mueble> muebles = muebleService.getAllMuebles();
        List<MuebleDTO> mueblesDTO = muebles.stream()
                .map(muebleMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(mueblesDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MuebleDTO> getMuebleById(@PathVariable Integer id) {
        return muebleService.getMuebleById(id)
                .map(mueble -> new ResponseEntity<>(muebleMapper.toDTO(mueble), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<MuebleDTO>> getMueblesByEstado(@PathVariable Boolean estado) {
        List<Mueble> muebles = muebleService.getMueblesByEstado(estado);
        List<MuebleDTO> mueblesDTO = muebles.stream()
                .map(muebleMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(mueblesDTO, HttpStatus.OK);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<MuebleDTO>> getMueblesByTipo(@PathVariable String tipo) {
        List<Mueble> muebles = muebleService.getMueblesByTipo(tipo);
        List<MuebleDTO> mueblesDTO = muebles.stream()
                .map(muebleMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(mueblesDTO, HttpStatus.OK);
    }

    @GetMapping("/material/{material}")
    public ResponseEntity<List<MuebleDTO>> getMueblesByMaterial(@PathVariable String material) {
        List<Mueble> muebles = muebleService.getMueblesByMaterial(material);
        List<MuebleDTO> mueblesDTO = muebles.stream()
                .map(muebleMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(mueblesDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MuebleDTO> createMueble(@RequestBody MuebleDTO muebleDTO) {
        Mueble mueble = muebleMapper.toEntity(muebleDTO);
        Mueble newMueble = muebleService.createMueble(mueble);
        MuebleDTO newMuebleDTO = muebleMapper.toDTO(newMueble);
        return new ResponseEntity<>(newMuebleDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MuebleDTO> updateMueble(@PathVariable Integer id, @RequestBody MuebleDTO muebleDTO) {
        try {
            Mueble mueble = muebleMapper.toEntity(muebleDTO);
            Mueble updatedMueble = muebleService.updateMueble(id, mueble);
            MuebleDTO updatedMuebleDTO = muebleMapper.toDTO(updatedMueble);
            return new ResponseEntity<>(updatedMuebleDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMueble(@PathVariable Integer id) {
        try {
            muebleService.deleteMueble(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
