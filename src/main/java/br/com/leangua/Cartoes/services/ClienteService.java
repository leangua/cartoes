package br.com.leangua.Cartoes.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leangua.Cartoes.models.Cliente;
import br.com.leangua.Cartoes.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	public Cliente criar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Optional<Cliente> buscaCliente(int id){
		return clienteRepository.findById(id);
	}
}
