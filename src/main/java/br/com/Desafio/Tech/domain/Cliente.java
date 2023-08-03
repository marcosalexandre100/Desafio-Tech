package br.com.Desafio.Tech.domain;

import br.com.Desafio.Tech.controller.clienteRequest.ClienteRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String logradouro;

    private String cidade;


    public Cliente(ClienteRequest clienteRequest){
       this.nome = clienteRequest.getNome();
       this.telefone = clienteRequest.getTelefone();
       this.email = clienteRequest.getEmail();
       this.logradouro = clienteRequest.getLogradouro();
       this.cidade = clienteRequest.getCidade();

    }


}
