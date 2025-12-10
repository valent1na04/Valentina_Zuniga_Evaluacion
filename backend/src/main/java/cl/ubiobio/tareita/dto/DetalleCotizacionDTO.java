package cl.ubiobio.tareita.dto;

public class DetalleCotizacionDTO {
    private Integer id;
    private Integer cotizacionId;
    private MuebleDTO mueble;
    private VarianteDTO variante;
    private Integer cantidad;
    private Integer precioUnitario;
    private Integer subtotal;

    public DetalleCotizacionDTO() {}

    // Builder Pattern
    public static class Builder {
        private Integer id;
        private Integer cotizacionId;
        private MuebleDTO mueble;
        private VarianteDTO variante;
        private Integer cantidad;
        private Integer precioUnitario;
        private Integer subtotal;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder cotizacionId(Integer cotizacionId) {
            this.cotizacionId = cotizacionId;
            return this;
        }

        public Builder mueble(MuebleDTO mueble) {
            this.mueble = mueble;
            return this;
        }

        public Builder variante(VarianteDTO variante) {
            this.variante = variante;
            return this;
        }

        public Builder cantidad(Integer cantidad) {
            this.cantidad = cantidad;
            return this;
        }

        public Builder precioUnitario(Integer precioUnitario) {
            this.precioUnitario = precioUnitario;
            return this;
        }

        public Builder subtotal(Integer subtotal) {
            this.subtotal = subtotal;
            return this;
        }

        public DetalleCotizacionDTO build() {
            DetalleCotizacionDTO dto = new DetalleCotizacionDTO();
            dto.id = this.id;
            dto.cotizacionId = this.cotizacionId;
            dto.mueble = this.mueble;
            dto.variante = this.variante;
            dto.cantidad = this.cantidad;
            dto.precioUnitario = this.precioUnitario;
            dto.subtotal = this.subtotal;
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

    public MuebleDTO getMueble() {
        return mueble;
    }

    public void setMueble(MuebleDTO mueble) {
        this.mueble = mueble;
    }

    public VarianteDTO getVariante() {
        return variante;
    }

    public void setVariante(VarianteDTO variante) {
        this.variante = variante;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Integer precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }
}
