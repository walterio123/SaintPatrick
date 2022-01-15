package entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

import java.util.List;


@Table(name = "tarjeta")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "nro_tarjeta", nullable = false)
    private String nroTarjeta;

    @Column(name = "saldo_actual", nullable = false)
    private Integer saldoActual;

    @OneToMany(mappedBy = "tarjeta", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Trans> transes = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

}