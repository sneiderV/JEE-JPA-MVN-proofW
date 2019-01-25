package com.modelo;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="TarjetasVirtuales")
public class TarjetaVirtual implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="numero")
	private int numero;
	
	@Column(name="valor")
	private double valor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cliente")
	private Cliente cliente;
	
	public TarjetaVirtual() {
		
	}

	public TarjetaVirtual(int numero, double valor, Cliente cliente) {
		super();
		this.numero = numero;
		this.valor = valor;
		this.cliente = cliente;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	@Override
	public String toString() {
		return "TarjetaVirtual [numero=" + numero + ", valor=" + valor + ", cliente=" + cliente + "]";
	}

}
