package es.unican.is2.tiendas.common;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Test;

public class IListaOrdenadaAcotadaTest{

	@Test
	public final void testTamañoLista() {
		ListaOrdenadaAcotada<String> loa = new ListaOrdenadaAcotada<String>();
		//Añadimos 5 elementos
		
		loa.add("A");
		loa.add("A");
		loa.add("A");
		loa.add("A");
		loa.add("A");
		
		int tamañolista = loa.size();
		assertEquals(5, tamañolista);
	}
	
	@Test
	public final void testOverFlowLista() {
		ListaOrdenadaAcotada<String> loa = new ListaOrdenadaAcotada<String>();
		//Max Instanciado es de 10 elementos
		//Añadimos 11 para para ocupar mas de su tamaño
		int tamañolista = loa.size();
		
		try {
            loa.add("A");
	 		loa.add("A");
			loa.add("A");
			loa.add("A");
			loa.add("A");
			loa.add("A");
			loa.add("A");
			loa.add("A");
			loa.add("A");
			loa.add("A");
			loa.add("A");
	         fail("Should have thrown an exception");
	     } catch (final RuntimeException e) {
	         assertTrue(true);
	     }
	   } 
		
	}
	

