package mappers.test;

import dto.TransDTO;
import entities.Cliente;
import entities.Tarjeta;
import entities.Trans;
import mappers.TransMappers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransMappersTest {

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
        System.out.println(trans);
        System.out.println(dto);
        System.out.println(list.size());
        assertAll(
                ()->{
                    assertEquals(trans.getId(),dto.getId());
                    assertEquals(trans.getImporte(),dto.getImporte());
                    assertEquals(list.size(),2);
                }
        );


    }

}

