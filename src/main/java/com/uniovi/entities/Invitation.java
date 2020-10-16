package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Invitation {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	//@JoinColumn(name = "emisor_id")
	private User emisor;
	
	@ManyToOne
	//@JoinColumn(name = "user_id")
	private User receptor;
	
	private boolean aceptada;

	Invitation(){}


	public Invitation(User emisor, User receptor, boolean aceptada) {
		super();
		this.emisor = emisor;
		this.receptor = receptor;
		this.aceptada = aceptada;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getEmisor() {
		return emisor;
	}

	public void setEmisor(User emisor) {
		this.emisor = emisor;
	}

	public User getReceptor() {
		return receptor;
	}

	public void setReceptor(User receptor) {
		this.receptor = receptor;
	}

	public boolean isAceptada() {
		return aceptada;
	}

	public void setAceptada(boolean aceptada) {
		this.aceptada = aceptada;
	}
	
	

}
