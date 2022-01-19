package com.ideas.saintpatricks.services;

import com.ideas.saintpatricks.dto.TransDTO;
import com.ideas.saintpatricks.entities.Trans;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;


import com.ideas.saintpatricks.mappers.TransMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ideas.saintpatricks.repository.TransRepository;

import java.util.List;



@Service
@AllArgsConstructor
public class TransServices {
    @Autowired
    private  TransRepository transRepository;
   // @Autowired
    private   final TransMappers transMappers;

    @Transactional
    public TransDTO save(TransDTO transDTO) {
        Trans trans = transMappers.toTrans(transDTO);

        return transMappers.toTransDto(transRepository.save(trans));
    }

    @Transactional(readOnly = false)
    public List<TransDTO> findAll() {
        return transMappers.toListTransDTO(transRepository.findAll());
    }

    @Transactional(readOnly = true)
    public TransDTO findById(String transId) throws NotFoundException {

       var respOptional = transRepository.findById(transId);
        if(respOptional.isPresent()){
            TransDTO dto=transMappers.toTransDto(respOptional.get());
            return dto;
        }else{
           throw  new NotFoundException("There is no Transaction with that identification.");
        }


    }
}