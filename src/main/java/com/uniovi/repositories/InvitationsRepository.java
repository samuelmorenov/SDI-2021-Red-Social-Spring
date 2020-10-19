package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Invitation;
import com.uniovi.entities.User;

public interface InvitationsRepository extends CrudRepository<Invitation, Long> {

	@Query("SELECT p FROM Invitation p WHERE p.emisor = ?1")
	List<Invitation> findAllSentByUser(User usuarioActual);
	
	@Query("SELECT p FROM Invitation p WHERE p.receptor = ?1 AND p.aceptada = false")
	Page<Invitation> findAllReceivedByUser(Pageable pageable, User usuarioActual);
	
	@Query("SELECT p FROM Invitation p WHERE p.emisor = ?1 AND p.receptor.id = ?2")
	Invitation getInvitation(User activeUser, Long invitationId);

	
}
