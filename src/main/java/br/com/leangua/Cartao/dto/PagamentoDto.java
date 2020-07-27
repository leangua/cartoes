package br.com.leangua.Cartao.dto;

public class PagamentoDto {

	private int cartao_id;
	private String descricao;
	private double valor;
	
	public int getCartaoId() {
		return cartao_id;
	}
	public void setCartao_id(int cartao_id) {
		this.cartao_id = cartao_id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
}
