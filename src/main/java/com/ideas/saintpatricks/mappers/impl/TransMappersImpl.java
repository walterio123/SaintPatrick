package com.ideas.saintpatricks.mappers.impl;

import com.ideas.saintpatricks.dto.TransDTO;
import com.ideas.saintpatricks.entities.Trans;
import com.ideas.saintpatricks.mappers.TransMappers;

import java.util.ArrayList;
import java.util.List;

public class TransMappersImpl implements TransMappers {
    @Override
    public TransDTO toTransDto(Trans trans) {
        if ( trans == null ) {
            return null;
        }

        TransDTO transDTO = new TransDTO();

        transDTO.setId( trans.getId() );
        transDTO.setFecha( trans.getFecha() );
        transDTO.setImporte( trans.getImporte() );
        transDTO.setTarjeta( trans.getTarjeta() );

        return transDTO;
    }

    @Override
    public Trans toTrans(TransDTO transDTO) {
        if ( transDTO == null ) {
            return null;
        }

        Trans trans = new Trans();

        trans.setId( transDTO.getId() );
        trans.setFecha( transDTO.getFecha() );
        trans.setImporte( transDTO.getImporte() );
        trans.setTarjeta( transDTO.getTarjeta() );

        return trans;
    }

    @Override
    public List<TransDTO> toListTransDTO(List<Trans> transs) {
        if ( transs == null ) {
            return null;
        }

        List<TransDTO> list = new ArrayList<TransDTO>( transs.size() );
        for ( Trans trans : transs ) {
            list.add( toTransDto( trans ) );
        }

        return list;
    }
}
