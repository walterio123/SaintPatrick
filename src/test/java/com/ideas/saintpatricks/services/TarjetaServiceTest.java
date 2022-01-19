package com.ideas.saintpatricks.services;

import com.ideas.saintpatricks.dto.TarjetaDTO;
import com.ideas.saintpatricks.entities.Cliente;
import com.ideas.saintpatricks.entities.Tarjeta;
import com.ideas.saintpatricks.mappers.TarjetaMappers;
import com.ideas.saintpatricks.mappers.TarjetaMappersImpl;
import com.ideas.saintpatricks.repository.TarjetaRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TarjetaServiceTest {

    @InjectMocks
    private TarjetaService tarjetaServices;

    @Mock
    private TarjetaRepository tarjetaRepository;

    @Spy
    private TarjetaMappers tarjetaMappers=new TarjetaMappersImpl();
    @Test
    void shouldCreateMock(){
        assertNotNull(tarjetaRepository);
    }

    @Test
    void save() {
        List<Tarjeta> tarjetasList=new ArrayList<>();
        Tarjeta tarjeta=Tarjeta.builder()
                .id("or4m-34ls-Y71m-0Fwm")
                .nroTarjeta("9823824")
                .saldoActual(20000)
                .cliente(Cliente.builder()
                                    .id("0934934")
                                    .nombre("cliente")
                                    .pin("7373")
                                    .tarjetas(tarjetasList)
                                    .build())
                .build();
        Mockito.when(tarjetaRepository.save(any(Tarjeta.class))).thenReturn(tarjeta);
        TarjetaDTO dto=tarjetaMappers.ToTarjetaDTO(tarjeta);
        //when
        TarjetaDTO dtoSave=tarjetaServices.save(dto);
        //then
        assertEquals(dtoSave.getId(),dto.getId());
    }


    @Test
    void findAll() {
        List<Tarjeta> tarjetasList=new ArrayList<>();
        Tarjeta tarjeta=Tarjeta.builder()
                .id("or4m-34ls-Y71m-0Fwm")
                .nroTarjeta("9823824")
                .saldoActual(20000)
                .cliente(Cliente.builder()
                        .id("0934934")
                        .nombre("cliente")
                        .pin("7373")
                        .tarjetas(tarjetasList)
                        .build())
                .build();
        Tarjeta tarjeta1=Tarjeta.builder()
                .id("or5m-34ls-Y71m-0Fwm")
                .nroTarjeta("59823824")
                .saldoActual(250000)
                .cliente(Cliente.builder()
                        .id("05934934")
                        .nombre("cliente")
                        .pin("7375")
                        .tarjetas(tarjetasList)
                        .build())
                .build();
        List<Tarjeta>tarjetaListFinAll=new ArrayList<>(Arrays.asList(tarjeta,tarjeta1));
        //then
        Mockito.when(tarjetaRepository.findAll()).thenReturn(tarjetaListFinAll);
        //when
        List<TarjetaDTO>tarjetaDTOList=(tarjetaServices.findAll());
        //
        assertAll(
                ()->{
                    assertEquals(2,tarjetaDTOList.size());
                    assertEquals("or4m-34ls-Y71m-0Fwm",tarjetaDTOList.get(0).getId());
                    assertEquals("0934934",tarjetaDTOList.get(0).getCliente().getId());
                    assertEquals("or5m-34ls-Y71m-0Fwm",tarjetaDTOList.get(1).getId());
                }
        );
    }

    @Test
    void findById() throws NotFoundException {
        List<Tarjeta> tarjetasList=new ArrayList<>();
        Optional<Tarjeta> tarjeta=Optional.of(Tarjeta.builder()
                .id("or4L-34ls-Y71m-0Fwm")
                .nroTarjeta("9823824")
                .saldoActual(20000)
                .cliente(Cliente.builder()
                        .id("0934934")
                        .nombre("cliente")
                        .pin("7373")
                        .tarjetas(tarjetasList)
                        .build())
                .build());
        //
        Mockito.when(tarjetaRepository.findById("or4L-34ls-Y71m-0Fwm")).thenReturn(tarjeta);
        TarjetaDTO dto=(tarjetaServices.findById("or4L-34ls-Y71m-0Fwm"));


        verify(tarjetaRepository).findById("or4L-34ls-Y71m-0Fwm");
        assertAll(
                ()->{
                    assertEquals("or4L-34ls-Y71m-0Fwm",dto.getId());
                    assertEquals("0934934",dto.getCliente().getId());
                }
        );
    }
    @Test
    void findByIdNotFounException()  {
        List<Tarjeta> tarjetasList=new ArrayList<>();
        Optional<Tarjeta> tarjeta=Optional.of(Tarjeta.builder()
                .id("or4L-34ls-Y71m-0Fwm")
                .nroTarjeta("9823824")
                .saldoActual(20000)
                .cliente(Cliente.builder()
                        .id("0934934")
                        .nombre("cliente")
                        .pin("7373")
                        .tarjetas(tarjetasList)
                        .build())
                .build());
        //
        assertThatThrownBy(()->tarjetaServices.findById("or4L-34ls-Y71m-0Fwm"))
                                    .isInstanceOf(NotFoundException.class)
                                    .hasMessageContaining("Not card with: "+tarjeta.get().getId()+" id.");

    }
}