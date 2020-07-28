package br.com.leangua.Cartoes.dto;

import br.com.leangua.Cartoes.models.Cartao;
import br.com.leangua.Cartoes.models.Cliente;
import br.com.leangua.Cartoes.models.Pagamento;
import org.springframework.stereotype.Component;

@Component
public class Mapper {


    public Cartao paraCartao(CartaoEntradaDto cartaoEntradaDto){
        Cartao cartao = new Cartao();
        cartao.setNumero(cartaoEntradaDto.getNumero());

        Cliente cliente = new Cliente();
        cliente.setId(cartaoEntradaDto.getClienteId());
        cartao.setCliente(cliente);

        return cartao;
    }

    public CartaoDto paraCartaoDto(Cartao cartao){
        CartaoDto cartaoDto = new CartaoDto();
        cartaoDto.setClienteId(cartao.getCliente().getId());
        cartaoDto.setAtivo(cartao.getAtivo());
        cartaoDto.setId(cartao.getId());
        cartaoDto.setNumero(cartao.getNumero());
        return cartaoDto;
    }

    public Pagamento paraPagamento(PagamentoDto pagamentoDto){
        Pagamento pagamento = new Pagamento();
        pagamento.setDescricao(pagamentoDto.getDescricao());
        pagamento.setValor(pagamentoDto.getValor());

        Cartao cartao = new Cartao();
        cartao.setId(pagamentoDto.getCartaoId());
        pagamento.setCartao(cartao);
        return pagamento;
    }
}
