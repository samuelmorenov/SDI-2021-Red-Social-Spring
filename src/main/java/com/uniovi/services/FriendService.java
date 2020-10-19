package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

@Service
public class FriendService {
	@Autowired
	private UsersRepository usersRepository;


	public Page<User> getFriends(Pageable pageable, User user) {
		return usersRepository.getFriends(pageable, user);
	}
	

}
