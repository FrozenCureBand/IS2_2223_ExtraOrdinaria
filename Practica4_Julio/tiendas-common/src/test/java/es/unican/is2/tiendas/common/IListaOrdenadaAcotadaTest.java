package es.unican.is2.tiendas.common;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Test;

public class IListaOrdenadaAcotadaTest{

	@Test
	public final void testTamañoLista() {
		ListaOrdenadaAcotada<String> loa = new ListaOrdenadaAcotada<String>();
		//Añadimos 5 elementos
		
		for (int i = 0; i < 5; i++) {
			loa.add("A");
		}
		
		int tamañolista = loa.size();
		assertEquals(5, tamañolista);
	}
	
	@Test
	public final void testOverFlowLista() {
		ListaOrdenadaAcotada<String> loa = new ListaOrdenadaAcotada<String>();
		//Max Instanciado es de 10 elementos
		//Añadimos 11 para para ocupar mas de su tamaño
		try {
			for (int i = 0; i < 11; i++) {
				loa.add("A");
			}
	         fail("Should have thrown an exception");
	     } catch (final RuntimeException e) {
	         assertTrue(true);
	     }
	   } 
		
}
	

