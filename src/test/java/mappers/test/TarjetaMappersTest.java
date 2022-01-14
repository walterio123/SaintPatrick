package mappers.test;

import dto.TarjetaDTO;
import entities.Tarjeta;
import entities.Trans;
import mappers.TarjetaMappers;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TarjetaMappersTest {
    private final TarjetaMappers mapper= Mappers.getMapper(TarjetaMappers.class);

    private List<Trans>ListTrans;
    Trans trans;
    Trans trans1;
    Tarjeta tarjeta;
    @Before("startup")
    public void init() {

        trans=new Trans();
        trans.setId("8293-3289-isdf2-238t");
        trans.setFecha(new Date());
        trans.setImporte(1000);
        tarjeta=new Tarjeta();
        tarjeta.setId("ioT3-pYp2-odfn3-i32o");
        tarjeta.setNroTarjeta("8623134225");
        tarjeta.setSaldoActual(10000);
        tarjeta.setTranses(ListTrans);

        trans.setTarjeta(tarjeta);

        trans1=new Trans();
        trans1.setId("8293-3289-isdf2-238t");
        trans1.setFecha(new Date());
        trans1.setImporte(1000);
        Tarjeta tarjeta1=new Tarjeta();

        trans.setTarjeta(tarjeta1);

        ListTrans = new ArrayList<>(Arrays.asList(trans,trans1));
    }


    @Test
    public void tarjetaMapperTestSimple(){

        Tarjeta tarjeta1=new Tarjeta();
        tarjeta1.setId("ioT3-pYp2-odfn3-i32o");
        tarjeta1.setNroTarjeta("8623134225");
        tarjeta1.setSaldoActual(10000);
        tarjeta1.setTranses(ListTrans);

        List<Tarjeta>tarjetaList=new ArrayList<>(Arrays.asList(tarjeta,tarjeta1));
        List<TarjetaDTO>tarjetaDTOList=mapper.toListTarjetaDTO(tarjetaList);
       TarjetaDTO dto1= mapper.ToTarjetaDTO(tarjeta1);
       System.out.println(tarjetaDTOList.size());
       System.out.println(tarjeta1.getTranses());
        assertEquals(tarjeta1.getId(),dto1.getId());
        assertEquals(2,tarjetaDTOList.size());
        assertEquals(tarjetaList.get(1).getId(),tarjetaDTOList.get(1).getId());
    }
}
