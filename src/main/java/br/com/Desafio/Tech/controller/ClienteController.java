package br.com.Desafio.Tech.controller;

import br.com.Desafio.Tech.controller.clienteRequest.ClienteRequest;
import br.com.Desafio.Tech.controller.clienteResponse.ClienteResponse;
import br.com.Desafio.Tech.domain.Cliente;
import br.com.Desafio.Tech.mapper.ClienteMapper;
import br.com.Desafio.Tech.repository.ClienteRepository;
import br.com.Desafio.Tech.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {


    private final ClienteMapper mapper;

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponse> salvar(@RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = clienteService.salvar(mapper.toCliente(clienteRequest));
        ClienteResponse clienteResponse = mapper.toClienteResponse(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponse);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listarTodos(){
        List<Cliente> cliente = clienteService.listarTodos();
        List<ClienteResponse> clienteResponse = mapper.toListClienteResponse(cliente);
        return ResponseEntity.status(HttpStatus.OK).body(clienteResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizar(@PathVariable Long id, @RequestBody ClienteRequest clienteRequest){
        Cliente cliente = clienteService.atualizar(id, mapper.toCliente(clienteRequest));
        ClienteResponse clienteResponse = mapper.toClienteResponse(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id){
     clienteService.deletar(id);
     return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
