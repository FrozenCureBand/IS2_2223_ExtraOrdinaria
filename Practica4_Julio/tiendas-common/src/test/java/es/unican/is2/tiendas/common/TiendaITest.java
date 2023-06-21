package es.unican.is2.tiendas.common;

import es.unican.is2.tiendas.business.GestionTiendas;
import es.unican.is2.tiendas.dao.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TiendaITest {

    private Tienda tienda;
    private Empleado empleado;
    private Empleado empleado2;
    TiendasDAO tiendasDAO = new TiendasDAO();
	EmpleadosDAO vehiculosDAO = new EmpleadosDAO();
    
    @Test
    public void testGastoMensualSueldos() {

    	GestionTiendas gt = new GestionTiendas(tiendasDAO, vehiculosDAO);
    	Tienda t1 = new Tienda();
    	t1 = gt.tienda("SantanderCentro");
        double gastoMensual = t1.gastoMensualSueldos();

        // Verificar el resultado esperado
        assertEquals(3050.0, gastoMensual);
    }

//    @Test
//    public void testGetEmpleados() {
//        // Obtener la lista de empleados de la tienda
//        List<Empleado> empleadosObtenidos = tienda.getEmpleados();
//
//        // Verificar que la lista de empleados es la misma que la configurada anteriormente
//        assertEquals(2, empleadosObtenidos.size());
//        assertEquals(empleado1, empleadosObtenidos.get(0));
//        assertEquals(empleado2, empleadosObtenidos.get(1));
//    }
    

}

