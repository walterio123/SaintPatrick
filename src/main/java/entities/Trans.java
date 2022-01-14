package entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Trans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Date fecha;
    private Integer importe;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tarjeta_id", nullable = false)
    private Tarjeta tarjeta;

}
