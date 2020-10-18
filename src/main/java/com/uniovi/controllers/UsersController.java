package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.User;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {
	@Autowired
	private RolesService rolesService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private SignUpFormValidator signUpFormValidator;

	@Autowired
	private HttpSession httpSession;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup_GET(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup_POST(@Validated User user, BindingResult result, Model model) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		user.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/user/add")
	public String user_add(Model model) {
		model.addAttribute("rolesList", rolesService.getRoles());
		return "user/add";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String user_add_POST(@ModelAttribute User user) {
		usersService.addUser(user);
		return "redirect:/user/list";
	}

	@RequestMapping("/user/details/{id}")
	public String user_details_id(Model model, @PathVariable Long id) {
		model.addAttribute("user", usersService.getUser(id));
		return "user/details";
	}

	@RequestMapping(value = "/user/edit/{id}")
	public String user_edit_id(Model model, @PathVariable Long id) {
		User user = usersService.getUser(id);
		model.addAttribute("user", user);
		return "user/edit";
	}

	@RequestMapping(value = "/user/edit/{id}", method = RequestMethod.POST)
	public String user_edit_id_POST(Model model, @PathVariable Long id, @ModelAttribute User user) {
		user.setId(id);
		usersService.addUser(user);
		return "redirect:/user/details/" + id;
	}

	@RequestMapping("/user/delete/{id}")
	public String user_delete_id(@PathVariable Long id) {
		usersService.deleteUser(id);
		return "redirect:/user/list";
	}

	@RequestMapping("/user/list")
	public String user_list(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		this.list(model, pageable, principal, searchText);
		return "user/list";
	}

// TODO Cambiar index a list o redirigir a login
//	@RequestMapping("/")
//	public String index(Model model, Pageable pageable, Principal principal,
//			@RequestParam(value = "", required = false) String searchText) {
//		this.list(model, pageable, principal, searchText);
//		return "user/list";
//	}
//	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		this.list(model, pageable, principal, searchText);
		return "user/list";
	}

	private void list(Model model, Pageable pageable, Principal principal, String searchText) {
		// Set active user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		httpSession.setAttribute("currentlyUser", activeUser);

		// Paginacion
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		users = usersService.getUsers(pageable);
		model.addAttribute("usersList", users.getContent());
		model.addAttribute("page", users);

//		// Set lista de invitaciones
//		List<User> userList = invitationsService.getUsersWithInvitation(activeUser);
//		model.addAttribute("usersWithInvitation", userList);
	}

}