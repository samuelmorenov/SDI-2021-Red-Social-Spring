package com.uniovi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

public class PO_Invitation extends PO_NavView {

	private static String user = "pedro@email.com";
	private static String userc = "123456";
	private static String user2 = "maria@email.com";
	private static String userc2 = "123456";

	public static void enviarPeticiones(WebDriver driver) {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, user, userc);
		PO_HomeView.clickId(driver, "sendButton2");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}
	public static void enviarPeticiones2(WebDriver driver) {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, user2, userc2);
		PO_HomeView.clickId(driver, "sendButton2");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}
	
}
