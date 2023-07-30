package br.com.Desafio.Tech.service;

import br.com.Desafio.Tech.domain.Cliente;
import br.com.Desafio.Tech.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente){
        return  clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }
    public void deletar(Long id){
        clienteRepository.deleteById(id);
    }
    public Cliente atualizar(Long id, Cliente cliente){
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }
}
