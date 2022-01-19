package com.ideas.saintpatricks.Controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ideas.saintpatricks.dto.TarjetaDTO;
import com.ideas.saintpatricks.entities.Cliente;
import com.ideas.saintpatricks.entities.Tarjeta;
import com.ideas.saintpatricks.mappers.TarjetaMappers;
import com.ideas.saintpatricks.mappers.TarjetaMappersImpl;
import com.ideas.saintpatricks.services.TarjetaService;
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

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TarjetaController.class)
class TarjetaControllerTest {

    //mapeador pora los objetos Json
    ObjectMapper objectMapper = new ObjectMapper();
    //un escritor para los Json
    ObjectWriter objectWritter = objectMapper.writer();
    @MockBean
    private TarjetaService tarjetaService;
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private TarjetaController tarjetaController;
    @Spy
    private final TarjetaMappers tarjetaMappers = new TarjetaMappersImpl();

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }


    @Test
    void save() throws Exception {
        List<Tarjeta> tarjetasList = new ArrayList<>();
        Tarjeta tarjeta = (Tarjeta.builder()
                .id("t64L-34ls-Y71m-0Fwm")
                .nroTarjeta("9823824")
                .saldoActual(20000)
                .cliente(Cliente.builder()
                        .id("0934934")
                        .nombre("cliente")
                        .pin("7373")
                        .tarjetas(tarjetasList)
                        .build())
                .build());
        TarjetaDTO dto = tarjetaMappers.ToTarjetaDTO(tarjeta);
        Mockito.when(tarjetaService.save(dto)).thenReturn(dto);
        //Then
        //primero convertimos a string el json creado
        String content = objectWritter.writeValueAsString(dto);
        //creando una solicitud servlet para el guardado
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/tarjeta")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        //entorno de prueba y resultado
        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());
    }

    @Test
    void findAll() throws Exception {
        List<Tarjeta> tarjetasList = new ArrayList<>();
        Tarjeta tarjeta = (Tarjeta.builder()
                .id("t64L-34ls-Y71m-0Fwm")
                .nroTarjeta("9823824")
                .saldoActual(20000)
                .cliente(Cliente.builder()
                        .id("0934934")
                        .nombre("cliente")
                        .pin("7373")
                        .tarjetas(tarjetasList)
                        .build())
                .build());
        List<Tarjeta> tarjetaListSave = new ArrayList<>(Arrays.asList(tarjeta));
        List<TarjetaDTO> tarjetaDTOList = tarjetaMappers.toListTarjetaDTO(tarjetaListSave);
        //When //Then
        Mockito.when(tarjetaService.findAll()).thenReturn(tarjetaDTOList);

        //creando una solicitud servlet para el guardado
        mockMvc.perform(MockMvcRequestBuilders.get("/tarjeta")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("t64L-34ls-Y71m-0Fwm")));

    }

    @Test
    void findById() throws Exception {
        List<Tarjeta> tarjetasList = new ArrayList<>();
        Tarjeta tarjeta = (Tarjeta.builder()
                .id("10")
                .nroTarjeta("9823824")
                .saldoActual(20000)
                .cliente(Cliente.builder()
                        .id("0934934")
                        .nombre("cliente")
                        .pin("7373")
                        .tarjetas(tarjetasList)
                        .build())
                .build());
        //When //Then
        TarjetaDTO dto = tarjetaMappers.ToTarjetaDTO(tarjeta);
        Mockito.when(tarjetaService.findById("10")).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/tarjeta/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("10")))
                .andExpect(jsonPath("$.nroTarjeta", is("9823824")));
    }

    @Test
    void findByIdNotFound() throws Exception {
        List<Tarjeta> tarjetasList = new ArrayList<>();
        Tarjeta tarjeta = (Tarjeta.builder()
                .id("10")
                .nroTarjeta("9823824")
                .saldoActual(20000)
                .cliente(Cliente.builder()
                        .id("0934934")
                        .nombre("cliente")
                        .pin("7373")
                        .tarjetas(tarjetasList)
                        .build())
                .build());
        //When //Then
        TarjetaDTO dto = tarjetaMappers.ToTarjetaDTO(tarjeta);
        Mockito.when(tarjetaService.findById("10")).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/tarjeta/11")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
}