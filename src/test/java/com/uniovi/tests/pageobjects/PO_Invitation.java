package com.uniovi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

import com.uniovi.services.data.UserList;

public class PO_Invitation extends PO_NavView {

	public static void enviarPeticion(WebDriver driver, int user1, int user2) {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, UserList.usuarios(user1).email, UserList.usuarios(user1).password);
		int correccionDeId = user2 + 1;
		PO_HomeView.clickId(driver, "sendButton" + correccionDeId);
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	public static String enviarPeticionCuentaNueva(WebDriver driver, int user) {
		String name = Integer.toString((int) (100000 * Math.random()));
		String password = UserList.usuariosTest(0).password;

		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, name+"@email.com", name, name, password, password);
		int correccionDeId = user + 1;
		PO_HomeView.clickId(driver, "sendButton" + correccionDeId);
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		return name;
	}
}
