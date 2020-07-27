package br.com.leangua.Cartoes.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.leangua.Cartoes.models.Cliente;
import br.com.leangua.Cartoes.services.ClienteService;

@RestController
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;

    @PostMapping("/cliente")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cliente CadastrarCliente(@Valid @RequestBody Cliente cliente){
    	return clienteService.criar(cliente);
    }
    
    @GetMapping("/cliente/{id}")
    public Cliente buscarCliente(@PathVariable int id) {
    	Optional<Cliente> optional = clienteService.buscaCliente(id);
    	
    	if (!optional.isPresent()) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    	}
    	
    	return optional.get();
    }

}
