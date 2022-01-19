package com.ideas.saintpatricks.dto;

import com.ideas.saintpatricks.entities.Tarjeta;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TransDTO {
    private String id;
    @DateTimeFormat(style = "dd/MM/yyyy")
    private Date fecha;
    private Integer importe;
    private Tarjeta tarjeta;



}
