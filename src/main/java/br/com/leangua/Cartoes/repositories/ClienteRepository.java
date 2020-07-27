package br.com.leangua.Cartoes.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.leangua.Cartoes.models.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

}
