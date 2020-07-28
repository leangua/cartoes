package br.com.leangua.Cartoes.services;

import br.com.leangua.Cartoes.dto.CartaoDto;
import br.com.leangua.Cartoes.exceptions.ValidacaoException;
import br.com.leangua.Cartoes.models.Cartao;
import br.com.leangua.Cartoes.models.Cliente;
import br.com.leangua.Cartoes.repositories.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartaoService {
	
	@Autowired
	CartaoRepository cartaoRepository;
	
	@Autowired
	ClienteService clienteService;
	
	public Cartao criar(Cartao cartao) {
		
		Optional<Cliente> optional = clienteService.buscaCliente(cartao.getCliente().getId());
		
		if (!optional.isPresent()) {
			throw new ValidacaoException("cliente", "cliente não encontrado");
		}
		
		Cliente cliente = optional.get();
		
		cartao.setCliente(cliente);
		cartao.setAtivo(false);

		cartaoRepository.save(cartao);
		
		return cartao;
	}
	
	public CartaoDto ativar(String numero) {
		Optional<Cartao> optional = cartaoRepository.findByNumero(numero);
		
		if (!optional.isPresent()) {
			throw new ValidacaoException("cartao", "cartao inexistente");
		}
		
		Cartao cartao = optional.get();
		
		if (cartao.getAtivo()) {
			throw new ValidacaoException("cartao", "cartao já está ativo");
		}
		
		cartao.setAtivo(true);
		
		cartaoRepository.save(cartao);
		
		return converter(cartao);
	}
	
	public CartaoDto buscar(String numero) {
		Optional<Cartao> optional = cartaoRepository.findByNumero(numero);
		
		if (!optional.isPresent()) {
			throw new ValidacaoException("cartao", "cartao inexistente");
		}
		
		Cartao cartao = optional.get();
		
		return converter(cartao);
	}
	
	public Optional<Cartao>buscarPorId(int id) {
		return cartaoRepository.findById(id);
	}
	
	public CartaoDto converter(Cartao cartao) {
		CartaoDto cartaoDto = new CartaoDto();
		cartaoDto.setId(cartao.getId());
		cartaoDto.setNumero(cartao.getNumero());
		cartaoDto.setClienteId(cartao.getCliente().getId());
		cartaoDto.setAtivo(false);
		
		return cartaoDto;
	}

}
