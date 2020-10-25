package com.uniovi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

import com.uniovi.services.data.UserDto;

public class PO_Invitation extends PO_NavView {

	public static void enviarPeticiones(WebDriver driver, UserDto user, String buttonName) {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, user.email, user.password);
		PO_HomeView.clickId(driver, buttonName);
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}
}
