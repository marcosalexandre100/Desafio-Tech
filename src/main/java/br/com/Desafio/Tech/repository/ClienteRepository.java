package br.com.Desafio.Tech.repository;

import br.com.Desafio.Tech.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
