package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true)
	private String email;
	private String name;
	private String lastName;

	private String role;

	private String password;
	@Transient // propiedad que no se almacena e la tabla.
	private String passwordConfirm;
	
	@OneToMany(mappedBy = "receptor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Invitation> recibidas;
	
	@OneToMany(mappedBy = "emisor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Invitation> emitidas;
	
	@ManyToMany()
	private Set<User> amigos;

	public User(String email, String name, String lastName) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return this.name + " " + this.lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	Set<Invitation> _getRecibidas() {
		return recibidas;
	}
	
	public Set<Invitation> getRecibidas() {
		return new HashSet<Invitation>(recibidas);
	}

	public void setRecibidas(Set<Invitation> recibidas) {
		this.recibidas = recibidas;
	}

	Set<Invitation> _getEmitidas() {
		return emitidas;
	}
	
	public Set<Invitation> getEmitidas() {
		return new HashSet<Invitation>(emitidas);
	}

	public void setEmitidas(Set<Invitation> emitidas) {
		this.emitidas = emitidas;
	}

	public Set<User> getAmigos() {
		return amigos;
	}

	public void setAmigos(Set<User> amigos) {
		this.amigos = amigos;
	}
	
	

}