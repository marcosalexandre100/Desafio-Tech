package br.com.Desafio.Tech.controller.clienteRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClienteRequest {


    private String nome;

    private String email;

    private String telefone;

    private String logradouro;

    private String cidade;


}
