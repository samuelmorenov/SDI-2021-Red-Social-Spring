package com.uniovi.tests.ejercicios;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import com.uniovi.tests.DriverSingleton;
import com.uniovi.tests.util.SeleniumUtils;

public class BaseTests {

	static WebDriver driver = DriverSingleton.getDriver();

	private static String URL = "http://localhost:8090";

	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();

	}

	// TO-DO: Comprobar que no se usa y borrar antes de entregar
	@SuppressWarnings("unused")
	protected void espera() {
		espera(3);
	}

	protected void espera(int n) {
		SeleniumUtils.esperarSegundos(driver, n);
	}

	// Para chequear un texto plano:
	// PO_View.checkElement(driver, "text", "Los usuarios que actualmente figuran");
	// Para chequear un elemento internacionalizado:
	// PO_RegisterView.checkKey(driver, "list.intro", PO_Properties.getSPANISH());
}
