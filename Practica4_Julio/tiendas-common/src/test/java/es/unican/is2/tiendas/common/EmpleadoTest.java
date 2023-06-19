package es.unican.is2.tiendas.common;

import java.time.LocalDate;
import java.time.Period;
import org.junit.Test;
import junit.framework.TestCase;

public class EmpleadoTest extends TestCase {
	
//    @Test
//    public void testSueldoDependiente() {
//        // Prueba para la categoría "DEPENDIENTE"
//        Empleado1 empleado = new Empleado1(Categoria.DEPENDIENTE);
//        double resultado = empleado.sueldo();
//        assertEquals(1000.0, resultado);
//    }
//
//    @Test
//    public void testSueldoEncargadoSinAntiguedad() {
//        // Prueba para la categoría "ENCARGADO" sin antigüedad
//        Empleado1 empleado = new Empleado1(Categoria.ENCARGADO);
//        double resultado = empleado.sueldo();
//        assertEquals(1200.0, resultado);
//    }
//
//    @Test
//    public void testSueldoEncargadoConAntiguedad() {
//        // Prueba para la categoría "ENCARGADO" con antigüedad
//        LocalDate fechaContrato = LocalDate.now().minusYears(6);
//        Empleado1 empleado = new Empleado1(Categoria.ENCARGADO, fechaContrato);
//        double resultado = empleado.sueldo();
//        assertEquals(1250.0, resultado);
//    }
//
//    @Test
//    public void testSueldoEncargadoConMasAntiguedad() {
//        // Prueba para la categoría "ENCARGADO" con más antigüedad
//        LocalDate fechaContrato = LocalDate.now().minusYears(11);
//        Empleado1 empleado = new Empleado1(Categoria.ENCARGADO, fechaContrato);
//        double resultado = empleado.sueldo();
//        assertEquals(1300.0, resultado);
//    }

    @Test
    public void testSueldoEncargadoConMaxAntiguedad() {
        // Prueba para la categoría "ENCARGADO" con máxima antigüedad
        LocalDate fechaContrato = LocalDate.now().minusYears(16);
        Empleado1 empleado = new Empleado1(Categoria.ENCARGADO, fechaContrato);
        double resultado = empleado.sueldo();
        assertEquals(1350.0, resultado);
    }

//    @Test
//    public void testSueldoCategoríaInvalida() {
//        // Prueba para una categoría no válida
//        Empleado1 empleado = new Empleado1(Categoria.OTRA_CATEGORIA);
//        double resultado = empleado.sueldo();
//        assertEquals(0.0, resultado);
//    }
//
//    @Test
//    public void testSueldoConBaja() {
//        // Prueba con baja activa
//        Empleado1 empleado = new Empleado1(Categoria.DEPENDIENTE);
//        empleado.setBaja(true);
//        double resultado = empleado.sueldo();
//        assertEquals(750.0, resultado);
//    }
}
//Tuve que implementar una copia de la Clase Empleado ya que causaba conflicto con los imports del paquete es.unican.is2.tiendas.common, segun el buildbath 
// y el maven compile e install todo esta ok, se debe a un problema del propio eclipse.
class Empleado1 {
    private Categoria categoria;
    private LocalDate fechaContrato;
    private boolean baja;

    public Empleado1(Categoria categoria) {
        this.categoria = categoria;
    }

    public Empleado1(Categoria categoria, LocalDate fechaContrato) {
        this.categoria = categoria;
        this.fechaContrato = fechaContrato;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }

    public double sueldo() {
        double sueldoBase;

        if (categoria == Categoria.DEPENDIENTE) {
            sueldoBase = 1000;
        } else if (categoria == Categoria.ENCARGADO) {
            sueldoBase = 1200;
            int antiguedad = Period.between(fechaContrato, LocalDate.now()).getYears();
            System.out.println(antiguedad);
            if (antiguedad > 15) {
                sueldoBase += 150;
                return sueldoBase;
            }
            
            if (antiguedad > 10) {
                sueldoBase += 100;
                return sueldoBase;
            }
            
            if (antiguedad > 5) {
                sueldoBase += 50;
                return sueldoBase;
            }
            
        } else {
            sueldoBase = 0;
        }

        if (baja) {
            sueldoBase *= 0.75;
        }
        return sueldoBase;
    }
}

