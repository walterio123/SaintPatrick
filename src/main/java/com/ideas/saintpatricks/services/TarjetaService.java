package com.ideas.saintpatricks.services;


import com.ideas.saintpatricks.dto.TarjetaDTO;
import com.ideas.saintpatricks.entities.Tarjeta;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import com.ideas.saintpatricks.mappers.TarjetaMappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ideas.saintpatricks.repository.TarjetaRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class TarjetaService {

    private final TarjetaRepository tarjetaRepository;
    private final TarjetaMappers tarjetaMappers;

    @Transactional
    public TarjetaDTO save(TarjetaDTO tarjetaDTO){
        Tarjeta tarjeta=tarjetaMappers.toTarjeta(tarjetaDTO);
        return tarjetaMappers.ToTarjetaDTO(tarjetaRepository.save(tarjeta));
    }
    @Transactional(readOnly = true)
    public List<TarjetaDTO> findAll(){

        return tarjetaMappers.toListTarjetaDTO(tarjetaRepository.findAll());
    }

    @Transactional(readOnly = true)
    public TarjetaDTO findById(String tarjetaId) throws NotFoundException{
        var respOptional=tarjetaRepository.findById(tarjetaId);
        if(respOptional.isPresent()){
            Tarjeta tarjeta=respOptional.get();
            return tarjetaMappers.ToTarjetaDTO(tarjeta);
        }else{
            throw  new NotFoundException("Not card with: "+tarjetaId+" id.");
        }
    }

}
