package com.ideas.saintpatricks.services;

import com.ideas.saintpatricks.dto.ClienteDTO;
import com.ideas.saintpatricks.entities.Cliente;
import com.ideas.saintpatricks.mappers.ClienteMappers;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.ideas.saintpatricks.repository.ClienteRepository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;



@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;


    private final  ClienteMappers clienteMappers;

    @Transactional
    public ClienteDTO save(ClienteDTO clienteDTO){
        Cliente cliente=clienteMappers.toCliente(clienteDTO);

        return  clienteMappers.toClienteDTO( clienteRepository.save(cliente));
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll(){
        List<ClienteDTO>clienteDTOList=clienteMappers.toListClienteDTO(clienteRepository.findAll());
        return clienteDTOList;
    }
    @Transactional(readOnly=true)
    public ClienteDTO findById(String clienteId) throws NotFoundException {
        var respOptional=clienteRepository.findById(clienteId);
        if(respOptional.isPresent()){
            Cliente cliente=(respOptional.get());
            ClienteDTO dto=clienteMappers.toClienteDTO(cliente);
            return dto;
        }else{
             throw new NotFoundException("Not card with: "+clienteId+" id.");
        }

    }

}
