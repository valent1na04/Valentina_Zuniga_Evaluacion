package cl.ubiobio.tareita.mappers;

import cl.ubiobio.tareita.dto.VentaDTO;
import cl.ubiobio.tareita.models.Venta;
import org.springframework.stereotype.Component;

/**
 * Mapper Pattern: Converts between Venta entity and VentaDTO
 */
@Component
public class VentaMapper {

    public VentaDTO toDTO(Venta venta) {
        if (venta == null) return null;

        return VentaDTO.builder()
                .id(venta.getId())
                .cotizacionId(venta.getCotizacion() != null ? venta.getCotizacion().getId() : null)
                .fecha(venta.getFecha())
                .metodoPago(venta.getMetodoPago())
                .total(venta.getTotal())
                .estado(venta.getEstado())
                .build();
    }

    public Venta toEntity(VentaDTO dto) {
        if (dto == null) return null;

        Venta venta = new Venta();
        venta.setId(dto.getId());
        venta.setFecha(dto.getFecha());
        venta.setMetodoPago(dto.getMetodoPago());
        venta.setTotal(dto.getTotal());
        venta.setEstado(dto.getEstado());

        return venta;
    }
}
