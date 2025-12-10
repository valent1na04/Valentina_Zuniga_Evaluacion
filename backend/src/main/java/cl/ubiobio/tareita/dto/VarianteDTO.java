package cl.ubiobio.tareita.dto;

public class VarianteDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer aumentoPrecio;

    public VarianteDTO() {}

    // Builder Pattern
    public static class Builder {
        private Integer id;
        private String nombre;
        private String descripcion;
        private Integer aumentoPrecio;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder descripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Builder aumentoPrecio(Integer aumentoPrecio) {
            this.aumentoPrecio = aumentoPrecio;
            return this;
        }

        public VarianteDTO build() {
            VarianteDTO dto = new VarianteDTO();
            dto.id = this.id;
            dto.nombre = this.nombre;
            dto.descripcion = this.descripcion;
            dto.aumentoPrecio = this.aumentoPrecio;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getAumentoPrecio() {
        return aumentoPrecio;
    }

    public void setAumentoPrecio(Integer aumentoPrecio) {
        this.aumentoPrecio = aumentoPrecio;
    }
}
