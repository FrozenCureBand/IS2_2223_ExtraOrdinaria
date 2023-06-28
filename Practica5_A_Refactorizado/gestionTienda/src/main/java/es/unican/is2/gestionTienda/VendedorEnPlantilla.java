package es.unican.is2.gestionTienda;


public class VendedorEnPlantilla extends Vendedor {
	
	private TipoVendedor tipo;
	private String dni;
	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre
	 * @param dni
	 * @param tipo
	 */
	
	/**
	 * WMC: 4 (Se calcula contando el número de métodos en la clase VendedorEnPlantilla, en este caso: VendedorEnPlantilla, tipo, getDni, equals)
	 * WMCn: 0 (No hay métodos sobrescritos en las subclases de VendedorEnPlantilla)
	 * CBO: 1 (La clase VendedorEnPlantilla está acoplada directamente a la clase Vendedor)
	 * CCog: 1 (La clase VendedorEnPlantilla está acoplada indirectamente a través del método equals con la clase Object)
	 */

	public VendedorEnPlantilla(String nombre, String id, String dni, TipoVendedor tipo) {
		super(nombre, id);
		this.tipo = tipo;
		this.dni=dni;
	}
	
	public TipoVendedor getTipo() {
		return tipo;
	}
	
	public String getDNI() {
		return dni;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof VendedorEnPlantilla)) 
			return false;
		VendedorEnPlantilla v = (VendedorEnPlantilla) obj;
		return (v.getDNI().equals(getDNI()) && v.getDNI().equals(getDNI()));
	}
}
