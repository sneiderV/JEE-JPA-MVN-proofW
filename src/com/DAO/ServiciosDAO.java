package com.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.modelo.Cliente;
import com.modelo.TarjetaVirtual;

public class ServiciosDAO {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaH2");
	private static EntityManager manager;
	
	
	/**
	 * 
	 * @param id
	 * @param nombre
	 * @param direccion
	 * @param numero
	 * @param valor
	 */
	public void registarCliente(int id, String nombre, String direccion,int numero, double valor) {
		System.out.println("---------------------------------------------------");
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		
		Cliente c = new Cliente(id, nombre,direccion);
		if (!existeCliente(c)) {
			
			TarjetaVirtual tv = new TarjetaVirtual(numero, valor, c);
			if (!existeTarjeta(tv)) {
				manager.persist(c);
				System.out.println("--> agregue el cliente: "+c.toString());
				manager.persist(tv);
				c.addTarjetaVirtual(tv);
				System.out.println("--> con la tarjeta "+numero);
				manager.getTransaction().commit();
			}
			else {
				System.out.println("La tarjeta con ese numero ya existe");
			}
		}
		else {
			System.out.println("El cliente con ese nombre o identificacion ya existe");
		}
		
		manager.close();
		System.out.println("---------------------------------------------------");
	}
	
	/**
	 * 
	 * @param numero
	 * @param valor
	 * @param Idcliente
	 */
	public void registrarTarjeta(int numero, double valor, int idCliente) {
		
		System.out.println("---------------------------------------------------");
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		
		Cliente cliente = new Cliente();
		//cliente = manager.find(Cliente.class, Idcliente);
		//cliente = (Cliente) manager.createQuery("SELECT c FROM Cliente c WHERE c.id="+idCliente).getSingleResult();
		cliente = darCliente(idCliente);
		//if (existeCliente(cliente)) {
		if (cliente!=null) {
			System.out.println("--> cliente buscado"+cliente);
			
			if (!cumpleMaximoDeTarjetas(cliente)) {
				TarjetaVirtual tv = new TarjetaVirtual(numero, valor, cliente);
				
				if (!existeTarjeta(tv)) {
					manager.persist(tv);
					System.out.println("--> agregue la tarjeta virtual: "+tv.toString());
				}
				else {
					System.out.println("La tarjeta con ese numero ya existe");
				}
				
			}
			else {
				System.out.println("el cliente ya tiene el maximo de tarjetas");
			}
		}
		else {
			System.out.println("el cliente no existe");
		}
		
		manager.getTransaction().commit();
		manager.close();
		System.out.println("---------------------------------------------------");
	}
	
	/**
	 * 
	 * @param nuevoCliente
	 * @return
	 */
	public boolean existeCliente(Cliente nuevoCliente) {
		boolean exite = false;
			List<Cliente> clientes = (List<Cliente>)manager.createQuery("SELECT e FROM Cliente e").getResultList();
			
			for (int i = 0; i < clientes.size() && !exite ; i++) {
				if (clientes.get(i).getNombre().equals(nuevoCliente.getNombre()) && clientes.get(i).getId()==nuevoCliente.getId()) {
					exite=true;
				}
			}
			System.out.println("--> el cliente existe? "+exite);
			
		return exite;
	}
	
	/**
	 * Verifica la existencia de una tarjeta 
	 * @param nuevaTarjeta
	 * @return
	 */
	public boolean existeTarjeta(TarjetaVirtual nuevaTarjeta) {
		boolean existe = false;
		List<TarjetaVirtual> tarjetas = (List<TarjetaVirtual>)manager.createQuery("SELECT t FROM TarjetaVirtual t").getResultList();
		
		for (int i = 0; i < tarjetas.size() && !existe ; i++) {
			if(tarjetas.get(i).getNumero()==nuevaTarjeta.getNumero()) {
				existe=true;
			}
		}
		System.out.println("--> la tarjeta existe? "+existe);
		return existe;
	}
	
	/**
	 * Verifica la cantidad maxima de tarjetas que tiene un usuario, solo puede tener 2 tarjetas
	 * @param cliente
	 * @return
	 */
	public boolean cumpleMaximoDeTarjetas(Cliente cliente) {
		boolean cumpleMaximo = false;
		//cliente = manager.find(Cliente.class, Idcliente);
		cliente = (Cliente) manager.createQuery("SELECT c FROM Cliente c WHERE c.id="+cliente.getId()).getSingleResult();
		if(cliente.getTarjetas().size()>=2) {
			cumpleMaximo=true;
		}
		System.out.println("-->el cliente tiene "+cliente.getTarjetas().size()+" tarjetas");
		return cumpleMaximo;
	}
	
	/**
	 * 
	 * @param idCliente
	 * @return el cliente con el id especifico y null si no existe
	 */
	public Cliente darCliente(int idCliente) {
		Cliente buscado = null;
		boolean existe = false;
		List<Cliente> clientes = (List<Cliente>)manager.createQuery("SELECT e FROM Cliente e").getResultList();
		for (int i = 0; i < clientes.size() && !existe ; i++) {
			if (clientes.get(i).getId()==idCliente) {
				existe=true;
				buscado = clientes.get(i);
			}
		}
		System.out.println("--> el cliente existe? "+existe);
		
		return buscado;
	}
	
	/**
	 * 
	 */
	public void darTarjetas() {
		System.out.println("---------------------------------------------------");
		manager = emf.createEntityManager();
		
		List<TarjetaVirtual> tarjetas = (List<TarjetaVirtual>)manager.createQuery("SELECT t FROM TarjetaVirtual t").getResultList();
		
		System.out.println("--> "+tarjetas.size()+" TARJETAS.");
		for (TarjetaVirtual tv : tarjetas) {
			System.out.println(tv.toString());
		}
		
		manager.close();
		System.out.println("---------------------------------------------------");
	}
	
	/**
	 * 
	 */
	public void darClientes() {
		System.out.println("---------------------------------------------------");
		manager = emf.createEntityManager();
		
		List<Cliente> clientes = (List<Cliente>)manager.createQuery("SELECT e FROM Cliente e").getResultList();
				
		System.out.println("--> "+clientes.size()+" CLIENTES.");
		for (Cliente cliente : clientes) {
			System.out.println(cliente.toString());
		}
		
		manager.close();
		System.out.println("---------------------------------------------------");
	}
}
