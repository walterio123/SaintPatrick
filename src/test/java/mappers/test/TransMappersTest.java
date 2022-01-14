package mappers.test;

import dto.TransDTO;
import entities.Tarjeta;
import entities.Trans;
import mappers.TransMappers;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransMappersTest {

    private final TransMappers mapper= Mappers.getMapper(TransMappers.class);

    @Test
    public void transMappersSimpleTest(){
        List<Trans>transactions=new ArrayList<>();
        Tarjeta tarjeta=new Tarjeta();
        tarjeta.setId("pios23.234");
        tarjeta.setNroTarjeta("2342");
        tarjeta.setSaldoActual(20202);
        tarjeta.setTranses(transactions);

        Trans trans=new Trans();
        trans.setId("8293-3289-isdf2-238t");
        trans.setFecha(new Date());
        trans.setImporte(1000);
        trans.setTarjeta(tarjeta);

        Trans trans1=new Trans();
        trans.setId("8223-3289-isdf2-238t");
        trans.setFecha(new Date());
        trans.setImporte(5000);
        trans.setTarjeta(tarjeta);


        List<Trans>list=new ArrayList<>();
        list.add(trans1);
        list.add(trans);

        TransDTO dto=mapper.toTransDto(trans);
        System.out.println(trans);
        System.out.println(dto);
        System.out.println(list.size());
        assertEquals(trans.getId(),dto.getId());
        assertEquals(trans.getImporte(),dto.getImporte());
        assertEquals(list.size(),2);

    }

}

