package mappers.test;

import dto.ClienteDTO;
import entities.Cliente;
import entities.Tarjeta;
import entities.Trans;
import mappers.ClienteMappers;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ClienteMappersTest<listedTrans> {
    private final ClienteMappers mapper= Mappers.getMapper(ClienteMappers.class);
    @Test
    public void ClienteMappersTestSimple(){
        List<Trans>transacciones=new ArrayList<>();
        Tarjeta tarjetaCliente0=new Tarjeta();
        tarjetaCliente0.setId("poe3-o455-y54k-8so3");
        tarjetaCliente0.setNroTarjeta("983455739454");
        tarjetaCliente0.setSaldoActual(10000);
        tarjetaCliente0.setTranses(transacciones);

        Tarjeta tarjetaCliente1=new Tarjeta();
        tarjetaCliente1.setId("poja-o4t5-y54k-8so3");
        tarjetaCliente1.setNroTarjeta("192455739454");
        tarjetaCliente1.setSaldoActual(5000);
        tarjetaCliente1.setTranses(transacciones);

        List<Tarjeta>tarjetaList=new ArrayList<>(Arrays.asList(tarjetaCliente0,tarjetaCliente1));

        Cliente cliente=new Cliente();
        cliente.setId("ijn3-7dh3-6vn3-8en2");
        cliente.setNombre("waly");
        cliente.setPin("8774");
        cliente.setTarjetas(tarjetaList);

        Cliente cliente2=new Cliente();
        cliente2.setId("iEr3-7dh3-6vn3-8en2");
        cliente2.setNombre("nano");
        cliente2.setPin("7421");
        cliente2.setTarjetas(tarjetaList);
        List<Cliente>listClient=new ArrayList<>(Arrays.asList(cliente,cliente2));

        ClienteDTO dto=mapper.toClienteDTO(cliente);

        List<ClienteDTO>clienteDTOList=mapper.toListClienteDTO(listClient);
        assertEquals(dto.getId(),cliente.getId());
        assertEquals(dto.getTarjetas().get(0).getNroTarjeta(),
                cliente.getTarjetas().get(0).getNroTarjeta());
        assertEquals(2,dto.getTarjetas().size());
        assertEquals(2,clienteDTOList.size());




}
}
