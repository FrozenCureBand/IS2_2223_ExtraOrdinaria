package es.unican.is2.gestionTienda;


public class VendedorEnPracticas extends Vendedor {
	
	private String dni;
	
	/**
	 * Retorna un nuevo vendedor en prcticas
	 * @param nombre
	 * @param dni
	 */
	
	/**
	 * WMC: 3 (Se calcula contando el número de métodos en la clase vendedorEnPracticas, en este caso: vendedorEnPracticas, getDni y equals)
	 * WMCn: 0 (No hay métodos sobrescritos en las subclases de vendedorEnPracticas)
	 * CBO: 1 (La clase vendedorEnPracticas está acoplada directamente a la clase Vendedor)
	 * CCog: 1 (La clase vendedorEnPracticas está acoplada indirectamente a través del método equals con la clase Object)
	 */

	public VendedorEnPracticas(String nombre, String id, String dni) {
		super(nombre, id);
		this.dni= dni;
	}
	
	public String getDni() {
		return dni;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof VendedorEnPracticas)) 
			return false;
		VendedorEnPracticas v = (VendedorEnPracticas) obj;
		return (v.getDNI().equals(getDNI()) && v.getDni().equals(getDni()));
	}
}
