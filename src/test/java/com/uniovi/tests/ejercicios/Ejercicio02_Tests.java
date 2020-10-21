package com.uniovi.tests.ejercicios;

import org.junit.Test;

import com.uniovi.services.data.UserList;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;

public class Ejercicio02_Tests extends ClasePadre{
	

	/** Inicio de sesión con datos válidos (administrador). */
	@Test
	public void E02_Prueba_05() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, UserList.admin.email, UserList.admin.password);
		PO_RegisterView.checkKey(driver, "list.intro", PO_Properties.getSPANISH());
	}

	/** Inicio de sesión con datos válidos (usuario estándar) */
	@Test
	public void E02_Prueba_06() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, UserList.usuarios(0).email, UserList.usuarios(0).password);
		PO_RegisterView.checkKey(driver, "list.intro", PO_Properties.getSPANISH());
	}

	/**
	 * Inicio de sesión con datos inválidos (usuario estándar, campo email y
	 * contraseña vacíos).
	 */
	@Test
	public void E02_Prueba_07() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "", "");
		espera();
		PO_RegisterView.checkKey(driver, "Error.login", PO_Properties.getSPANISH());
	}

	/**
	 * Inicio de sesión con datos válidos (usuario estándar, email existente, pero
	 * contraseña incorrecta).
	 */
	@Test
	public void E02_Prueba_08() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, UserList.usuarios(0).email, "incorrecta");
		PO_RegisterView.checkKey(driver, "Error.login", PO_Properties.getSPANISH());
	}

}
