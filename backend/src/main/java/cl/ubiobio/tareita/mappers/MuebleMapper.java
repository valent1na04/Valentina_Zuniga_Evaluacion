package cl.ubiobio.tareita.mappers;

import cl.ubiobio.tareita.dto.MuebleDTO;
import cl.ubiobio.tareita.models.Mueble;
import org.springframework.stereotype.Component;

/**
 * Mapper Pattern: Converts between Mueble entity and MuebleDTO
 */
@Component
public class MuebleMapper {

    public MuebleDTO toDTO(Mueble mueble) {
        if (mueble == null) return null;

        return MuebleDTO.builder()
                .id(mueble.getId())
                .nombre(mueble.getNombre())
                .tipo(mueble.getTipo())
                .precioBase(mueble.getPrecioBase())
                .stock(mueble.getStock())
                .estado(mueble.getEstado())
                .tamanio(mueble.getTamanio())
                .material(mueble.getMaterial())
                .build();
    }

    public Mueble toEntity(MuebleDTO dto) {
        if (dto == null) return null;

        Mueble mueble = new Mueble();
        mueble.setId(dto.getId());
        mueble.setNombre(dto.getNombre());
        mueble.setTipo(dto.getTipo());
        mueble.setPrecioBase(dto.getPrecioBase());
        mueble.setStock(dto.getStock());
        mueble.setEstado(dto.getEstado());
        mueble.setTamanio(dto.getTamanio());
        mueble.setMaterial(dto.getMaterial());

        return mueble;
    }
}
