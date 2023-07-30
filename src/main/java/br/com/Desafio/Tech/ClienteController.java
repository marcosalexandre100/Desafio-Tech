package br.com.Desafio.Tech;

import br.com.Desafio.Tech.domain.Cliente;
import br.com.Desafio.Tech.repository.ClienteRepository;
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
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente){
        Cliente clientes = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientes);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos(){
        List<Cliente> clientes = clienteRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }
    @PutMapping()
    public ResponseEntity<Cliente> alterar( @RequestBody Cliente cliente){
        Cliente clientes = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id){
       Optional<Cliente> cliente = clienteRepository.findById(id);
       if(cliente.isPresent()){
           clienteRepository.deleteById(id);
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.notFound().build();
    }
}
