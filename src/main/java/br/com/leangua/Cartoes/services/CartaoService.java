package br.com.leangua.Cartoes.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leangua.Cartao.dto.CartaoDto;
import br.com.leangua.Cartao.dto.CartaoEntradaDto;
import br.com.leangua.Cartoes.exceptions.ValidacaoException;
import br.com.leangua.Cartoes.models.Cartao;
import br.com.leangua.Cartoes.models.Cliente;
import br.com.leangua.Cartoes.repositories.CartaoRepository;

@Service
public class CartaoService {
	
	@Autowired
	CartaoRepository cartaoRepository;
	
	@Autowired
	ClienteService clienteService;
	
	public CartaoDto criar(CartaoEntradaDto cartaoEntradaDto) {
		
		Optional<Cliente> optional = clienteService.buscaCliente(cartaoEntradaDto.getClienteId());
		
		if (!optional.isPresent()) {
			throw new ValidacaoException("cliente", "cliente não encontrado");
		}
		
		Cliente cliente = optional.get();
		
		Cartao cartao = new Cartao(cartaoEntradaDto.getNumero(), cliente);
		
		cartaoRepository.save(cartao);
		
		return converter(cartao);
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
