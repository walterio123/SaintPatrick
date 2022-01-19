package com.ideas.saintpatricks.mappers;

import com.ideas.saintpatricks.dto.ClienteDTO;
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
    @BeforeAll
    public static void setUp(){
        mapper= Mappers.getMapper(ClienteMappers.class);
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
}