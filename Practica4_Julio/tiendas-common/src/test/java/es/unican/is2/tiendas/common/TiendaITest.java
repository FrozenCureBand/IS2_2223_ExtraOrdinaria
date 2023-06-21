package es.unican.is2.tiendas.common;

import es.unican.is2.tiendas.business.GestionTiendas;
import es.unican.is2.tiendas.dao.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
    
    @Test
    public void testGastoMensualSueldos2() {

    	GestionTiendas gt = new GestionTiendas(tiendasDAO, vehiculosDAO);
    	Tienda t1 = new Tienda();
    	t1 = gt.tienda("MaliañoCentro");
        double gastoMensual = t1.gastoMensualSueldos();

        // Verificar el resultado esperado
        assertEquals(2300.0, gastoMensual);
    }
    
    @Test
    public void testGastoMensualSueldosTiendaInexistente() {
        // Verificar el resultado esperado
        try {
			for (int i = 0; i < 11; i++) {
				GestionTiendas gt = new GestionTiendas(tiendasDAO, vehiculosDAO);
		    	Tienda t1 = new Tienda();
		    	t1 = gt.tienda("BarakaldoCentro");
		        double gastoMensual = t1.gastoMensualSueldos();
			}
	         fail("Tienda Inxistente en DAO");
	     } catch (final RuntimeException e) {
	         assertTrue(true);
	     }
    }
    
}
            	


	     