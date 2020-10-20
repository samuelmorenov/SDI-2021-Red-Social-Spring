package com.uniovi.services.data;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

// DONE: Para crear la base de datos de nuevo descomentar esta clase
@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;

	@PostConstruct
	public void init() {
		
		for (int i = 0; i < UserList.usuarios.length; i++) {
			User user = new User(UserList.usuarios[i].email, UserList.usuarios[i].name, UserList.usuarios[i].lastName);
			user.setPassword(UserList.usuarios[i].password);
			user.setRole(UserList.usuarios[i].role);
			usersService.addUser(user);
		}
		for (int i = 0; i < UserList.admins.length; i++) {
			User user = new User(UserList.admins[i].email, UserList.admins[i].name, UserList.admins[i].lastName);
			user.setPassword(UserList.admins[i].password);
			user.setRole(UserList.admins[i].role);
			usersService.addUser(user);
		}
		
		


	}
}