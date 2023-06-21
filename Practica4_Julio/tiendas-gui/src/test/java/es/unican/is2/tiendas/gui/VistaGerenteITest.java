package es.unican.is2.tiendas.gui;
import es.unican.is2.tiendas.business.*;
import es.unican.is2.tiendas.dao.EmpleadosDAO;
import es.unican.is2.tiendas.dao.TiendasDAO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fest.swing.fixture.FrameFixture;

public class VistaGerenteITest {

	private FrameFixture demo;
		// Componentes capa DAO
			TiendasDAO tiendasDAO = new TiendasDAO();
			EmpleadosDAO vehiculosDAO = new EmpleadosDAO();
			
		// Componentes capa negocio
			GestionTiendas negocio = new GestionTiendas(tiendasDAO, vehiculosDAO);
			
		
	@BeforeEach
	public void setUp() {
		VistaGerente gui = new VistaGerente(negocio, negocio);
		demo = new FrameFixture(gui);
		gui.setVisible(true);	
	}
	
	@AfterEach
	public void tearDown() {
		demo.cleanUp();
	}
	
	@Test
	public void test() {
		// Comprobamos que la interfaz tiene el aspecto deseado
		demo.button("btnBuscar").requireText("Buscar");
		//  Prueba de saludo con nombre
		// Escribimos un nombre
		demo.textBox("txtNombreTienda").enterText("SantanderCentro");
		// Pulsamos el botón
		demo.button("btnBuscar").click();		
		// Comprobamos la salida
		demo.textBox("txtDireccionTienda").requireText("Paseo Pereda 3 Santander 39001");
		
		//Prueba de Tienda sin nombre
		demo.textBox("txtNombreTienda").setText("");
		demo.button("btnBuscar").click();
		demo.textBox("txtDireccionTienda").requireText("Paseo Pereda 3 Santander 39001");
		
		//Prueba de la Lista de empleados por tienda se muestra correctamente
		demo.button("btnBuscar").requireText("Buscar");
		//  Prueba de saludo con nombre
		// Escribimos un nombre
		demo.textBox("txtNombreTienda").enterText("SantanderCentro");
		// Pulsamos el botón
		demo.button("btnBuscar").click();		
		// Comprobamos la salida
		demo.list("listNombreEmpleados").requireSelectedItems("","");
		
		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
