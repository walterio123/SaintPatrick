package com.ideas.saintpatricks.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ideas.saintpatricks.dto.TransDTO;
import com.ideas.saintpatricks.entities.Cliente;
import com.ideas.saintpatricks.entities.Tarjeta;
import com.ideas.saintpatricks.entities.Trans;
import com.ideas.saintpatricks.mappers.TransMappers;
import com.ideas.saintpatricks.mappers.TransMappersImpl;
import com.ideas.saintpatricks.services.TransServices;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;



import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransController.class)
class TransControllerTest {

    //mapeador pora los objetos Json
    ObjectMapper objectMapper=new ObjectMapper();
    //un escritor para los Json
    ObjectWriter objectWritter=objectMapper.writer();
    @MockBean
    private TransServices transServices;
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private TransController transController;
    @Spy
    private final TransMappers transMappers=new TransMappersImpl();

    @Test
            void shouldCreateMockMvc(){
        assertNotNull(mockMvc);
    }
    @Test
    void listOfTrans() throws Exception {
        List<Trans> transactions=new ArrayList<>();
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

        Tarjeta tarjeta= Tarjeta.builder()
                .id("poe3-o455-y54k-8so3")
                .nroTarjeta("983455739004")
                .saldoActual(1000)
                .transes(list)
                .build();

        Tarjeta tarjeta1= Tarjeta.builder()
                .id("ioT3-pYp2-odfn3-i32o")
                .nroTarjeta("983455739454")
                .saldoActual(1000)
                .transes(list)
                .build();

        List<Tarjeta>tarjetaList=new ArrayList<>(Arrays.asList(tarjeta,tarjeta1));


        Cliente clienteTT=Cliente.builder()
                .id("etevc")
                .nombre("Jhon")
                .pin("9432")
                .tarjetas(tarjetaList)
                .build();

        TransDTO mockTrans= (TransDTO.builder()
                .id("10")
                .fecha(new Date())
                .importe(10000)
                .tarjeta(Tarjeta.builder()
                        .id("oi23-31k3-osp4-239r")
                        .nroTarjeta("98734298723")
                        .saldoActual(500000)
                        .cliente(clienteTT).build())
                .build());

        List<TransDTO>transDTOList=new ArrayList<>(Arrays.asList(mockTrans));
        System.out.println(transDTOList);
        when(transServices.findAll()).thenReturn(transDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/trans")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    void findByIdTrans() throws Exception {
        List<Trans> transactions=new ArrayList<>();
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

        Tarjeta tarjeta= Tarjeta.builder()
                .id("poe3-o455-y54k-8so3")
                .nroTarjeta("983455739004")
                .saldoActual(1000)
                .transes(list)
                .build();

        Tarjeta tarjeta1= Tarjeta.builder()
                .id("ioT3-pYp2-odfn3-i32o")
                .nroTarjeta("983455739454")
                .saldoActual(1000)
                .transes(list)
                .build();

        List<Tarjeta>tarjetaList=new ArrayList<>(Arrays.asList(tarjeta,tarjeta1));


        Cliente clienteTT=Cliente.builder()
                .id("etevc")
                .nombre("Jhon")
                .pin("9432")
                .tarjetas(tarjetaList)
                .build();

        TransDTO mockTrans= (TransDTO.builder()
                .id("10")
                .fecha(new Date())
                .importe(10000)
                .tarjeta(Tarjeta.builder()
                        .id("oi23-31k3-osp4-239r")
                        .nroTarjeta("98734298723")
                        .saldoActual(500000)
                        .cliente(clienteTT).build())
                .build());

        when(transServices.findById("10")).thenReturn(mockTrans);

        mockMvc.perform(MockMvcRequestBuilders.get("/trans/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }
    @Test
    void findByIdTransNotFound() throws Exception {
        List<Trans> transactions=new ArrayList<>();
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

        Tarjeta tarjeta= Tarjeta.builder()
                .id("poe3-o455-y54k-8so3")
                .nroTarjeta("983455739004")
                .saldoActual(1000)
                .transes(list)
                .build();

        Tarjeta tarjeta1= Tarjeta.builder()
                .id("ioT3-pYp2-odfn3-i32o")
                .nroTarjeta("983455739454")
                .saldoActual(1000)
                .transes(list)
                .build();

        List<Tarjeta>tarjetaList=new ArrayList<>(Arrays.asList(tarjeta,tarjeta1));


        Cliente clienteTT=Cliente.builder()
                .id("etevc")
                .nombre("Jhon")
                .pin("9432")
                .tarjetas(tarjetaList)
                .build();

        TransDTO mockTrans= (TransDTO.builder()
                .id("10")
                .fecha(new Date())
                .importe(10000)
                .tarjeta(Tarjeta.builder()
                        .id("oi23-31k3-osp4-239r")
                        .nroTarjeta("98734298723")
                        .saldoActual(500000)
                        .cliente(clienteTT).build())
                .build());

        when(transServices.findById("10")).thenReturn(mockTrans);

        mockMvc.perform(MockMvcRequestBuilders.get("/trans/11")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());


    }
    @Test
    void saveTrans() throws Exception {
        List<Trans> transactions=new ArrayList<>();
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

        Tarjeta tarjeta= Tarjeta.builder()
                .id("poe3-o455-y54k-8so3")
                .nroTarjeta("983455739004")
                .saldoActual(1000)
                .transes(list)
                .build();

        Tarjeta tarjeta1= Tarjeta.builder()
                .id("ioT3-pYp2-odfn3-i32o")
                .nroTarjeta("983455739454")
                .saldoActual(1000)
                .transes(list)
                .build();

        List<Tarjeta>tarjetaList=new ArrayList<>(Arrays.asList(tarjeta,tarjeta1));


        Cliente clienteTT=Cliente.builder()
                .id("etevc")
                .nombre("Jhon")
                .pin("9432")
                .tarjetas(tarjetaList)
                .build();

        TransDTO mockTrans= (TransDTO.builder()
                .id("10")
                .fecha(new Date())
                .importe(10000)
                .tarjeta(Tarjeta.builder()
                        .id("oi23-31k3-osp4-239r")
                        .nroTarjeta("98734298723")
                        .saldoActual(500000)
                        .cliente(clienteTT).build())
                .build());
        //When
        when(transServices.save(mockTrans)).thenReturn(mockTrans);
        //Then
        //primero convertimos a string el json creado
        String content=objectWritter.writeValueAsString(mockTrans);
        //creando una solicitud servlet para el guardado
        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.post("/trans")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        //entorno de prueba y resultado
        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());

    }
    }
