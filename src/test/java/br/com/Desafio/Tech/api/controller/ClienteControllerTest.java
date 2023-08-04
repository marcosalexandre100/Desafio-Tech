package br.com.Desafio.Tech.api.controller;

import br.com.Desafio.Tech.controller.clienteRequest.ClienteRequest;
import br.com.Desafio.Tech.controller.clienteResponse.ClienteResponse;
import br.com.Desafio.Tech.domain.Cliente;
import br.com.Desafio.Tech.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<ClienteRequest> clienteRequestJacksonTester;
    @Autowired
    private JacksonTester<ClienteResponse> clienteResponseJacksonTester;
    @MockBean
    private ClienteService clienteService;
    private ClienteRequest request;
    private ClienteResponse responses;

    @BeforeEach
    void init(){
        request = new ClienteRequest("Marcos", "marcos@gmail.com",
                "58248689", "Rua matupa 19", "SP");

        responses = new ClienteResponse(null, request.getNome(), request.getEmail(), request.getTelefone(),
                request.getLogradouro(), request.getCidade());
    }

    @Test
    @DisplayName("Deve devolver codigo http 200 quando criar um cliente")
    void cenario_01() throws Exception {
        when(clienteService.salvar(any())).thenReturn(new Cliente(request));

        var response = mockMvc.perform(post("/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteRequestJacksonTester.write(request).getJson())).andReturn().getResponse();

        var jsonEsperado = clienteResponseJacksonTester.write(responses).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }

    @Test
    @DisplayName("Deve devolver codigo http 400 quando as informacoes estao invalidas")
    void cenario_02() throws Exception{
        var response = mockMvc.perform(post("/cliente"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve devolver o codigo http 200 quando listar  cliente")
    void cenario_03() throws Exception{
        List<Cliente> list = new ArrayList<>();

        when(clienteService.listarTodos()).thenReturn(list);
        var response = mockMvc.perform(get("/cliente"))
               .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    @DisplayName("Deve devolver o codigo http 204 ao excluir cliente")
    void cenario_04() throws Exception {
       doNothing().when(clienteService).deletar(anyLong());

        var response = mockMvc.perform(delete("/cliente/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("Deve retornar o codigo http 201 ao atualizar")
    void cenario_05() throws Exception{
        when(clienteService.atualizar(any(),anyLong())).thenReturn(new Cliente(request));

       var response=  this.mockMvc.perform(put("/cliente/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(clienteRequestJacksonTester.write(request).getJson())).andReturn().getResponse();

        var jsonEsperado = clienteResponseJacksonTester.write(responses).getJson();

       assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
       assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }


}
