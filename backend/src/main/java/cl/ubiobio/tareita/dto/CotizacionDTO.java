package cl.ubiobio.tareita.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CotizacionDTO {
    private Integer id;
    private LocalDateTime fecha;
    private String cliente;
    private Integer total;
    private String estado;
    private List<DetalleCotizacionDTO> detalles;

    public CotizacionDTO() {}

    // Builder Pattern
    public static class Builder {
        private Integer id;
        private LocalDateTime fecha;
        private String cliente;
        private Integer total;
        private String estado;
        private List<DetalleCotizacionDTO> detalles;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder fecha(LocalDateTime fecha) {
            this.fecha = fecha;
            return this;
        }

        public Builder cliente(String cliente) {
            this.cliente = cliente;
            return this;
        }

        public Builder total(Integer total) {
            this.total = total;
            return this;
        }

        public Builder estado(String estado) {
            this.estado = estado;
            return this;
        }

        public Builder detalles(List<DetalleCotizacionDTO> detalles) {
            this.detalles = detalles;
            return this;
        }

        public CotizacionDTO build() {
            CotizacionDTO dto = new CotizacionDTO();
            dto.id = this.id;
            dto.fecha = this.fecha;
            dto.cliente = this.cliente;
            dto.total = this.total;
            dto.estado = this.estado;
            dto.detalles = this.detalles;
            return dto;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<DetalleCotizacionDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCotizacionDTO> detalles) {
        this.detalles = detalles;
    }
}
