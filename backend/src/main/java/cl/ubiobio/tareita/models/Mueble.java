package cl.ubiobio.tareita.models;

import jakarta.persistence.*;

@Entity
@Table(name = "muebles")
public class Mueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String tipo;
    private Integer precioBase;
    private Integer stock;
    private Boolean estado;
    private String tamanio;
    private String material;

    public Mueble() {}

    public Mueble(String nombre, String tipo, Integer precioBase, Integer stock, Boolean estado, String tamanio, String material) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precioBase = precioBase;
        this.stock = stock;
        this.estado = estado;
        this.tamanio = tamanio;
        this.material = material;
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
