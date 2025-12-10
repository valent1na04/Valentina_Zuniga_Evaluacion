package cl.ubiobio.tareita.controllers;

import cl.ubiobio.tareita.dto.VarianteDTO;
import cl.ubiobio.tareita.mappers.VarianteMapper;
import cl.ubiobio.tareita.models.Variante;
import cl.ubiobio.tareita.services.VarianteService;
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
@RequestMapping("/api/variantes")
@CrossOrigin(origins = "*")
public class VarianteController {

    @Autowired
    private VarianteService varianteService;

    @Autowired
    private VarianteMapper varianteMapper;

    @GetMapping
    public ResponseEntity<List<VarianteDTO>> getAllVariantes() {
        List<Variante> variantes = varianteService.getAllVariantes();
        List<VarianteDTO> variantesDTO = variantes.stream()
                .map(varianteMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(variantesDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VarianteDTO> getVarianteById(@PathVariable Integer id) {
        return varianteService.getVarianteById(id)
                .map(variante -> new ResponseEntity<>(varianteMapper.toDTO(variante), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<VarianteDTO> createVariante(@RequestBody VarianteDTO varianteDTO) {
        Variante variante = varianteMapper.toEntity(varianteDTO);
        Variante newVariante = varianteService.createVariante(variante);
        VarianteDTO newVarianteDTO = varianteMapper.toDTO(newVariante);
        return new ResponseEntity<>(newVarianteDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VarianteDTO> updateVariante(@PathVariable Integer id, @RequestBody VarianteDTO varianteDTO) {
        try {
            Variante variante = varianteMapper.toEntity(varianteDTO);
            Variante updatedVariante = varianteService.updateVariante(id, variante);
            VarianteDTO updatedVarianteDTO = varianteMapper.toDTO(updatedVariante);
            return new ResponseEntity<>(updatedVarianteDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVariante(@PathVariable Integer id) {
        try {
            varianteService.deleteVariante(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
