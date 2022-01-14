package services;

import entities.Cliente;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ClienteRepository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente save(Cliente cliente){
        return  clienteRepository.save(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }
    @Transactional(readOnly=true)
    public Optional<Cliente>findById(String clienteId){
        return clienteRepository.findById(clienteId);
    }

}
