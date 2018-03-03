package br.com.fiap.roupas.model;

import java.io.Serializable;
import java.util.List;

public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String data;
	private String valorTotal;
	private List<ItemPedido> itensPedido;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

}
