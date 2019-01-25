package com.DAO;

import java.util.List;

import javax.persistence.*;

import com.modelo.Cliente;
import com.modelo.TarjetaVirtual;

public class TarjetaVirtualDAO {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaH2");
	private static EntityManager manager;
	
	public TarjetaVirtualDAO() {
		
	}
	
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
	
	public void registrarTarjeta(int numero, double valor, int Idcliente) {
		
		System.out.println("---------------------------------------------------");
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		
		Cliente cliente = new Cliente();
		//cliente = manager.find(Cliente.class, Idcliente);
		cliente = (Cliente) manager.createQuery("SELECT c FROM Cliente c WHERE c.id="+Idcliente).getSingleResult();
		System.out.println("--> cliente buscado"+cliente);
		System.out.println("--> cliente con "+cliente.getTarjetas().size()+"tarjetas");
		
		TarjetaVirtual tv = new TarjetaVirtual(numero, valor, cliente);
		manager.persist(tv);
		
		System.out.println("--> agregue la tarjeta virtual: "+tv.toString());
		
		manager.getTransaction().commit();
		manager.close();
		System.out.println("---------------------------------------------------");
	}
}
