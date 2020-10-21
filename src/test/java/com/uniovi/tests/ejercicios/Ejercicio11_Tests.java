package com.uniovi.tests.ejercicios;

import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.fail;

import org.junit.FixMethodOrder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Ejercicio11_Tests extends Test_Config {
	/**
	 * Intentar acceder sin estar autenticado a la opción de listado de usuarios. Se
	 * deberá volver al formulario de login.
	 */
	@Test
	public void Prueba_21() {
		fail("Not yet implemented");
	}

	/**
	 * Intentar acceder sin estar autenticado a la opción de listado de invitaciones
	 * de amistad recibida de un usuario estándar. Se deberá volver al formulario de
	 * login.
	 */
	@Test
	public void Prueba_22() {
		fail("Not yet implemented");
	}

	/**
	 * Estando autenticado como usuario estándar intentar acceder a una opción
	 * disponible solo para usuarios administradores (Se puede añadir una opción
	 * cualquiera en el menú). Se deberá indicar un mensaje de acción prohibida.
	 */
	@Test
	public void Prueba_23() {
		fail("Not yet implemented");
	}
}
