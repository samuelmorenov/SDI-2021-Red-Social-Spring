package com.uniovi.tests.ejercicios;

import org.junit.Test;

import com.uniovi.services.data.UserList;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_View;

public class Ejercicio04_Tests extends ClasePadre{
	/**
	 * Mostrar el listado de usuarios y comprobar que se muestran todos los que
	 * existen en el sistema.
	 */
	@Test
	public void E04_Prueba_11() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, UserList.usuariosTest(0).email, UserList.usuariosTest(0).password);
		PO_View.checkElement(driver, "text", UserList.usuarios(0).email);
		PO_View.checkElement(driver, "text", UserList.usuarios(1).email);
		PO_View.checkElement(driver, "text", UserList.usuarios(2).email);
		PO_View.checkElement(driver, "text", UserList.usuarios(3).email);
		PO_View.checkElement(driver, "text", UserList.usuarios(4).email);

		// TODO Comprobar paginacion
	}
}
