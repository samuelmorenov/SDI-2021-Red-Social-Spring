package com.uniovi.tests;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_RegisterProffesor;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Sdi1920613LabSpringApplicationTests {
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioenes
	// automáticas):
	static String PathFirefox65 = Config.getPathFirefox65();
	static String Geckdriver024 = Config.getGeckdriver024();
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

	// [Prueba1] Registro de profesores con datos válidos.
	@Test
	public void Prueba1() {
		PO_PrivateView.login(driver, "99999988F", "123456", "99999988F");
		List<WebElement> elementos = null;
		PO_PrivateView.accederPagina(driver, "professors-menu", "professor/add", elementos);
		PO_RegisterProffesor.fillForm(driver, "99999990A", "Josefo", "Perez", "Categoria_01");
		PO_View.checkElement(driver, "text", "Profesores");
	}
	

	@Test
	public void Prueba2() {
		PO_PrivateView.login(driver, "99999988F", "123456", "99999988F");
		List<WebElement> elementos = null;
		PO_PrivateView.accederPagina(driver, "professors-menu", "professor/add", elementos);
		PO_RegisterProffesor.fillForm(driver, "99999990A", "Jo", "Perez", "Categoria_01");
	}
	
	@Test
	public void Prueba3() {
		PO_PrivateView.login(driver, "99999990A", "123456", "99999990A");
		List<WebElement> elementos = null;
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, '" + "professors-menu" + "')]/a");
		elementos.get(0).click();
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Agregar Profesor", 3);
		
		
	}

}
