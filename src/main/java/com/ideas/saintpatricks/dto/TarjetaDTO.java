package com.ideas.saintpatricks.dto;

import com.ideas.saintpatricks.entities.Cliente;
import com.ideas.saintpatricks.entities.Trans;
import lombok.*;


import java.util.ArrayList;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarjetaDTO {
    private String id;

    private String nroTarjeta;

    private Integer saldoActual;

    private List<Trans> transes = new ArrayList<>();
    private Cliente cliente;
}
