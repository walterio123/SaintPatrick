package com.ideas.saintpatricks.mappers.impl;

import com.ideas.saintpatricks.dto.TarjetaDTO;
import com.ideas.saintpatricks.entities.Tarjeta;
import com.ideas.saintpatricks.entities.Trans;
import com.ideas.saintpatricks.mappers.TarjetaMappers;

import java.util.ArrayList;
import java.util.List;

public class TarjetaMappersImpl implements TarjetaMappers {
    @Override
    public Tarjeta toTarjeta(TarjetaDTO tarjetaDTO) {
        if ( tarjetaDTO == null ) {
            return null;
        }

        Tarjeta tarjeta = new Tarjeta();

        tarjeta.setId( tarjetaDTO.getId() );
        tarjeta.setNroTarjeta( tarjetaDTO.getNroTarjeta() );
        tarjeta.setSaldoActual( tarjetaDTO.getSaldoActual() );
        List<Trans> list = tarjetaDTO.getTranses();
        if ( list != null ) {
            tarjeta.setTranses( new ArrayList<Trans>( list ) );
        }
        tarjeta.setCliente( tarjetaDTO.getCliente() );

        return tarjeta;
    }

    @Override
    public TarjetaDTO ToTarjetaDTO(Tarjeta tarjeta) {
        if ( tarjeta == null ) {
            return null;
        }

        TarjetaDTO tarjetaDTO = new TarjetaDTO();

        tarjetaDTO.setId( tarjeta.getId() );
        tarjetaDTO.setNroTarjeta( tarjeta.getNroTarjeta() );
        tarjetaDTO.setSaldoActual( tarjeta.getSaldoActual() );
        List<Trans> list = tarjeta.getTranses();
        if ( list != null ) {
            tarjetaDTO.setTranses( new ArrayList<Trans>( list ) );
        }
        tarjetaDTO.setCliente( tarjeta.getCliente() );

        return tarjetaDTO;
    }

    @Override
    public List<TarjetaDTO> toListTarjetaDTO(List<Tarjeta> tarjetas) {
        if ( tarjetas == null ) {
            return null;
        }

        List<TarjetaDTO> list = new ArrayList<TarjetaDTO>( tarjetas.size() );
        for ( Tarjeta tarjeta : tarjetas ) {
            list.add( ToTarjetaDTO( tarjeta ) );
        }

        return list;
    }
}
