package br.com.Desafio.Tech.mapper;

import br.com.Desafio.Tech.controller.clienteRequest.ClienteRequest;
import br.com.Desafio.Tech.controller.clienteResponse.ClienteResponse;
import br.com.Desafio.Tech.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClienteMapper {


    private final ModelMapper mapper;

    public Cliente toCliente(ClienteRequest clienteRequest){
        return  mapper.map(clienteRequest, Cliente.class);
    }

    public ClienteResponse toClienteResponse(Cliente cliente){
        return mapper.map(cliente, ClienteResponse.class);
    }
    public List<ClienteResponse> toListClienteResponse(List<Cliente> cliente){
        return cliente.stream()
                .map(this::toClienteResponse)
                .collect(Collectors.toList());
    }
}
