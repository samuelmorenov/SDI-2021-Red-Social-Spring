package com.uniovi.tests.ejercicios;

import org.junit.Test;

import com.uniovi.services.data.UserList;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_Search;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

public class Ejercicio05_Tests extends Test_Config{


	/**
	 * Hacer una búsqueda con el campo vacío y comprobar que se muestra la página
	 * que corresponde con el listado usuarios existentes en el sistema.
	 */
	@Test
	public void E05_Prueba_12() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, UserList.usuariosTest(0).email, UserList.usuariosTest(0).password);
		PO_Search.search(driver, "");
		PO_View.checkElement(driver, "text", "lucas@email.com");
		PO_View.checkElement(driver, "text", "maria@email.com");
		PO_View.checkElement(driver, "text", "marta@email.com");
		PO_View.checkElement(driver, "text", "pelayo@email.com");
		PO_View.checkElement(driver, "text", "k@email.com");
	}

	/**
	 * Hacer una búsqueda escribiendo en el campo un texto que no exista y comprobar
	 * que se muestra la página que corresponde, con la lista de usuarios vacía.
	 */
	@Test
	public void E05_Prueba_13() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, UserList.usuariosTest(0).email, UserList.usuariosTest(0).password);
		PO_Search.search(driver, "pepe");
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "@email", PO_View.getTimeout());
	}

	/**
	 * Hacer una búsqueda con un texto específico y comprobar que se muestra la
	 * página que corresponde, con la lista de usuarios en los que el texto
	 * especificados sea parte de su nombre, apellidos o de su email.
	 */
	@Test
	public void E05_Prueba_14() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, UserList.usuariosTest(0).email, UserList.usuariosTest(0).password);
		PO_Search.search(driver, "ma");
		PO_View.checkElement(driver, "text", "maria@email.com");
		PO_View.checkElement(driver, "text", "marta@email.com");
	}
}
