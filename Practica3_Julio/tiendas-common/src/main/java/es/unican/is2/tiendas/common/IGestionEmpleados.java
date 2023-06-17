package es.unican.is2.tiendas.common;



/**
 * Interfaz de negocio para gestionar empleados
 */
public interface IGestionEmpleados {
	
	/**
	 * A�ade un nuevo empleado a una tienda
	 * @param e Empleado que se quiere a�adir
	 * @param nombre Nombre de la tienda
	 * @return El empleado a�adido
	 * 		   null si no se anhade porque no se encuentra la tienda
	 * @throws OperacionNoValida si el empleado ya existe
	 */
	public Empleado altaEmpleado(Empleado e, String nombre) throws OperacionNoValida;
	
	/**
	 * Elimina un empleado de una tienda 
	 * @param dni DNI del empleado
	 * @param nombre Nombre de la tienda
 	 * @return El empleado eliminado
 	 *         null si el empleado o la tienda no existen
 	 * @throws OperacionNoValida si el empleado no pertenece a la tienda indicada
	 */
	public Empleado bajaEmpleado(String dni, String nombre) throws OperacionNoValida;
	
	/**
	 * Retorna el empleado con el dni indicado
	 * @param dni
	 * @return El empleado con el dni indicado
	 *         null si no existe
	 */
	public Empleado empleado(String dni);

}
