package es.unican.is2.tiendas.business;

import es.unican.is2.tiendas.common.Empleado;
import es.unican.is2.tiendas.common.IGestionEmpleados;
import es.unican.is2.tiendas.common.IGestionTiendas;
import es.unican.is2.tiendas.common.OperacionNoValida;
import es.unican.is2.tiendas.common.Tienda;
import es.unican.is2.tiendas.dao.EmpleadosDAO;
import es.unican.is2.tiendas.dao.TiendasDAO;

public class GestionTiendas implements IGestionTiendas, IGestionEmpleados {
    private TiendasDAO tiendasDAO;
    private EmpleadosDAO empleadosDAO;

    public GestionTiendas(TiendasDAO tiendasDAO, EmpleadosDAO empleadosDAO) {
        this.tiendasDAO = tiendasDAO;
        this.empleadosDAO = empleadosDAO;
    }

    public Tienda altaTienda(Tienda t) {
        Tienda tiendaExistente = tiendasDAO.tienda(t.getNombre());
        if (tiendaExistente != null) {
            return null; // Tienda ya existe, devuelve null
        }
        
        // Tienda no existe, guárdala en el DAO
        tiendasDAO.creaTienda(t);
        return t;
    }

    public Tienda bajaTienda(String nombre) throws OperacionNoValida {
        Tienda tienda = tiendasDAO.tienda(nombre);
        if (tienda != null) {
            if (!tienda.getEmpleados().isEmpty()) {
                throw new OperacionNoValida("La tienda tiene empleados");
            }

            tiendasDAO.eliminaTienda(nombre);
            return tienda;
        }
        return null; // Tienda no existe, devuelve null
    }

    public Tienda tienda(String nombre) {
        return tiendasDAO.tienda(nombre);
    }

    public Empleado altaEmpleado(Empleado e, String nombre) throws OperacionNoValida {
        Tienda tienda = tiendasDAO.tienda(nombre);
        if (tienda != null) {
            Empleado empleadoExistente = empleadosDAO.empleado(e.getDNI());
            if (empleadoExistente != null) {
                throw new OperacionNoValida("El empleado ya existe");
            }

            tienda.getEmpleados().add(e);
            empleadosDAO.creaEmpleado(e);
            return e;
        }
        return null; // Tienda no existe, devuelve null
    }

    public Empleado bajaEmpleado(String dni, String nombre) throws OperacionNoValida {
        Tienda tienda = tiendasDAO.tienda(nombre);
        if (tienda != null) {
            Empleado empleado = empleadosDAO.eliminaEmpleado(dni);
            if (empleado != null && tienda.getEmpleados().contains(empleado)) {
                tienda.getEmpleados().remove(empleado);
                return empleado;
            } else {
                throw new OperacionNoValida("El empleado no pertenece a la tienda indicada");
            }
        }
        return null; // Tienda no existe, devuelve null
    }

    public Empleado empleado(String dni) {
        return empleadosDAO.empleado(dni);
    }
}