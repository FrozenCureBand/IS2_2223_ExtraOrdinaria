package es.unican.is2.tiendas.main;

import es.unican.is2.tiendas.dao.TiendasDAO;
import es.unican.is2.tiendas.dao.EmpleadosDAO;

import es.unican.is2.tiendas.business.GestionTiendas;

import es.unican.is2.tiendas.gui.VistaGerente;
/**
 * Clase principal que construye la aplicaci�n de tres capas y lanza su ejecuci�n
 */
public class Runner {

	public static void main(String[] args) {
		// Componentes capa DAO
		TiendasDAO tiendasDAO = new TiendasDAO();
		EmpleadosDAO vehiculosDAO = new EmpleadosDAO();
		
		// Componentes capa negocio
		GestionTiendas negocio = new GestionTiendas(tiendasDAO, vehiculosDAO);
		
		// Componentes casa presentacion
		VistaGerente vista = new VistaGerente(negocio, negocio);
		
		// Lanza ejecuci�n
		vista.setVisible(true);
	}

}
