package es.unican.is2.tiendas.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TiendaTest {

    private Tienda tienda;
    private Empleado empleado1;
    private Empleado empleado2;

//    @BeforeEach
//    public void setUp() {
//        // Crear instancias simuladas de Empleado con Mockito
//        empleado1 = mock(Empleado.class);
//        empleado2 = mock(Empleado.class);
//
//        // Configurar comportamiento simulado de los empleados
//        when(empleado1.sueldo()).thenReturn(1000.0);
//        when(empleado2.sueldo()).thenReturn(1200.0);
//
//        // Crear una lista de empleados y agregar los empleados simulados
//        List<Empleado> empleados = new ArrayList<Empleado>();
//        empleados.add(empleado1);
//        empleados.add(empleado2);
//
//        // Crear una instancia de Tienda y establecer la lista de empleados
//        tienda = new Tienda();
//    }
//
//    @Test
//    public void testGastoMensualSueldos() {
//        // Calcular el gasto mensual de sueldos de la tienda
//        double gastoMensual = tienda.gastoMensualSueldos();
//
//        // Verificar el resultado esperado
//        assertEquals(2200.0, gastoMensual);
//    }
//
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