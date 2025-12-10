package cl.ubiobio.tareita.dto;

public class MuebleDTO {
    private Integer id;
    private String nombre;
    private String tipo;
    private Integer precioBase;
    private Integer stock;
    private Boolean estado;
    private String tamanio;
    private String material;

    public MuebleDTO() {}

    // Builder Pattern
    public static class Builder {
        private Integer id;
        private String nombre;
        private String tipo;
        private Integer precioBase;
        private Integer stock;
        private Boolean estado;
        private String tamanio;
        private String material;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder tipo(String tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder precioBase(Integer precioBase) {
            this.precioBase = precioBase;
            return this;
        }

        public Builder stock(Integer stock) {
            this.stock = stock;
            return this;
        }

        public Builder estado(Boolean estado) {
            this.estado = estado;
            return this;
        }

        public Builder tamanio(String tamanio) {
            this.tamanio = tamanio;
            return this;
        }

        public Builder material(String material) {
            this.material = material;
            return this;
        }

        public MuebleDTO build() {
            MuebleDTO dto = new MuebleDTO();
            dto.id = this.id;
            dto.nombre = this.nombre;
            dto.tipo = this.tipo;
            dto.precioBase = this.precioBase;
            dto.stock = this.stock;
            dto.estado = this.estado;
            dto.tamanio = this.tamanio;
            dto.material = this.material;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Integer precioBase) {
        this.precioBase = precioBase;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
