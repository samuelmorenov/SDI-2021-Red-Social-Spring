package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);

	@Query("SELECT r FROM User r WHERE (LOWER(r.email) LIKE LOWER(?1) OR LOWER(r.name) LIKE LOWER(?1) OR LOWER(r.lastName) LIKE LOWER(?1)and r.email <> ?2)")
	Page<User> searchByNameEmailAndLastname(Pageable pageable, String searchText, String email);

	@Query("SELECT u FROM User u where u.email <> 'admin@email.com' and u.email <> ?1")
	Page<User> findRegularUsers(Pageable pageable, String email);

	@Query("SELECT p.amigos FROM User p WHERE p = ?1")
	Page<User> getFriends(Pageable pageable, User user);

}