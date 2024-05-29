package Laboratorio4com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
public class Instrumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    public String nombre;
    @Column
    public String instrumento;
    @Column
    public String marca;
    @Column
    public String modelo;

    @Column(length = 1000)
    public String imagen;
    @Column
    public String precio;
    @Column
    public String costoEnvio;
    @Column
    public String cantidadVendida;
    @Column
    public String descripcion;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;



}
