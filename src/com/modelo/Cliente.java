package com.modelo;

import java.io.Serializable;
import java.util.*;

import com.modelo.TarjetaVirtual;

import javax.persistence.*;

@Entity
@Table(name="Clientes")
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="nombre",length=40)
	private String nombre;
	
	@Column(name="direccion",length=50)
	private String direccion;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<TarjetaVirtual> tarjetas = new ArrayList<>();
	
	public Cliente() {
		
	}
	
	public Cliente(int id, String nombre, String direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public List<TarjetaVirtual> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<TarjetaVirtual> tarjetas) {
		this.tarjetas = tarjetas;
	}
	
	public void addTarjetaVirtual(TarjetaVirtual tv) {
		if (!tarjetas.contains(tv)) {
			tarjetas.add(tv);
			tv.setCliente(this);
		}
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion +"]";
	}

	
	
}
