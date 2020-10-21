package com.uniovi.tests.ejercicios;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.util.SeleniumUtils;

public class ClasePadre {
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioenes
	// automáticas):
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = ".\\lib\\geckodriver024win64.exe";
	// Comun:
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

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

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	protected String correoRandom() {
		return "correo" + Integer.toString((int) (100000 * Math.random())) + "@email.es";
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
