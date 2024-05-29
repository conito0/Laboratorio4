package Laboratorio4com.example.demo.entities;

import Laboratorio4com.example.demo.controller.PreferenceMP;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    public Instant fechaPedido;

    @Column
    public double totalPedido;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PedidoDetalle> detalles = new ArrayList<>();

    @Transient
    private PreferenceMP preferenceMP;

}
