package com.uniovi.services.data;

import com.uniovi.services.RolesService;

public class UserList {

	public final static UserDto[] usuarios = getUsuarios();

	private static UserDto[] getUsuarios() {
		RolesService rolesService = new RolesService();
		UserDto[] list = new UserDto[5];
		list[0] = new UserDto("pedro@email.com", "Pedro", "Díaz", "123456", rolesService.getRoles()[0]);
		list[1] = new UserDto("lucas@email.com", "Lucas", "Núñez", "123456", rolesService.getRoles()[0]);
		list[2] = new UserDto("maria@email.com", "María", "Rodríguez", "123456", rolesService.getRoles()[0]);
		list[3] = new UserDto("marta@email.com", "Marta", "Almonte", "123456", rolesService.getRoles()[0]);
		list[4] = new UserDto("pelayo@email.com", "Pelayo", "Valdes", "123456", rolesService.getRoles()[0]);
		return list;
	}

	public final static UserDto[] usuariosTest = getUsuariosTest();

	private static UserDto[] getUsuariosTest() {
		RolesService rolesService = new RolesService();
		UserDto[] list = new UserDto[5];
		list[0] = new UserDto("ana@email.com", "Ana", "Gomez", "123456", rolesService.getRoles()[0]);
		list[1] = new UserDto("flor@email.com", "Flor", "Azul", "123456", rolesService.getRoles()[0]);

		return list;
	}

	public final static UserDto[] admins = getAdmins();

	private static UserDto[] getAdmins() {
		RolesService rolesService = new RolesService();
		UserDto[] list = new UserDto[1];
		list[0] = new UserDto("edward@email.com", "Edward", "Núñez", "123456", rolesService.getRoles()[1]);
		return list;
	}

	public final static UserDto[] adminTest = getAdminTest();

	private static UserDto[] getAdminTest() {
		RolesService rolesService = new RolesService();
		UserDto[] list = new UserDto[1];
		list[0] = new UserDto("admin@email.com", "Admin", "Admin", "adminc", rolesService.getRoles()[1]);
		return list;
	}

}
