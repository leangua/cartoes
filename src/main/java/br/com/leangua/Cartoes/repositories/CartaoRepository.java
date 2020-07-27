package br.com.leangua.Cartoes.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.leangua.Cartoes.models.Cartao;

public interface CartaoRepository extends CrudRepository<Cartao, Integer> {
	Optional<Cartao> findByNumero(String numero);
	
}
