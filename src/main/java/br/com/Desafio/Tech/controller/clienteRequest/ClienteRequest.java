package br.com.Desafio.Tech.controller.clienteRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {



    private String nome;

    private String cpf;

    private String email;

    private int telefone;

    private String logradouro;

    private String cidade;

    private String uf;
}
