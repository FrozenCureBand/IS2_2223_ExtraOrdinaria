package es.unican.is2.gestionTienda;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;  // Se utiliza la interfaz List en lugar de la clase LinkedList
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Clase que representa una tienda con un conjunto de vendedores.
 * Gestiona las ventas realizadas y las comisiones asignadas a cada
 * vendedor. Los datos de la tienda se almacenan en un fichero de texto
 * que se pasa como parámetro al crear la tienda.
 */
public class Tienda {
	
	private List<Vendedor> lista = new ArrayList<>();  // Utilizar la interfaz List en lugar de la clase LinkedList
	private String direccion;
	private String nombre;
	private String datos;

	/**
	 * Crea la tienda cargando los datos desde el fichero indicado
	 * 
	 * @param datos Path absoluto del fichero de datos
	 */
	public Tienda(String datos) {
		this.datos = datos;
	}

	/**
	 * Retorna la dirección de la tienda
	 * @return Dirección de la tienda
	 */
	public String getDireccion() {  // Cambiar el nombre del método a getDireccion() 
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 * @return Nombre de la tienda
	 */
	public String getNombre() {  // Cambiar el nombre del método a getNombre() 
		return nombre;
	}

	/**
	 * Añade un nuevo vendedor a la tienda
	 * 
	 * @param nuevoVendedor Vendedor a añadir
	 * @return true si el vendedor se ha añadido, false si ya había un vendedor con el mismo id
	 */
	public boolean añade(Vendedor nuevoVendedor) throws IOException {  // Cambiar el nombre del método a añade() 
		Vendedor v = buscaVendedor(nuevoVendedor.getDNI());
		if (v != null) {
			return false;
		}
		lista.add(nuevoVendedor);
		vuelcaDatos();
		return true;
	}

	/**
	 * Elimina el vendedor cuyo dni se pasa como parámetro
	 * 
	 * @param id Id del vendedor a eliminar
	 * @return true si se elimina el vendedor, false si no existe ningún vendedor con el id indicado
	 */
	public boolean eliminaVendedor(String id) throws IOException {
		Vendedor v = buscaVendedor(id);
		if (v == null) {
			return false;
		}
		lista.remove(v);
		vuelcaDatos();
		return true;
	}

	/**
	 * Añade una venta a un vendedor
	 * 
	 * @param id Id del vendedor
	 * @param importe Importe de la venta
	 * @return true si se añade la venta, false si no se encuentra el vendedor
	 */
	public boolean añadeVenta(String id, double importe) throws IOException {  // Cambiar el nombre del método a añadeVenta() 
		Vendedor v = buscaVendedor(id);
		if (v == null) {
			return false;
		}
		double importeFinal = importe;
		//Se cambia el swtich por if aninados para ayudar a la lectura del codigo
		if (v instanceof VendedorEnPlantilla) {
		    TipoVendedor tipo = ((VendedorEnPlantilla) v).getTipo();
		    if (tipo == TipoVendedor.JUNIOR) {
		        importeFinal *= 1.005;
		    } else if (tipo == TipoVendedor.SENIOR) {
		        importeFinal *= 1.01;
		    }
		}

		v.añade(importeFinal);
		vuelcaDatos();
		return true;
	}

	/**
	 * Retorna el vendedor con el id indicado
	 * 
	 * @param id Id del vendedor a buscar
	 * @return vendedor con ese id o null si no existe ninguno
	 */
	public Vendedor buscaVendedor(String id) {
		lista = new ArrayList<>();  // Utilizar la interfaz List en lugar de la clase LinkedList
		Scanner in = null;
		try {
			in = new Scanner(new FileReader(datos));
			in.useLocale(Locale.ENGLISH);
			nombre = in.nextLine();
			direccion = in.nextLine();
			in.next();
			Vendedor ven = null;
			while (in.hasNext() && !in.next().equals("Junior")) {
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new VendedorEnPlantilla(nombre, idIn, dni, TipoVendedor.SENIOR);
				ven.setTotalVentas(totalVentas);  // Cambiar el nombre del método a setTotalVentas() 
				lista.add(ven);
			}
			while (in.hasNext() && !in.next().equals("Prácticas")) {  // Corregir la codificación del texto "Prácticas"
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new VendedorEnPlantilla(nombre, idIn, dni, TipoVendedor.JUNIOR);
				ven.setTotalVentas(totalVentas);  // Cambiar el nombre del método a setTotalVentas() 
				lista.add(ven);
			}
			while (in.hasNext()) {
				in.next();
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new VendedorEnPracticas(nombre, idIn, dni);  // Corregir el nombre de la clase vendedorEnPracticas a VendedorEnPracticas
				ven.setTotalVentas(totalVentas);  // Cambiar el nombre del método a setTotalVentas() 
				lista.add(ven);
			}
		} catch (FileNotFoundException e) {
		} finally {
			if (in != null) {
				in.close();
			}
		}
		for (Vendedor v : lista) {
			if (v.getDNI().equals(id)) {
				return v;
			}
		}
		return null;
	}

	/**
	 * Retorna la lista de vendedores actuales de la tienda 
	 * @return La lista de vendedores
	 */
	public List<Vendedor> vendedores() {
		lista = new ArrayList<>();  // Utilizar la interfaz List en lugar de la clase LinkedList
		Scanner in = null;
		try {
			in = new Scanner(new FileReader(datos));
			in.useLocale(Locale.ENGLISH);
			nombre = in.nextLine();
			direccion = in.nextLine();
			in.next();
			Vendedor ven = null;
			while (in.hasNext() && !in.next().equals("Junior")) {
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new VendedorEnPlantilla(nombre, idIn, dni, TipoVendedor.SENIOR);
				ven.setTotalVentas(totalVentas);  // Cambiar el nombre del método a setTotalVentas() 
				lista.add(ven);
			}
			while (in.hasNext() && !in.next().equals("Prácticas")) {  // Corregir la codificación del texto "Prácticas"
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new VendedorEnPlantilla(nombre, idIn, dni, TipoVendedor.JUNIOR);
				ven.setTotalVentas(totalVentas);  // Cambiar el nombre del método a setTotalVentas() 
				lista.add(ven);
			}
			while (in.hasNext()) {
				in.next();
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new VendedorEnPracticas(nombre, idIn, dni);  // Corregir el nombre de la clase vendedorEnPracticas a VendedorEnPracticas
				ven.setTotalVentas(totalVentas);  // Cambiar el nombre del método a setTotalVentas() 
				lista.add(ven);
			}
		} catch (FileNotFoundException e) {
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return lista;
	}

	/**
	 * Método que actualiza el fichero datosTienda.txt 
	 * con los datos actualizados de los vendedores
	 */
	private void vuelcaDatos() throws IOException {
		PrintWriter out = null;
		List<Vendedor> senior = new ArrayList<>();  // Utilizar la interfaz List en lugar de la clase LinkedList
		List<Vendedor> junior = new ArrayList<>();  // Utilizar la interfaz List en lugar de la clase LinkedList
		List<Vendedor> practicas = new ArrayList<>();  // Utilizar la interfaz List en lugar de la clase LinkedList

		for (Vendedor v : lista) {
			if (v instanceof VendedorEnPracticas) {  // Corregir el nombre de la clase vendedorEnPracticas a VendedorEnPracticas
				practicas.add(v);
			} else if (v instanceof VendedorEnPlantilla) {
				VendedorEnPlantilla vp = (VendedorEnPlantilla) v;
				if (vp.getTipo().equals(TipoVendedor.JUNIOR)) {  // Utilizar el método getTipo() en lugar de tipo()
					junior.add(v);
				} else if (vp.getTipo().equals(TipoVendedor.SENIOR)) {  // Utilizar el método getTipo() en lugar de tipo()
					senior.add(v);
				}
			}
		}
		try {
			out = new PrintWriter(new FileWriter(datos));
			out.println(nombre);
			out.println(direccion);
			out.println("Senior");
			for (Vendedor v : senior) {
				out.println(v.getNombre() + " id: " + v.getDNI() + " dni: " + v.getDNI() + " ventas: " + v.getTotalVentas());  // Cambiar el nombre del método a getTotalVentas() 
			}
			out.println("Junior");
			for (Vendedor v : junior) {
				out.println(v.getNombre() + " id: " + v.getDNI() + " dni: " + v.getDNI() + " ventas: " + v.getTotalVentas());  // Cambiar el nombre del método a getTotalVentas() 
			}
			out.println("Prácticas");  // Corregir la codificación del texto "Prácticas"
			for (Vendedor v : practicas) {
				out.println(v.getNombre() + " id: " + v.getDNI() + " dni: " + v.getDNI() + " ventas: " + v.getTotalVentas());  // Cambiar el nombre del método a getTotalVentas() 
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (out != null) {
				out.close();
			}
			lista = new ArrayList<>();  // Utilizar la interfaz List en lugar de la clase LinkedList
		}
	}
}
