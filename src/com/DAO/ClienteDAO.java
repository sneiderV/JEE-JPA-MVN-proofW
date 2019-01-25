package com.DAO;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.*;
import com.modelo.*;
/**
 * Gestor de persistencia
 * @author Esneider
 *
 */
public class ClienteDAO {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaH2");
	private static EntityManager manager;
	
	public ClienteDAO() {
		//gestor de persistencia 
		//emf = Persistence.createEntityManagerFactory("PersistenciaH2");
		//manager = emf.createEntityManager();
	}
	
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
	
	public Cliente darCliente(int id) {
		Cliente c = new Cliente();
		
		manager = emf.createEntityManager();
		
			//c = manager.find(Cliente.class, id);
		c = (Cliente) manager.createQuery("SELECT c FROM Cliente c WHERE c.id="+id).getSingleResult();
		System.out.println("-->cliente buscado "+c);

		manager.close();
		return c;
	}
	
	public void registarCliente(int id, String nombre, String direccion,int numero, double valor) {
		System.out.println("---------------------------------------------------");
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		
		Cliente c = new Cliente(id, nombre,direccion);
		manager.persist(c);
		System.out.println("--> agregue el cliente: "+c.toString());
		//asigno tarjeta
		TarjetaVirtual tv = new TarjetaVirtual(numero, valor, c);
		manager.persist(tv);
		c.addTarjetaVirtual(tv);
		System.out.println("--> con la tarjeta "+numero);
		
		manager.getTransaction().commit();
		manager.close();
		System.out.println("---------------------------------------------------");
	}
	
}
