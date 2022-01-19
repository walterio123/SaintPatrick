package com.ideas.saintpatricks.mappers;

import com.ideas.saintpatricks.dto.TarjetaDTO;
import com.ideas.saintpatricks.entities.Tarjeta;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper
public interface TarjetaMappers {

    Tarjeta toTarjeta(TarjetaDTO tarjetaDTO);
    TarjetaDTO ToTarjetaDTO(Tarjeta tarjeta);
    List<TarjetaDTO> toListTarjetaDTO(List<Tarjeta>tarjetas);
}
