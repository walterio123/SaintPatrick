package mappers.impl;

import dto.ClienteDTO;
import entities.Cliente;
import entities.Tarjeta;
import mappers.ClienteMappers;

import java.util.ArrayList;
import java.util.List;

public class ClienteMappersImpl implements ClienteMappers {
    @Override
    public ClienteDTO toClienteDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setId( cliente.getId() );
        clienteDTO.setNombre( cliente.getNombre() );
        clienteDTO.setPin( cliente.getPin() );
        List<Tarjeta> list = cliente.getTarjetas();
        if ( list != null ) {
            clienteDTO.setTarjetas( new ArrayList<Tarjeta>( list ) );
        }

        return clienteDTO;
    }

    @Override
    public Cliente toCliente(ClienteDTO clienteDTO) {
        if ( clienteDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setId( clienteDTO.getId() );
        cliente.setNombre( clienteDTO.getNombre() );
        cliente.setPin( clienteDTO.getPin() );
        List<Tarjeta> list = clienteDTO.getTarjetas();
        if ( list != null ) {
            cliente.setTarjetas( new ArrayList<Tarjeta>( list ) );
        }

        return cliente;
    }

    @Override
    public List<ClienteDTO> toListClienteDTO(List<Cliente> clientes) {
        if ( clientes == null ) {
            return null;
        }

        List<ClienteDTO> list = new ArrayList<ClienteDTO>( clientes.size() );
        for ( Cliente cliente : clientes ) {
            list.add( toClienteDTO( cliente ) );
        }

        return list;
    }
}
