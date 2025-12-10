package cl.ubiobio.tareita.models;

import jakarta.persistence.*;

@Entity
@Table(name = "variantes")
public class Variante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;
    private Integer aumentoPrecio;

    public Variante() {}

    public Variante(String nombre, String descripcion, Integer aumentoPrecio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.aumentoPrecio = aumentoPrecio;
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
