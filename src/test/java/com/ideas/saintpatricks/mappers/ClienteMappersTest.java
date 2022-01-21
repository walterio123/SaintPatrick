package com.ideas.saintpatricks.mappers;

import com.ideas.saintpatricks.dto.ClienteDTO;
import com.ideas.saintpatricks.dto.TarjetaDTO;
import com.ideas.saintpatricks.dto.TransDTO;
import com.ideas.saintpatricks.entities.Cliente;
import com.ideas.saintpatricks.entities.Tarjeta;
import com.ideas.saintpatricks.entities.Trans;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ClienteMappersTest {
    private static ClienteMappers mapper;//= Mappers.getMapper(ClienteMappers.class);
    private static TarjetaMappers tarjetaMappers;
    @BeforeAll
    public static void setUp(){
        mapper= Mappers.getMapper(ClienteMappers.class);
        tarjetaMappers=Mappers.getMapper(TarjetaMappers.class);
    }

    @Test
    public void ClienteMappersTestSimple(){
        List<Trans>transacciones=new ArrayList<>();
        Cliente clienteForCard=new Cliente();
        Tarjeta tarjetaCliente0= Tarjeta.builder()
                .id("poe3-o455-y54k-8so3")
                .nroTarjeta("983455739454")
                .saldoActual(1000)
                .transes(transacciones)
                .cliente(clienteForCard)
                .build();

        Tarjeta tarjetaCliente1=Tarjeta.builder()
                .id("poja-o4t5-y54k-8so3")
                .nroTarjeta("192455739454")
                .saldoActual(5000)
                .transes(transacciones)
                .cliente(clienteForCard)
                .build();


        List<Tarjeta>tarjetaList=new ArrayList<>(Arrays.asList(tarjetaCliente0,tarjetaCliente1));

        Cliente cliente= Cliente.builder()
                .id("ijn3-7dh3-6vn3-8en2")
                .nombre("waly")
                .pin("8774")
                . tarjetas(tarjetaList).build();

        Cliente cliente2= Cliente.builder()
                .id("iER3-7dh3-6vn3-8en2")
                .nombre("nano")
                .pin("7421")
                . tarjetas(tarjetaList).build();

        List<Cliente>listClient=new ArrayList<>(Arrays.asList(cliente,cliente2));

        ClienteDTO dto=mapper.toClienteDTO(cliente);

        List<ClienteDTO>clienteDTOList=mapper.toListClienteDTO(listClient);

        assertAll(
                ()->{
                    assertEquals(dto.getId(),cliente.getId());
                    assertEquals(dto.getTarjetas().get(0).getNroTarjeta(),
                            cliente.getTarjetas().get(0).getNroTarjeta());
                    assertEquals(2,dto.getTarjetas().size());
                    assertEquals(2,clienteDTOList.size());
                    System.out.println(cliente);
                    System.out.println(dto);
                }
        );

    }
    @Test
    public void ClienteMappersTestSimpleNull(){
        List<Trans>transacciones=new ArrayList<>();
        Cliente clienteForCard=new Cliente();
        Tarjeta tarjetaCliente0= Tarjeta.builder()
                .id("poe3-o455-y54k-8so3")
                .nroTarjeta("983455739454")
                .saldoActual(1000)
                .transes(transacciones)
                .cliente(clienteForCard)
                .build();

        Tarjeta tarjetaCliente1=Tarjeta.builder()
                .id("poja-o4t5-y54k-8so3")
                .nroTarjeta("192455739454")
                .saldoActual(5000)
                .transes(transacciones)
                .cliente(clienteForCard)
                .build();


        List<Tarjeta>tarjetaList=new ArrayList<>(Arrays.asList(tarjetaCliente0,tarjetaCliente1));

        Cliente cliente= null;
        ClienteDTO dto=mapper.toClienteDTO(cliente);
        assertEquals(null,dto);


    }
    @Test
    void toCliente() {
        List<TransDTO>transaccionesDTO=new ArrayList<>();
        List<Trans>transacciones=new ArrayList<>();;
        ClienteDTO clienteForCardDTO=new ClienteDTO();
        Cliente clienteForCard=new Cliente();
        TarjetaDTO tarjetaCliente0= TarjetaDTO.builder()
                .id("poe3-o455-y54k-8so3")
                .nroTarjeta("983455739454")
                .saldoActual(1000)
                .transes(transacciones)
                .cliente(clienteForCard)
                .build();

        TarjetaDTO tarjetaCliente1=TarjetaDTO.builder()
                .id("poja-o4t5-y54k-8so3")
                .nroTarjeta("192455739454")
                .saldoActual(5000)
                .transes(transacciones)
                .cliente(clienteForCard)
                .build();


        List<TarjetaDTO>tarjetaListDTO=new ArrayList<>(Arrays.asList(tarjetaCliente0,tarjetaCliente1));
        List<Tarjeta>tarjetaList=new ArrayList<>(Arrays.asList((tarjetaMappers.toTarjeta(tarjetaCliente0)),(tarjetaMappers.toTarjeta(tarjetaCliente1))));
        ClienteDTO clienteDTO= ClienteDTO.builder()
                .id("ijn3-7dh3-6vn3-8en2")
                .nombre("waly")
                .pin("8774")
                . tarjetas(tarjetaList).build();

        ClienteDTO cliente2= ClienteDTO.builder()
                .id("iER3-7dh3-6vn3-8en2")
                .nombre("nano")
                .pin("7421")
                . tarjetas(tarjetaList).build();

        //List<ClienteDTO>listClientDTO=new ArrayList<>(Arrays.asList(clienteDTO,cliente2));

        Cliente clientePrueba=mapper.toCliente(clienteDTO);



        assertAll(
                ()->{
                    assertEquals(clienteDTO.getId(),clientePrueba.getId());
                    assertEquals(clienteDTO.getTarjetas().get(0).getNroTarjeta(),
                            clientePrueba.getTarjetas().get(0).getNroTarjeta());
                    assertEquals(2,clienteDTO.getTarjetas().size());

                }
        );
    }
    @Test
    void  toClienteNull(){
        ClienteDTO dto=null;
        Cliente cliente=mapper.toCliente(dto);
        assertEquals(null,cliente);
    }


}