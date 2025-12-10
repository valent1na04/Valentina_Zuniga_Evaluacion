package cl.ubiobio.tareita.mappers;

import cl.ubiobio.tareita.dto.VarianteDTO;
import cl.ubiobio.tareita.models.Variante;
import org.springframework.stereotype.Component;

/**
 * Mapper Pattern: Converts between Variante entity and VarianteDTO
 */
@Component
public class VarianteMapper {

    public VarianteDTO toDTO(Variante variante) {
        if (variante == null) return null;

        return VarianteDTO.builder()
                .id(variante.getId())
                .nombre(variante.getNombre())
                .descripcion(variante.getDescripcion())
                .aumentoPrecio(variante.getAumentoPrecio())
                .build();
    }

    public Variante toEntity(VarianteDTO dto) {
        if (dto == null) return null;

        Variante variante = new Variante();
        variante.setId(dto.getId());
        variante.setNombre(dto.getNombre());
        variante.setDescripcion(dto.getDescripcion());
        variante.setAumentoPrecio(dto.getAumentoPrecio());

        return variante;
    }
}
