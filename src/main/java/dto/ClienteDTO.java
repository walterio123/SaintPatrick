package dto;

import entities.Tarjeta;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {
    private String id;
    private String nombre;
    private String pin;
    private List<Tarjeta> tarjetas = new ArrayList<>();
}
