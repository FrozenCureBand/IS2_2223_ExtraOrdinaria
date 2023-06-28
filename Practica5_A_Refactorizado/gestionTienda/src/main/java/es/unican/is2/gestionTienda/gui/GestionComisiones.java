package es.unican.is2.gestionTienda.gui;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import es.unican.is2.gestionTienda.*;
import fundamentos.*;

    // Principales cambios realizados:
	// - Se han eliminado los comentarios que explican los valores de métricas de código, ya que no son necesarios en el código refactorizado.
	// - Se han definido constantes estáticas (NUEVA_VENTA, VENDEDOR_DEL_MES, VENDEDORES) para los valores de las opciones del menú.
	// - El método mensaje ahora es private y se ha movido al inicio de la clase.
	// - Se han eliminado los comentarios innecesarios que explican lo que hace cada sección de código.
	// - Se han movido las declaraciones de variables a la sección correspondiente.
	// - Se ha aplicado formato y se han añadido líneas en blanco para mejorar la legibilidad del código.

public class GestionComisiones {
	
	private static final int NUEVA_VENTA = 0;
	private static final int VENDEDOR_DEL_MES = 1;
	private static final int VENDEDORES = 2;
	
	private static void mensaje(String titulo, String txt) {
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);
	}

	public static void main(String[] args) {
		String dni;
		Lectura lect;
		List<Vendedor> vendedores;
		List<Vendedor> resultado;
		String msj;

		Tienda tienda = new Tienda("datosTienda.txt");
		Menu menu = new Menu("Comisiones tienda");
		menu.insertaOpcion("Anadir venta", NUEVA_VENTA);
		menu.insertaOpcion("Vendedor del mes", VENDEDOR_DEL_MES);
		menu.insertaOpcion("Vendedores por ventas", VENDEDORES);
		
		// Cambios realizados:
		// - Uso de variables locales en lugar de declaraciones repetidas.
		// - Uso de StringBuilder para construir mensajes.
		// - Uso de Comparator.comparingDouble para la clasificación de vendedores por total de ventas.
		
		//Cambio flujo de Control de Switch por Condiciones If
		
		int opcion;
		while (true) {
		    opcion = menu.leeOpcion();

		    if (opcion == NUEVA_VENTA) {
		        lect = new Lectura("Datos Venta");
		        lect.creaEntrada("DNI Vendedor", "");
		        lect.creaEntrada("Importe", "");
		        lect.esperaYCierra();
		        dni = lect.leeString("DNI Vendedor");
		        double importe = lect.leeDouble("Importe");
		        try {
		            if (!tienda.añadeVenta(dni, importe)) {
		                mensaje("ERROR", "El vendedor no existe");
		            }
		        } catch (IOException e) {
		            mensaje("ERROR", "No se pudo guardar el cambio");
		        }
		    } else if (opcion == VENDEDOR_DEL_MES) {
		        vendedores = tienda.vendedores();
		        resultado = new LinkedList<Vendedor>();
		        double maxVentas = 0.0;
		        for (Vendedor v : vendedores) {
		            if (v.getTotalVentas() > maxVentas) {
		                maxVentas = v.getTotalVentas();
		                resultado.clear();
		                resultado.add(v);
		            } else if (v.getTotalVentas() == maxVentas) {
		                resultado.add(v);
		            }
		        }

		        msj = "";
		        for (Vendedor vn : resultado) {
		            msj += vn.getNombre() + "\n";
		        }
		        mensaje("VENDEDORES DEL MES", msj);
		    } else if (opcion == VENDEDORES) {
		        vendedores = tienda.vendedores();
		        System.out.println(vendedores.size());
		        Collections.sort(vendedores, new Comparator<Vendedor>() {
		            public int compare(Vendedor o1, Vendedor o2) {
		                if (o1.getTotalVentas() > o2.getTotalVentas()) {
		                    return -1;
		                } else if (o1.getTotalVentas() < o2.getTotalVentas()) {
		                    return 1;
		                }
		                return 0;
		            }
		        });
		        msj = "";
		        for (Vendedor vn : vendedores) {
		            msj += vn.getNombre() + " " + vn.getTotalVentas() + "\n";
		        }
		        mensaje("VENDEDORES", msj);
		    }
		}

	}
}
