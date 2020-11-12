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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Invitation;
import com.uniovi.entities.User;
import com.uniovi.services.FriendService;
import com.uniovi.services.InvitationsService;
import com.uniovi.services.UsersService;

@Controller
public class FriendController {

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private UsersService usersService;

	@Autowired
	private FriendService friendService;

	@Autowired
	private InvitationsService invitationsService;

	@RequestMapping("/friend/invitationlist")
	public String friend_invitationlist(Model model, Pageable pageable, Principal principal) {
		// Set active user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		httpSession.setAttribute("currentlyUser", activeUser);

		// Paginacion
		Page<Invitation> invitations = new PageImpl<Invitation>(new LinkedList<Invitation>());
		invitations = invitationsService.getInvitationsReceived(pageable);
		model.addAttribute("invitationList", invitations.getContent());
		model.addAttribute("page", invitations);

		return "friend/invitationlist";
	}

	@RequestMapping(value = "/friend/accept", method = RequestMethod.POST)
	public String friend_accept_POST(@RequestParam Long InvitationId, Model model, Principal principal) {

		Invitation invitation = invitationsService.getInvitationById(InvitationId);
		invitation.setAceptada(true);
		invitationsService.update(invitation);

		User emisor = invitation.getEmisor();
		User receptor = invitation.getReceptor();

		emisor.getAmigos().add(receptor);
		receptor.getAmigos().add(emisor);

		usersService.update(emisor);
		usersService.update(receptor);

		return "redirect:/friend/invitationlist";
	}

	@RequestMapping(value = { "/friend/friendlist" }, method = RequestMethod.GET)
	public String friend_friendlist(Model model, Pageable pageable, Principal principal) {

		// Set active user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		httpSession.setAttribute("currentlyUser", activeUser);

		// Paginacion
		Page<User> amigos = new PageImpl<User>(new LinkedList<User>());
		amigos = friendService.getFriends(pageable, activeUser);
		model.addAttribute("friendList", amigos.getContent());
		model.addAttribute("page", amigos);

		return "/friend/friendlist";

	}
}