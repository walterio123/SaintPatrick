package com.ideas.saintpatricks.services;


import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.ideas.saintpatricks.dto.TransDTO;
import com.ideas.saintpatricks.entities.Cliente;
import com.ideas.saintpatricks.entities.Tarjeta;
import com.ideas.saintpatricks.entities.Trans;
import com.ideas.saintpatricks.mappers.TransMappers;
import com.ideas.saintpatricks.repository.TransRepository;
import javassist.NotFoundException;
import com.ideas.saintpatricks.mappers.TransMappersImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class TransServicesTest {

    @InjectMocks
    private TransServices transServices;

    @Mock
    private TransRepository transRepository;

    @Spy
    private TransMappers transMappers=new TransMappersImpl();
    @Test
    void shouldCreateMock(){
        assertNotNull(transRepository);
    }


    @Test
    void testFindByIdTrans() throws NotFoundException {
        //given(indicando que tiene que hacer antes de probar el service save)
        Cliente clienteTT=new Cliente();
        List<Trans> listForCard=new ArrayList<>();
        Optional<Trans> trans=Optional.of(Trans.builder()
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
    void testFindByIdNotFoundTrans() throws NotFoundException {
        //given(indicando que tiene que hacer antes de probar el service save)
        Cliente clienteTT=new Cliente();
        List<Trans> listForCard=new ArrayList<>();
        Optional<Trans> trans=Optional.of(Trans.builder()
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
        //then
        assertThatThrownBy(()->transServices.findById("10"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("There is no Transaction with that identification.");
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
    @Test
    void testSaveTrans() throws NotFoundException {
        //given(indicando que tiene que hacer antes de probar el service save)
        Cliente clienteTT=new Cliente();
        List<Trans> listForCard=new ArrayList<>();
        Trans trans= (Trans.builder()
                .id("100")
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
                .id("110")
                .importe(5000)
                .fecha(new Date())
                .tarjeta(Tarjeta.builder()
                        .id("U7w4-3Tr4-t51Y-z03l-A9f8")
                        .saldoActual(6000)
                        .cliente(clienteTT)
                        .transes(listForCard)
                        .build()
                ).build());

        TransDTO dto=transMappers.toTransDto(trans);

        when(transRepository.save(any(Trans.class))).thenReturn(trans);
        //when
        TransDTO dtoSave=transServices.save(dto);
        //then
        assertEquals(dtoSave.getId(),dto.getId());

    }
}