package com.Test;

import org.junit.Test;
import static org.junit.Assert.*;

import com.DAO.ServiciosDAO;

public class ServiciosTestJUnit {

	private ServiciosDAO servicios;

	private void setupEscenario1() {
		servicios = new ServiciosDAO();
		servicios.registarCliente(1, "Esneider Velandia", "Carrera 1 # 15", 1, 100000);
	}

	private void setupEscenario2() {
		servicios = new ServiciosDAO();
		servicios.registarCliente(1, "Esneider Velandia", "Carrera 1 # 15", 1, 100000);
		servicios.registrarTarjeta(2, 200000, 1);
	}

	/**
	 * Se registra un cliente
	 */
	@Test
	public void registrarCliente() {
		setupEscenario1();
		assertEquals(1, servicios.darNumClientes());
	}

	/**
	 * se registra una tarjeta a un cliente
	 */
	@Test
	public void registratTarjetaVirtual() {
		setupEscenario1();
		servicios.registrarTarjeta(2, 200000, 1);
		assertEquals(2, servicios.darNumTarjetas());
	}

	/**
	 * No se permite agregar mas de 2 tarjetas a un cliente
	 */
	@Test
	public void registratTresTarjetas() {
		setupEscenario2();
		servicios.registrarTarjeta(2, 200000, 1);
		assertEquals(2, servicios.darNumTarjetas());
	}

	/**
	 * No se permite agregar una tarjeta con valor negativo
	 */
	@Test
	public void regisrarTarjetaConValorNegativo() {
		setupEscenario1();
		servicios.registrarTarjeta(2, -100000, 1);
		assertEquals(1, servicios.darNumTarjetas());
		;
		servicios.darClientes();
	}

	/**
	 * No se permite registrar una tarjeta con el mismo numero
	 */
	@Test
	public void registrarTarjetaNumeroExistente() {
		setupEscenario1();
		servicios.registrarTarjeta(1, -100000, 1);
		servicios.darTarjetas();
		assertEquals(2, servicios.darNumTarjetas());
	}

	/**
	 * No se permite registrar el mismo clientes
	 */
	@Test
	public void clienteYaExistente() {
		setupEscenario1();
		servicios.registarCliente(1, "Esneider Velandia", "Carrera 1 # 15", 5, 100000);
		assertEquals(1, servicios.darNumClientes());
	}
}
