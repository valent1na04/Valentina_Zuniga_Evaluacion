package cl.ubiobio.tareita.mappers;

import cl.ubiobio.tareita.dto.DetalleCotizacionDTO;
import cl.ubiobio.tareita.models.DetalleCotizacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mapper Pattern: Converts between DetalleCotizacion entity and DetalleCotizacionDTO
 */
@Component
public class DetalleCotizacionMapper {

    @Autowired
    private MuebleMapper muebleMapper;

    @Autowired
    private VarianteMapper varianteMapper;

    public DetalleCotizacionDTO toDTO(DetalleCotizacion detalle) {
        if (detalle == null) return null;

        return DetalleCotizacionDTO.builder()
                .id(detalle.getId())
                .cotizacionId(detalle.getCotizacion() != null ? detalle.getCotizacion().getId() : null)
                .mueble(muebleMapper.toDTO(detalle.getMueble()))
                .variante(varianteMapper.toDTO(detalle.getVariante()))
                .cantidad(detalle.getCantidad())
                .precioUnitario(detalle.getPrecioUnitario())
                .subtotal(detalle.getSubtotal())
                .build();
    }

    public DetalleCotizacion toEntity(DetalleCotizacionDTO dto) {
        if (dto == null) return null;

        DetalleCotizacion detalle = new DetalleCotizacion();
        detalle.setId(dto.getId());
        detalle.setMueble(muebleMapper.toEntity(dto.getMueble()));
        detalle.setVariante(varianteMapper.toEntity(dto.getVariante()));
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioUnitario(dto.getPrecioUnitario());
        detalle.setSubtotal(dto.getSubtotal());

        return detalle;
    }
}
