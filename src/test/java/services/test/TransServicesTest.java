package services.test;

import dto.TransDTO;
import entities.Cliente;
import entities.Tarjeta;
import entities.Trans;
import javassist.NotFoundException;
import mappers.TransMappers;
import mappers.TransMappersImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.TransRepository;
import services.TransServices;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class TransServicesTest {

    @InjectMocks
    private TransServices transServices;

    @Mock
    private TransRepository transRepository;

    @Spy
    private TransMappers transMappers=new TransMappersImpl();

    @Test
    void testFindByIdTrans() throws NotFoundException {
        //given(indicando que tiene que hacer antes de probar el service save)
        Cliente clienteTT=new Cliente();
        List<Trans> listForCard=new ArrayList<>();
        Optional<Trans> trans= Optional.of(Trans.builder()
                .id("10")
                .importe(1000)
                .fecha(new Date())
                .tarjeta(Tarjeta.builder()
                        .id("oiw4-3wer4-t51Y-z03l-A9f8")
                        .saldoActual(1000)
                        .cliente(clienteTT)
                        .transes(listForCard)
                        .build()
                ).build());
    //que hace cuando busca en el repo La Trans con el id 10?retorna trans
        Mockito.when(transRepository.findById("10")).thenReturn(trans);

        //when

        TransDTO dto= transServices.findById("10");


        //then
        verify(transRepository).findById("10");
      assertAll(
              ()->{
                  assertEquals("10",dto.getId());
                  assertEquals("oiw4-3wer4-t51Y-z03l-A9f8",dto.getTarjeta().getId());
              }
      );


    }

    @Test
    void testFindAllTrans() throws NotFoundException {
        //given(indicando que tiene que hacer antes de probar el service save)
        Cliente clienteTT=new Cliente();
        List<Trans> listForCard=new ArrayList<>();
        Trans trans= (Trans.builder()
                                        .id("10")
                                        .importe(1000)
                                        .fecha(new Date())
                                        .tarjeta(Tarjeta.builder()
                                                .id("oiw4-3wer4-t51Y-z03l-A9f8")
                                                .saldoActual(1000)
                                                .cliente(clienteTT)
                                                .transes(listForCard)
                                                .build()
                                        ).build());

        Trans trans1= (Trans.builder()
                                        .id("11")
                                        .importe(5000)
                                        .fecha(new Date())
                                        .tarjeta(Tarjeta.builder()
                                                .id("U7w4-3Tr4-t51Y-z03l-A9f8")
                                                .saldoActual(6000)
                                                .cliente(clienteTT)
                                                .transes(listForCard)
                                                .build()
                                        ).build());
        List<Trans>transList=new ArrayList<>(Arrays.asList(trans,trans1));

        //
        Mockito.when(transRepository.findAll()).thenReturn(transList);

        //when

        List<TransDTO> dtos= transServices.findAll();


        //then
        verify(transRepository).findAll();
        assertAll(
                ()->{
                    assertEquals(transList.size(),dtos.size());
                    assertEquals("11",dtos.get(1).getId());
                    assertEquals("10",dtos.get(0).getId());
                }
        );


    }
//TODO Verificar stubbing
    //@Test
    void testSaveTrans()  {
        //given(indicando que tiene que hacer antes de probar el service save)
        List<Tarjeta>tarjetaList=new ArrayList<>();
        Cliente clienteTT=Cliente.builder()
                                            .id("etevc")
                                            .nombre("Jhon")
                                            .pin("9432")
                                            .tarjetas(tarjetaList)
                                            .build();
        List<Trans> listForCard=new ArrayList<>();
        Trans trans= (Trans.builder()
                .id("10")
                .importe(1000)
                .fecha(new Date())
                .tarjeta(Tarjeta.builder()
                        .id("oiw4-3wer4-t51Y-z03l-A9f8")
                        .saldoActual(1000)
                        .cliente(clienteTT)
                        .transes(listForCard)
                        .build()
                ).build());
        //que hace cuando busca en el repo La Trans con el id 10?retorna trans
        Mockito.when(transRepository.save(trans)).thenReturn(trans);

        TransDTO transSaved=transMappers.toTransDto(trans);
        //when

        TransDTO dto= transServices.save((transSaved));


        //then
        verify(transRepository).save(trans);
        assertAll(
                ()->{
                    //assertEquals("10",dto.getId());
                    //assertEquals("oiw4-3wer4-t51Y-z03l-A9f8",dto.getTarjeta().getId());
                }
        );


    }

}
