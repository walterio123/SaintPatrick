package mappers.test;

import dto.TarjetaDTO;
import entities.Tarjeta;
import entities.Trans;
import mappers.TarjetaMappers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TarjetaMappersTest {

    private static TarjetaMappers mapper;



    @BeforeAll
    public static void setup() {
        mapper= Mappers.getMapper(TarjetaMappers.class);
    }


    @Test
    public void tarjetaMapperTestSimple(){
         List<Trans>ListTrans=new ArrayList<>();

        Trans trans= Trans.builder()
                            .id("8293-3289-isdf2-238t")
                            .fecha(new Date())
                            .importe(1000)
                            .tarjeta(Tarjeta.builder()
                                    .id("ioT3-pYp2-odfn3-i32o")
                                    .nroTarjeta("8623134225")
                                    .saldoActual(10000)
                                    .transes(ListTrans).build())
                .build();

        Trans trans1= Trans.builder()
                .id("8293-3289-isdf2-238t")
                .fecha(new Date())
                .importe(1000)
                .tarjeta(Tarjeta.builder()
                        .id("ioT3-pYp2-oidn3-iT2o")
                        .nroTarjeta("8623134000")
                        .saldoActual(50000)
                        .transes(ListTrans).build())
                .build();

        ListTrans = new ArrayList<>(Arrays.asList(trans,trans1));

        Tarjeta tarjeta= Tarjeta.builder()
                                    .id("poe3-o455-y54k-8so3")
                                    .nroTarjeta("983455739004")
                                    .saldoActual(1000)
                                    .transes(ListTrans)
                                    .build();

        Tarjeta tarjeta1= Tarjeta.builder()
                                    .id("ioT3-pYp2-odfn3-i32o")
                                    .nroTarjeta("983455739454")
                                    .saldoActual(1000)
                                    .transes(ListTrans)
                                    .build();


        List<Tarjeta>tarjetaList=new ArrayList<>(Arrays.asList(tarjeta,tarjeta1));
        List<TarjetaDTO>tarjetaDTOList=mapper.toListTarjetaDTO(tarjetaList);
       TarjetaDTO dto1= mapper.ToTarjetaDTO(tarjeta1);
       assertAll(
               ()->{
                   assertEquals(tarjeta1.getId(),dto1.getId());
                   assertEquals(2,tarjetaDTOList.size());
                   assertEquals(tarjetaList.get(1).getId(),tarjetaDTOList.get(1).getId());
               }
       );
    }
}
