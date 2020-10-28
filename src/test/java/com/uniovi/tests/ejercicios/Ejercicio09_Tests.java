package com.uniovi.tests.ejercicios;

import org.junit.Test;

import com.uniovi.services.data.UserList;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_Invitation;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_View;

public class Ejercicio09_Tests extends BaseTests {
	/**
	 * Mostrar el listado de amigos de un usuario. Comprobar que el listado contiene
	 * los amigos que deben ser.
	 */
	@Test
	public void Prueba_19() {
		String amigo1 = PO_Invitation.enviarPeticionCuentaNueva(driver, 1);
		String amigo2 = PO_Invitation.enviarPeticionCuentaNueva(driver, 1);
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, UserList.usuarios(1).email, UserList.usuarios(1).password);
		PO_PrivateView.accederPagina(driver, "friends-menu", "/friend/invitationlist");
		PO_HomeView.clickId(driver, "acceptButton" + amigo1);
		PO_HomeView.clickId(driver, "acceptButton" + amigo2);
		PO_PrivateView.accederPagina(driver, "friends-menu", "/friend/friendlist");
		PO_View.checkElement(driver, "text", amigo1);
		PO_View.checkElement(driver, "text", amigo2);
	}
}
