package br.com.leangua.Cartoes.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leangua.Cartao.dto.PagamentoDto;
import br.com.leangua.Cartoes.exceptions.ValidacaoException;
import br.com.leangua.Cartoes.models.Cartao;
import br.com.leangua.Cartoes.models.Pagamento;
import br.com.leangua.Cartoes.repositories.PagamentoRepository;

@Service
public class PagamentoService {
	
	@Autowired
	CartaoService cartaoService;
	
	@Autowired
	PagamentoRepository pagamentoRepository;
	
	
	public void efetuaPagamento(PagamentoDto pagamentoDto) {
		Optional<Cartao> optional = cartaoService.buscarPorId(pagamentoDto.getCartaoId());
	    
	    if(!optional.isPresent()) {
	      throw new ValidacaoException("cartao", "Cartão não encontrado");
	    }

//	    if(!optional.get().getAtivo()) {
//	        throw new ValidacaoException("cartao", "Cartão não ativo");
//	    }
	    
	    Cartao cartao = optional.get();
	    
	    Pagamento pagamento = new Pagamento(cartao, pagamentoDto.getDescricao(), pagamentoDto.getValor());

	    pagamentoRepository.save(pagamento);
	}
	
	public Iterable<Pagamento> buscarPagamentos(int idCartao){
		Optional<Cartao> optional = cartaoService.buscarPorId(idCartao);
		
		if (!optional.isPresent()) {
			throw new ValidacaoException("cartao", "cartao inexistente");
		}
		
		return pagamentoRepository.findAllByCartao_id(idCartao);
	}

}
