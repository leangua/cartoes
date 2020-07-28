package br.com.leangua.Cartoes.controllers;

import br.com.leangua.Cartoes.dto.*;
import br.com.leangua.Cartoes.dto.CartaoEntradaDto;
import br.com.leangua.Cartoes.models.Cartao;
import br.com.leangua.Cartoes.services.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CartaoController {
	
	@Autowired
	CartaoService cartaoService;

	@Autowired
	Mapper mapper;
	
	@PostMapping("/cartao")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CartaoDto criarCartao(@Valid @RequestBody CartaoEntradaDto cartaoEntradaDto) {
		Cartao cartao = mapper.paraCartao(cartaoEntradaDto);

		cartao = cartaoService.criar(cartao);

		return mapper.paraCartaoDto(cartao);
	}
	
	@PatchMapping("/cartao/{numero}")
	public void ativaCartao(@PathVariable String numero, @RequestBody boolean ativo) {
		cartaoService.ativar(numero);
	}
	
	@GetMapping("cartao/{numero}")
	public CartaoDto buscaCartao(@PathVariable String numero) {
		return cartaoService.buscar(numero);
	}

}
