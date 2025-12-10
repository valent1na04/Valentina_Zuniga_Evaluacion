package cl.ubiobio.tareita.mappers;

import cl.ubiobio.tareita.dto.CotizacionDTO;
import cl.ubiobio.tareita.dto.DetalleCotizacionDTO;
import cl.ubiobio.tareita.models.Cotizacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper Pattern: Converts between Cotizacion entity and CotizacionDTO
 */
@Component
public class CotizacionMapper {

    @Autowired
    private DetalleCotizacionMapper detalleCotizacionMapper;

    public CotizacionDTO toDTO(Cotizacion cotizacion) {
        if (cotizacion == null) return null;

        List<DetalleCotizacionDTO> detallesDTO = new ArrayList<>();
        if (cotizacion.getDetalles() != null) {
            detallesDTO = cotizacion.getDetalles().stream()
                    .map(detalleCotizacionMapper::toDTO)
                    .collect(Collectors.toList());
        }

        return CotizacionDTO.builder()
                .id(cotizacion.getId())
                .fecha(cotizacion.getFecha())
                .cliente(cotizacion.getCliente())
                .total(cotizacion.getTotal())
                .estado(cotizacion.getEstado())
                .detalles(detallesDTO)
                .build();
    }

    public Cotizacion toEntity(CotizacionDTO dto) {
        if (dto == null) return null;

        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setId(dto.getId());
        cotizacion.setFecha(dto.getFecha());
        cotizacion.setCliente(dto.getCliente());
        cotizacion.setTotal(dto.getTotal());
        cotizacion.setEstado(dto.getEstado());

        return cotizacion;
    }
}
