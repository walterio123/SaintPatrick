package com.ideas.saintpatricks.mappers;

import com.ideas.saintpatricks.dto.TransDTO;
import com.ideas.saintpatricks.entities.Cliente;
import com.ideas.saintpatricks.entities.Tarjeta;
import com.ideas.saintpatricks.entities.Trans;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TransMappersTest {
    private static TransMappers mapper;

    @BeforeAll
    public static void setup(){
        mapper= Mappers.getMapper(TransMappers.class);
    }
    @Test
    public void transMappersSimpleTest(){
        List<Trans>transactions=new ArrayList<>();
        Cliente clienteForTrans=new Cliente();

        Trans trans= Trans.builder()
                .id("8293-3289-isdf2-238t")
                .fecha(new Date())
                .importe(1000)
                .tarjeta(Tarjeta.builder()
                        .id("ioT3-pYp2-oidn3-iT2o")
                        .nroTarjeta("8623134000")
                        .saldoActual(50000)
                        .transes(transactions)
                        .cliente(clienteForTrans)
                        .build())
                .build();

        Trans trans1= Trans.builder()
                .id("8293-3289-isdf2-238t")
                .fecha(new Date())
                .importe(1000)
                .tarjeta(Tarjeta.builder()
                        .id("ioT3-pYp2-oidn3-iT2o")
                        .nroTarjeta("8623134000")
                        .saldoActual(50000)
                        .transes(transactions)
                        .cliente(clienteForTrans)
                        .build())
                .build();

        List<Trans>list=new ArrayList<>(Arrays.asList(trans,trans1));

        TransDTO dto=mapper.toTransDto(trans);
        assertAll(
                ()->{
                    assertEquals(trans.getId(),dto.getId());
                    assertEquals(trans.getImporte(),dto.getImporte());
                    assertEquals(list.size(),2);
                }
        );


    }
    @Test
   void toTrans(){
        List<Trans>transactions=new ArrayList<>();
        Cliente clienteForTrans=new Cliente();

        TransDTO transDTO= TransDTO.builder()
                .id("8293-3289-isdf2-238t")
                .fecha(new Date())
                .importe(1000)
                .tarjeta(Tarjeta.builder()
                        .id("ioT3-pYp2-oidn3-iT2o")
                        .nroTarjeta("8623134000")
                        .saldoActual(50000)
                        .transes(transactions)
                        .cliente(clienteForTrans)
                        .build())
                .build();

        Trans transTest=mapper.toTrans(transDTO);
        assertAll(
                ()->{
                    assertEquals(transTest.getId(),transDTO.getId());
                    assertEquals(transTest.getImporte(),transDTO.getImporte());

                }
        );
    }
    @Test
    void toTransNull(){
        TransDTO dto=null;
        Trans trans=mapper.toTrans(dto);
        assertEquals(null,trans);
    }
    @Test
    void toListDTO(){
        List<Trans>transactions=new ArrayList<>();
        Cliente clienteForTrans=new Cliente();

        Trans trans= Trans.builder()
                .id("8293-3289-isdf2-238t")
                .fecha(new Date())
                .importe(1000)
                .tarjeta(Tarjeta.builder()
                        .id("ioT3-pYp2-oidn3-iT2o")
                        .nroTarjeta("8623134000")
                        .saldoActual(50000)
                        .transes(transactions)
                        .cliente(clienteForTrans)
                        .build())
                .build();
    List<Trans>transList=new ArrayList<>(Arrays.asList(trans));
    List<TransDTO>dtoList=mapper.toListTransDTO(transList);

    assertEquals(dtoList.size(),transList.size());
    }
    @Test
    void toTransDTONull(){
        Trans trans=null;
        TransDTO dto=mapper.toTransDto(trans);
        assertEquals(null,dto);
    }
}