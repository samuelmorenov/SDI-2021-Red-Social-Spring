package com.uniovi.tests;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MySocialNetwork_Tests {
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioenes
	// automáticas):
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = ".\\lib\\geckodriver024win64.exe";
	// Comun:
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	private String admin = "admin@email.com";
	private String adminc = "admin";
	private String user = "ana@email.com";
	private String userc = "123456";
//	private String user2 = "maria@email.com";
//	private String userc2 = "123456";

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

	private String correoRandom() {
		return "correo" + Integer.toString((int) (100000 * Math.random())) + "@email.es";
	}

	//TO-DO: Comprobar que no se usa y borrar antes de entregar
	@SuppressWarnings("unused") 
	private void espera() {
		espera(3);
	}

	//TO-DO: Comprobar que no se usa y borrar antes de entregar
	@SuppressWarnings("unused") 
	private void espera(int n) {
		SeleniumUtils.esperarSegundos(driver, n);
	}

	// Para chequear un texto plano:
	// PO_View.checkElement(driver, "text", "Los usuarios que actualmente figuran");
	// Para chequear un elemento internacionalizado:
	// PO_RegisterView.checkKey(driver, "list.intro", PO_Properties.getSPANISH());

	/** Registro de Usuario con datos válidos. */
	@Test
	public void E01_Prueba_01() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, correoRandom(), "Josefo", "Perez", "77777", "77777");
		//TODO: Comprobar que despues de loguear va a donde tiene que ir
		//PO_RegisterView.checkKey(driver, "list.intro", PO_Properties.getSPANISH());
	}

	/** Registro de Usuario con datos inválidos: email vacío */
	@Test
	public void E01_Prueba_02_1() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "", "Josefo", "Perez", "77777", "77777");
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
	}

	/** Registro de Usuario con datos inválidos: nombre vacío */
	@Test
	public void E01_Prueba_02_2() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, correoRandom(), "", "Perez", "77777", "77777");
		PO_RegisterView.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH());
	}

	/** Registro de Usuario con datos inválidos: apellidos vacío */
	@Test
	public void E01_Prueba_02_3() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, correoRandom(), "Josefo", " ", "77777", "77777");
		PO_RegisterView.checkKey(driver, "Error.signup.lastName.length", PO_Properties.getSPANISH());
	}

	/**
	 * Registro de Usuario con datos inválidos: repetición de contraseña inválida
	 */
	@Test
	public void E01_Prueba_03() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, correoRandom(), "Josefo", "Perez", "77777", "77776");
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
	
	}

	/** Registro de Usuario con datos inválidos: email existente */
	@Test
	public void E01_Prueba_04() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, user, "Josefo", "Perez", "77777", "77777");
		PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH());
	}

	/** Inicio de sesión con datos válidos (administrador). */
	@Test
	public void E02_Prueba_05() {
		//TODO in progress...
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, admin, adminc);
		PO_RegisterView.checkKey(driver, "list.intro", PO_Properties.getSPANISH());
	}

	/** Inicio de sesión con datos válidos (usuario estándar) */
	@Test
	public void E02_Prueba_06() {
		//TODO in progress...
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, user, userc);
		PO_RegisterView.checkKey(driver, "list.intro", PO_Properties.getSPANISH());
	}

	/**
	 * Inicio de sesión con datos inválidos (usuario estándar, campo email y
	 * contraseña vacíos).
	 */
	@Test
	public void E02_Prueba_07() {
		//TODO in progress...
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "", "");
		PO_RegisterView.checkKey(driver, "Error.login", PO_Properties.getSPANISH());
	}

	/**
	 * Inicio de sesión con datos válidos (usuario estándar, email existente, pero
	 * contraseña incorrecta).
	 */
	@Test
	public void E02_Prueba_08() {
		//TODO in progress...
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, user, "incorrecta");
		PO_RegisterView.checkKey(driver, "Error.login", PO_Properties.getSPANISH());
	}

	/**
	 * Hacer click en la opción de salir de sesión y comprobar que se redirige a la
	 * página de inicio de sesión (Login).
	 */
	@Test
	public void E03_Prueba_09() {
		fail("Not yet implemented");
	}

	/**
	 * Comprobar que el botón cerrar sesión no está visible si el usuario no está
	 * autenticado.
	 */
	@Test
	public void E03_Prueba_10() {
		fail("Not yet implemented");
	}

	/**
	 * Mostrar el listado de usuarios y comprobar que se muestran todos los que
	 * existen en el sistema.
	 */
	@Test
	public void E04_Prueba_11() {
		fail("Not yet implemented");
	}

	/**
	 * Hacer una búsqueda con el campo vacío y comprobar que se muestra la página
	 * que corresponde con el listado usuarios existentes en el sistema.
	 */
	@Test
	public void E05_Prueba_12() {
		fail("Not yet implemented");
	}

	/**
	 * Hacer una búsqueda escribiendo en el campo un texto que no exista y comprobar
	 * que se muestra la página que corresponde, con la lista de usuarios vacía.
	 */
	@Test
	public void E05_Prueba_13() {
		fail("Not yet implemented");
	}

	/**
	 * Hacer una búsqueda con un texto específico y comprobar que se muestra la
	 * página que corresponde, con la lista de usuarios en los que el texto
	 * especificados sea parte de su nombre, apellidos o de su email.
	 */
	@Test
	public void E05_Prueba_14() {
		fail("Not yet implemented");
	}

	/**
	 * Desde el listado de usuarios de la aplicación, enviar una invitación de
	 * amistad a un usuario. Comprobar que la solicitud de amistad aparece en el
	 * listado de invitaciones (punto siguiente).
	 */
	@Test
	public void E06_Prueba_15() {
		fail("Not yet implemented");
	}

	/**
	 * Desde el listado de usuarios de la aplicación, enviar una invitación de
	 * amistad a un usuario al que ya le habíamos enviado la invitación previamente.
	 * No debería dejarnos enviar la invitación, se podría ocultar el botón de
	 * enviar invitación o notificar que ya había sido enviada previamente.
	 */
	@Test
	public void E06_Prueba_16() {
		fail("Not yet implemented");
	}

	/**
	 * Mostrar el listado de invitaciones de amistad recibidas. Comprobar con un
	 * listado que contenga varias invitaciones recibidas.
	 */
	@Test
	public void E07_Prueba_17() {
		fail("Not yet implemented");
	}

	/**
	 * Sobre el listado de invitaciones recibidas. Hacer click en el botón/enlace de
	 * una de ellas y comprobar que dicha solicitud desaparece del listado de
	 * invitaciones.
	 */
	@Test
	public void E08_Prueba_18() {
		fail("Not yet implemented");
	}

	/**
	 * Mostrar el listado de amigos de un usuario. Comprobar que el listado contiene
	 * los amigos que deben ser.
	 */
	@Test
	public void E09_Prueba_19() {
		fail("Not yet implemented");
	}

	/**
	 * Visualizar al menos cuatro páginas en Español/Inglés/Español (comprobando que
	 * algunas de las etiquetas cambian al idioma correspondiente). Ejemplo, Página
	 * principal/Opciones Principales de Usuario/Listado de Usuarios.
	 */
	@Test
	public void E10_Prueba_20() {
		fail("Not yet implemented");

	}

	/**
	 * Intentar acceder sin estar autenticado a la opción de listado de usuarios. Se
	 * deberá volver al formulario de login.
	 */
	@Test
	public void E11_Prueba_21() {
		fail("Not yet implemented");
	}

	/**
	 * Intentar acceder sin estar autenticado a la opción de listado de invitaciones
	 * de amistad recibida de un usuario estándar. Se deberá volver al formulario de
	 * login.
	 */
	@Test
	public void E11_Prueba_22() {
		fail("Not yet implemented");
	}

	/**
	 * Estando autenticado como usuario estándar intentar acceder a una opción
	 * disponible solo para usuarios administradores (Se puede añadir una opción
	 * cualquiera en el menú). Se deberá indicar un mensaje de acción prohibida.
	 */
	@Test
	public void E11_Prueba_23() {
		fail("Not yet implemented");
	}

}
