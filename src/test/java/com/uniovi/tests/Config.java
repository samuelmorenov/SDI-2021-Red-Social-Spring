package com.uniovi.tests;

public class Config {
	static private boolean enCasa = true;

	public static String getGeckdriver024() {
		String uni = "C:\\Users\\UO266321\\Desktop\\Workspace Spring Boot\\geckodriver024win64.exe";
		String casa = "D:\\Uniovi\\SDI\\geckodriver024win64.exe";
		return enCasa ? casa : uni;
	}

	public static String getPathFirefox65() {
		String uni = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
		String casa = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
		return enCasa ? casa : uni;
	}

}
