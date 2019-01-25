package com.controller;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.ClienteDAO;
import com.DAO.ServiciosDAO;
import com.DAO.TarjetaVirtualDAO;
import com.modelo.Cliente;
import com.modelo.TarjetaVirtual;

/**
 * Servlet implementation class servicios
 */
@WebServlet("/servicios")
public class servicios extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servicios() {
        super();
    }
    /*
    public void init() {
    	EmpleadoDAO e = new EmpleadoDAO();
    	e.crearEmpleado(10L, "VELANDIA", "ESNEIDER", new GregorianCalendar(1979,6,6).getTime());
    	e.darEmpleados();		
	} */
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
				
		String op = request.getParameter("op");
		switch (op) {
		case "1":
			int id = Integer.parseInt(request.getParameter("id"));
			String nom = request.getParameter("nom");
			String dir = request.getParameter("dir");
			int num = Integer.parseInt(request.getParameter("num"));
			double val = Double.parseDouble(request.getParameter("val"));
					
			//ClienteDAO c = new ClienteDAO();
			//c.registarCliente(id, nom, dir, num, val);
			if(val >0) {
				ServiciosDAO sd = new ServiciosDAO();
				sd.registarCliente(id, nom, dir, num, val);
				
			}else {
				System.out.println("el valor de la tarjeta debe ser mayor a cero");
			}
			
	    	break;
		case "2":
			
			int numero = Integer.parseInt(request.getParameter("num"));
			double valor = Double.parseDouble(request.getParameter("val"));
			int idCliente = Integer.parseInt(request.getParameter("id"));
			
			//TarjetaVirtualDAO tvNueva = new TarjetaVirtualDAO();
			//tvNueva.registrarTarjeta(numero, valor, idCliente);
			if (valor>0) {
				ServiciosDAO sda = new ServiciosDAO();
				sda.registrarTarjeta(numero, valor, idCliente);
			}
			else {
				System.out.println("el valor de la tarjeta debe ser mayor a cero");
			}

			break;
		case "3":
			
			//ClienteDAO cd = new ClienteDAO();
			//cd.darClientes();
			ServiciosDAO sdao = new ServiciosDAO();
			sdao.darClientes();
			
			break;
		case "4":
			
			//TarjetaVirtualDAO td = new TarjetaVirtualDAO();
			//td.darTarjetas();
			ServiciosDAO sedao = new ServiciosDAO();
			sedao.darTarjetas();
			
			break;
		
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
