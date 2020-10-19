package com.uniovi.services;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Invitation;
import com.uniovi.entities.User;
import com.uniovi.repositories.InvitationsRepository;
import com.uniovi.repositories.UsersRepository;

@Service
public class InvitationsService {
	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private InvitationsRepository invitationsRepository;

	@Autowired
	private HttpSession httpSession;

	public void sendInvitation(User emisor, Long receptorId) {
		User receptor = usersRepository.findById(receptorId).get();
		Invitation invitacion = new Invitation(emisor, receptor, false);
		invitationsRepository.save(invitacion);
	}

	public List<User> getUsersWithInvitation(User usuarioActual) {
		List<User> usersList = new LinkedList<User>();

		for (Invitation i : invitationsRepository.findAllSentByUser(usuarioActual))
			usersList.add(i.getReceptor());

		return usersList;
	}

	public Page<Invitation> getInvitationsReceived(Pageable pageable) {
		User activeUser = (User) httpSession.getAttribute("currentlyUser");
		Page<Invitation> invitations = invitationsRepository.findAllReceivedByUser(pageable, activeUser);
		return invitations;
	}

	public Invitation getInvitationById(Long invitationId) {
		return invitationsRepository.findById(invitationId).get();
	}

	public void update(Invitation invitation) {
		invitationsRepository.save(invitation);
	}
}
