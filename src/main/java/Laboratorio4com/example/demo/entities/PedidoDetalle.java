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
public class PedidoDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    public int cantidad;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "instrumento_id")
    private Instrumento instrumento;



}
