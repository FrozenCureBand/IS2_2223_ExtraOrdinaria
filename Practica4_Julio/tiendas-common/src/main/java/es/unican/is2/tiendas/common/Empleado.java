package es.unican.is2.tiendas.common;

import java.time.LocalDate;
import java.time.Period;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Empleado de la tienda, con sus datos personales 
 * y datos de ventas y comisiones
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Empleado")

@XmlAccessorOrder(XmlAccessOrder.UNDEFINED)
public class Empleado {
	
	private String nombre;
	@XmlElement(name="dni")
	private String DNI;
	private Categoria categoria;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	private LocalDate fechaContrato;
	
	private boolean baja = false;
	
	/**
	 * Constructor sin par�metros. 
	 * IMPTE: No borrar aunque se defina otro
	 */
	public Empleado() {
		
	}

	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	
	/**
	 * Retorna el dni del vendedor
	 * @return id
	 */
	public String getDNI() {
		return DNI;
	}
	
	/**
	 * Retorna la categoria del empleado
	 */
	public Categoria getCategoria () {
		return categoria;
	}
	
	/**
	 * Retorna la fecha de contrato
	 */
	public LocalDate getFechaContrato() {
		return fechaContrato;
	}
	
	/**
	 * Retorna si el empleado est� de baja
	 */
	public boolean isBaja() {
		return baja;
	}
	
	/** 
	 * Dar de baja al empleado
	 */
	public void darDeBaja() {
		this.baja=true;
	}
	
	/**
	 * Dar de alta al empleado
	 */
	public void darDeAlta() {
		this.baja=false;
	}
	
	/**
	 * Retorna el sueldo bruto del empleado
	 */
	public double sueldo() {
		double sueldoBase;

	    if (categoria == Categoria.DEPENDIENTE) {
	        sueldoBase = 1000; // Sueldo base para empleados en prácticas
	    } else if (categoria == Categoria.ENCARGADO) {
	        sueldoBase = 1200; // Sueldo base para empleados fijos

	        // Añadir complemento por antigüedad
	        int antiguedad = Period.between(fechaContrato, LocalDate.now()).getYears();
	        if (antiguedad > 5) {
	            sueldoBase += 50;
	        }
	        if (antiguedad > 10) {
	            sueldoBase += 100;
	        }
	        if (antiguedad > 15) {
	            sueldoBase += 150;
	        }
	    } else {
	        sueldoBase = 0; // Categoría no válida, sueldo base igual a 0
	    }

	    // Aplicar reducción por baja
	    if (baja) {
	        sueldoBase *= 0.75; // Reducción del 25%
	    }

	    return sueldoBase;
	}
	
}
