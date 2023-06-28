package es.unican.is2.gestionTienda.gui;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import es.unican.is2.gestionTienda.*;
import fundamentos.*;

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
		
		int opcion;
		while (true) {
			opcion = menu.leeOpcion();

			switch (opcion) {
			case NUEVA_VENTA:
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
				break;

			case VENDEDOR_DEL_MES:
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
				break;

			case VENDEDORES:
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
				break;
			}
		}
	}
}