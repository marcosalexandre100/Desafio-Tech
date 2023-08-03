package br.com.Desafio.Tech.controller.clienteResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String logradouro;

    private String cidade;


}
