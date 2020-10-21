package com.uniovi.services;

import org.springframework.stereotype.Service;

@Service
public class RolesService {
	static String[] roles = { "ROLE_STUDENT", "ROLE_ADMIN" };

	public static String[] getRoles() {
		return roles;
	}
}