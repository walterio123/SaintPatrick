package com.ideas.saintpatricks.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ideas.saintpatricks.dto.ClienteDTO;
import com.ideas.saintpatricks.entities.Cliente;
import com.ideas.saintpatricks.entities.Tarjeta;
import com.ideas.saintpatricks.mappers.ClienteMappers;
import com.ideas.saintpatricks.mappers.ClienteMappersImpl;
import com.ideas.saintpatricks.mappers.TransMappers;
import com.ideas.saintpatricks.mappers.TransMappersImpl;
import com.ideas.saintpatricks.services.ClienteService;
import com.ideas.saintpatricks.services.TransServices;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    //mapeador pora los objetos Json
    ObjectMapper objectMapper=new ObjectMapper();
    //un escritor para los Json
    ObjectWriter objectWritter=objectMapper.writer();
    @MockBean
    private ClienteService clienteService;
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private ClienteController clienteController;
    @Spy
    private final ClienteMappers clienteMappers=new ClienteMappersImpl();

    @Test
    void shouldCreateMockMvc(){
        assertNotNull(mockMvc);
    }
    @Test
    void save() throws Exception {
        List<Tarjeta> tarjetaList=new ArrayList<>();

        Cliente cliente= Cliente.builder()
                .id("ijn3-7dh3-6vn3-8en2")
                .nombre("waly")
                .pin("8774")
                . tarjetas(tarjetaList).build();

        ClienteDTO dto=clienteMappers.toClienteDTO(cliente);
        Mockito.when(clienteService.save(dto)).thenReturn(dto);
        //Then
        //primero convertimos a string el json creado
        String content = objectWritter.writeValueAsString(dto);
        //creando una solicitud servlet para el guardado
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        //entorno de prueba y resultado
        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());

    }

    @Test
    void findAll() throws Exception {
        List<Tarjeta> tarjetaList=new ArrayList<>();

        Cliente cliente= Cliente.builder()
                .id("ijn3-7dh3-6vn3-8en2")
                .nombre("waly")
                .pin("8774")
                . tarjetas(tarjetaList).build();
        ClienteDTO clienteD=clienteMappers.toClienteDTO(cliente);
        List<ClienteDTO>clienteDTOList=new ArrayList<>(Arrays.asList(clienteD));

        //when
        Mockito.when(clienteService.findAll()).thenReturn(clienteDTOList);
        //creando una solicitud servlet para el guardado
        mockMvc.perform(MockMvcRequestBuilders.get("/cliente")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("ijn3-7dh3-6vn3-8en2")));

    }

    @Test
    void findById() throws Exception {
        List<Tarjeta> tarjetaList=new ArrayList<>();

        Cliente cliente= Cliente.builder()
                .id("ijn3-7dh3-6vn3-8en2")
                .nombre("waly")
                .pin("8774")
                . tarjetas(tarjetaList).build();
        ClienteDTO clienteD=clienteMappers.toClienteDTO(cliente);
        Mockito.when(clienteService.findById("ijn3-7dh3-6vn3-8en2")).thenReturn(clienteD);

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/ijn3-7dh3-6vn3-8en2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void findByIdNotFound() throws Exception {
        List<Tarjeta> tarjetaList=new ArrayList<>();

        Cliente cliente= Cliente.builder()
                .id("ijn3-7Th3-6vn3-8en2")
                .nombre("waly")
                .pin("8774")
                . tarjetas(tarjetaList).build();
        ClienteDTO clienteD=clienteMappers.toClienteDTO(cliente);
        Mockito.when(clienteService.findById("ijn3-7Th3-6vn3-8en2")).thenReturn(clienteD);

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/ijn3-7hh3-6vn3-8en2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}