package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void init() {
	}

	@Autowired
	private HttpSession httpSession;

	public Page<User> getUsers(Pageable pageable) {

		User activeUser = (User) httpSession.getAttribute("currentlyUser");
		Page<User> users = usersRepository.findOthersUsersWithNoThisRole(pageable, activeUser.getEmail(),
				RolesService.getRoles()[1]);

		return users;
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}

	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}

	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}

	public Page<User> searchByNameAndLastname(Pageable pageable, String searchText) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		searchText = "%" + searchText + "%";
		User activeUser = (User) httpSession.getAttribute("currentlyUser");

		users = usersRepository.searchByNameEmailAndLastname(pageable, searchText, activeUser.getEmail());

		return users;
	}

	public void update(User user) {
		usersRepository.save(user);
	}

}
