package com.ideas.saintpatricks.services;

import com.ideas.saintpatricks.Controllers.ClienteController;
import com.ideas.saintpatricks.dto.ClienteDTO;
import com.ideas.saintpatricks.entities.Cliente;
import com.ideas.saintpatricks.entities.Tarjeta;
import com.ideas.saintpatricks.mappers.ClienteMappers;
import com.ideas.saintpatricks.mappers.ClienteMappersImpl;

import com.ideas.saintpatricks.repository.ClienteRepository;

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
class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Spy
    private ClienteMappers clienteMappers=new ClienteMappersImpl();
    @Test
    void shouldCreateMock(){
        assertNotNull(clienteRepository);
    }

    @Test
    void save() {
        List<Tarjeta>tarjetaList=new ArrayList<>();
        Cliente cliente= Cliente.builder()
                .id("ijn3-7dh3-6vn3-8en2")
                .nombre("waly")
                .pin("8774")
                . tarjetas(tarjetaList).build();
        //when
        Mockito.when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        //then
        ClienteDTO dto=clienteMappers.toClienteDTO(clienteRepository.save(cliente));
        ClienteDTO dtoSave=clienteService.save(dto);
        assertAll(
                ()->{
                    assertEquals("ijn3-7dh3-6vn3-8en2",dtoSave.getId());
                    assertEquals("waly",dtoSave.getNombre());
                    assertEquals("8774",dtoSave.getPin());
                }
        );
    }

    @Test
    void findAll() {
        List<Tarjeta>tarjetaList=new ArrayList<>();
        Cliente cliente= Cliente.builder()
                .id("ijn3-7dh3-6vn3-8en2")
                .nombre("waly")
                .pin("8774")
                . tarjetas(tarjetaList).build();
        List<Cliente>clienteList=new ArrayList<>(Arrays.asList(cliente));
        //when
        Mockito.when(clienteRepository.findAll()).thenReturn(clienteList);
        //then
        List<ClienteDTO>clienteDTOList=(clienteService.findAll());

        assertAll(
                ()->{
                    assertEquals(1,clienteDTOList.size());
                    assertEquals("ijn3-7dh3-6vn3-8en2",clienteDTOList.get(0).getId());
                    assertEquals("waly",clienteDTOList.get(0).getNombre());
                }
        );

    }

    @Test
    void findById() throws NotFoundException {
        List<Tarjeta>tarjetaList=new ArrayList<>();
       Optional< Cliente> cliente= Optional.of(Cliente.builder()
                .id("ijn3-7dh3-6vn3-8en2")
                .nombre("waly")
                .pin("8774")
                . tarjetas(tarjetaList).build());
        //when
        Mockito.when(clienteRepository.findById("ijn3-7dh3-6vn3-8en2")).thenReturn(cliente);
        //
        ClienteDTO dto=clienteService.findById("ijn3-7dh3-6vn3-8en2");
        //then
        assertAll(
                ()->{
                    assertEquals("ijn3-7dh3-6vn3-8en2",dto.getId());
                    assertEquals("waly",dto.getNombre());
                }
        );
    }
    @Test
    void findByIdTrowNotFound() {
        List<Tarjeta>tarjetaList=new ArrayList<>();
        Optional< Cliente> cliente= Optional.of(Cliente.builder()
                .id("ijn6-7dh3-6vn3-8en2")
                .nombre("waly")
                .pin("8784")
                . tarjetas(tarjetaList).build());
        assertThatThrownBy(()->clienteService.findById("ijn6-7dh3-6vn3-8en2"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Not card with: "+cliente.get().getId()+" id.");

    }
}