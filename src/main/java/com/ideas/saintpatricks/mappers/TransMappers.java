package com.ideas.saintpatricks.mappers;

import com.ideas.saintpatricks.dto.TransDTO;
import com.ideas.saintpatricks.entities.Trans;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TransMappers {
    TransDTO toTransDto(Trans trans);
    Trans toTrans(TransDTO transDTO);
    List<TransDTO> toListTransDTO(List<Trans>transs);
}
