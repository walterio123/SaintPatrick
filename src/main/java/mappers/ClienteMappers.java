package mappers;

import dto.ClienteDTO;
import entities.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ClienteMappers {
    ClienteDTO toClienteDTO(Cliente cliente);
   Cliente toCliente(ClienteDTO clienteDTO);
   List<ClienteDTO> toListClienteDTO(List<Cliente>clientes);
}
