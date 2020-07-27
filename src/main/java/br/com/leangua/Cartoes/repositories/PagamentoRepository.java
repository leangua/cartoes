package br.com.leangua.Cartoes.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.leangua.Cartoes.models.Pagamento;

public interface PagamentoRepository extends CrudRepository<Pagamento, Integer> {
	Iterable<Pagamento> findAllByCartao_id(int numero);
}
