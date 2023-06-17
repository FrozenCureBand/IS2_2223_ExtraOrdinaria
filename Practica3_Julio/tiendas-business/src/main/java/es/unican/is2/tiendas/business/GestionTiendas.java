package es.unican.is2.tiendas.business;

import es.unican.is2.tiendas.common.ITiendasDAO;
import es.unican.is2.tiendas.common.OperacionNoValida;
import es.unican.is2.tiendas.common.Tienda;
import es.unican.is2.tiendas.dao.EmpleadosDAO;
import es.unican.is2.tiendas.dao.TiendasDAO;
import es.unican.is2.tiendas.common.Empleado;
import es.unican.is2.tiendas.common.IEmpleadosDAO;
import es.unican.is2.tiendas.common.IGestionTiendas;
import es.unican.is2.tiendas.common.IGestionEmpleados;
import es.unican.is2.tiendas.gui.VistaGerente;

public class GestionTiendas implements IGestionTiendas, IGestionEmpleados{
	
	public GestionTiendas(TiendasDAO tiendasDAO2, EmpleadosDAO vehiculosDAO) {
		// TODO Auto-generated constructor stub
	}

	public Tienda altaTienda(Tienda t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Tienda bajaTienda(String nombre) throws OperacionNoValida {
		// TODO Auto-generated method stub
		return null;
	}

	public Tienda tienda(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	public Empleado altaEmpleado(Empleado e, String nombre) throws OperacionNoValida {
		// TODO Auto-generated method stub
		return null;
	}

	public Empleado bajaEmpleado(String dni, String nombre) throws OperacionNoValida {
		// TODO Auto-generated method stub
		return null;
	}

	public Empleado empleado(String dni) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
