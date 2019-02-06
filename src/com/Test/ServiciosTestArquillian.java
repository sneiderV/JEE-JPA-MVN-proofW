package com.Test;

import com.DAO.ServiciosDAO;

@RunWith(Arquillian.class)
public class ServiciosTestArquillian {
	
	 @Deployment
	    public static JavaArchive createDeployment() {
	        return ShrinkWrap.create(JavaArchive.class)
	            .addClass(ServiciosDAOiosDAO.class)
	            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	    }

	 
	 @Inject
	 ServiciosDAO servicios;
	 
	 /**
	  * Se registra un cliente
	  */
	 @Test
	 public void registrarCliente() {
		 servicios.registarCliente(1, "Esneider Velandia", "Carrera 1 # 15", 1, 100000);
		 Assert.assertNotNull("Se agrego el cliente", servicios.darCliente(1));
	 }
	 
	 /**
	  * se registra una tarjeta a un cliente
	  */
	 @Test
	 public void registratTarjetaVirtual() {
		 servicios.registarCliente(2, "Hisnardo Gonzalez", "Carrera 1 # 15", 2, 100000);
		 servicios.registrarTarjeta(3, 200000, 1);
		 assertEquals(2, servicios.darCliente(1).getTarjetas().size());
	 }
	 
	 /**
	  * No se permite agregar mas de 2 tarjetas a un cliente 
	  */
	 @Test
	 public void registratTresTarjetas() {
		 servicios.registarCliente(3, "Jairo Velez", "Carrera 1 # 15",4, 100000);
		 servicios.registrarTarjeta(5, 200000, 3);
		 servicios.registrarTarjeta(6, 200000, 3);
		 assertEquals(2, servicios.darCliente(3).getTarjetas().size());
	 }
	 
	 /**
	  * No se permite agregar una tarjeta con valor negativo
	  */
	 @Test
	 public void regisrarTarjetaConValorNegativo() {
		 servicios.registarCliente(4, "Santiago Gutierrez", "Carrera 1 # 15",4, -100000);
		 assertNull(servicios.darCliente(4));
	 }
	 
	 /**
	  * No se permite registrar una tarjeta con el mismo numero
	  */
	 @Test
	 public void registrarTarjetaNumeroExistente() {
		 servicios.registarCliente(5, "Gabriela Perez", "Carrera 1 # 15",5, 100000);
		 servicios.registrarTarjeta(5, 100000, 5);
		 assertEquals(1, servicios.darCliente(5).getTarjetas().size());
	 }
	 
	 /**
	  * No se permite registrar el mismo clientes
	  */
	 @Test
	 public void clienteYaExistente() {
		 servicios.registarCliente(6, "Laura Murcia", "Carrera 1 # 15", 6, 100000);
		 servicios.registarCliente(6, "Laura Murcia", "Carrera 1 # 15", 6, 100000);
		 assertEquals(1, servicios.darNumClientes());
	 }

	 // CON ESCENARIOS
	 
	 private void setupEscenario1( )
	    {
	    	servicios = new ServiciosDAO();
	    	servicios.registarCliente(1, "Esneider Velandia", "Carrera 1 # 15", 1, 100000);
	    }
	    
	    private void setupEscenario2( )
	    {
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
			 assertEquals(1, servicios.darNumTarjetas());;
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
