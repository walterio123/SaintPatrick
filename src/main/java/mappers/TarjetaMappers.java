package mappers;

import dto.TarjetaDTO;
import entities.Tarjeta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper
public interface TarjetaMappers {

    Tarjeta toTarjeta(TarjetaDTO tarjetaDTO);
    TarjetaDTO ToTarjetaDTO(Tarjeta tarjeta);
    List<TarjetaDTO> toListTarjetaDTO(List<Tarjeta>tarjetas);
}
