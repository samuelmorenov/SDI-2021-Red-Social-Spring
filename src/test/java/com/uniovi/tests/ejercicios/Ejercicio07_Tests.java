package com.uniovi.tests.ejercicios;

import org.junit.Test;

import com.uniovi.services.data.UserList;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_Invitation;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_View;

public class Ejercicio07_Tests extends Test_Config {

	/**
	 * Mostrar el listado de invitaciones de amistad recibidas. Comprobar con un
	 * listado que contenga varias invitaciones recibidas.
	 */
	@Test
	public void Prueba_17() {
		PO_Invitation.enviarPeticiones(driver);
		PO_Invitation.enviarPeticiones2(driver);
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, UserList.usuariosTest(1).email, UserList.usuariosTest(1).password);
		PO_PrivateView.accederPagina(driver, "friends-menu", "/friend/invitationlist");
		PO_View.checkElement(driver, "text", "Pedro");
		PO_View.checkElement(driver, "text", "María");
	}
}
