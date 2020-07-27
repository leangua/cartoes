package br.com.leangua.Cartoes.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.leangua.Cartao.dto.CartaoDto;
import br.com.leangua.Cartao.dto.CartaoEntradaDto;
import br.com.leangua.Cartoes.services.CartaoService;

@RestController
public class CartaoController {
	
	@Autowired
	CartaoService cartaoService;
	
	@PostMapping("/cartao")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CartaoDto criarCartao(@Valid @RequestBody CartaoEntradaDto cartaoEntradaDto) {
		return cartaoService.criar(cartaoEntradaDto);
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
