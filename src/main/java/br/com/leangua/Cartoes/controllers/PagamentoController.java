package br.com.leangua.Cartoes.controllers;

import br.com.leangua.Cartoes.dto.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.leangua.Cartoes.dto.PagamentoDto;
import br.com.leangua.Cartoes.models.Pagamento;
import br.com.leangua.Cartoes.services.PagamentoService;

@RestController
public class PagamentoController {
	
	@Autowired
	PagamentoService pagamentoService;

	@Autowired
	Mapper mapper;

	
	@PostMapping("/pagamento")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void efetuaPagamento(@RequestBody PagamentoDto pagamentoDto) {
		Pagamento pagamento = new Pagamento();
		pagamento = mapper.paraPagamento(pagamentoDto);

		pagamentoService.efetuaPagamento(pagamento);
	}
	
	@GetMapping("/pagamentos/{id_cartao}")
	public Iterable<Pagamento> buscarPagamentos(@PathVariable int id_cartao){
		return pagamentoService.buscarPagamentos(id_cartao);
	}

}
