package com.uniovi.tests.ejercicios;

import org.junit.Test;

import com.uniovi.services.data.UserList;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

public class Ejercicio03_Tests extends ClasePadre {
	/**
	 * Hacer click en la opción de salir de sesión y comprobar que se redirige a la
	 * página de inicio de sesión (Login).
	 */
	@Test
	public void E03_Prueba_09() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, UserList.usuarios(0).email, UserList.usuarios(0).password);
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		espera();
		PO_View.checkElement(driver, "text", "Identifícate");
		// TODO Cambiar texto a internacionalizado
	}

	/**
	 * Comprobar que el botón cerrar sesión no está visible si el usuario no está
	 * autenticado.
	 */
	@Test
	public void E03_Prueba_10() {
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "logout", PO_View.getTimeout());
	}

}
