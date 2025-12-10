package cl.ubiobio.tareita.dto;

import java.time.LocalDateTime;

public class VentaDTO {
    private Integer id;
    private Integer cotizacionId;
    private LocalDateTime fecha;
    private String metodoPago;
    private Integer total;
    private String estado;

    public VentaDTO() {}

    // Builder Pattern
    public static class Builder {
        private Integer id;
        private Integer cotizacionId;
        private LocalDateTime fecha;
        private String metodoPago;
        private Integer total;
        private String estado;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder cotizacionId(Integer cotizacionId) {
            this.cotizacionId = cotizacionId;
            return this;
        }

        public Builder fecha(LocalDateTime fecha) {
            this.fecha = fecha;
            return this;
        }

        public Builder metodoPago(String metodoPago) {
            this.metodoPago = metodoPago;
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

        public VentaDTO build() {
            VentaDTO dto = new VentaDTO();
            dto.id = this.id;
            dto.cotizacionId = this.cotizacionId;
            dto.fecha = this.fecha;
            dto.metodoPago = this.metodoPago;
            dto.total = this.total;
            dto.estado = this.estado;
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

    public Integer getCotizacionId() {
        return cotizacionId;
    }

    public void setCotizacionId(Integer cotizacionId) {
        this.cotizacionId = cotizacionId;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
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
}
