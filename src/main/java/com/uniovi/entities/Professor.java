package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Professor {

	@Id
	@GeneratedValue
	private Long id;
	private String dni;
	private String name;
	private String lastname;
	private String category;

	public Professor() {

	}

	public Professor(Long id, String dni, String name, String lastname, String category) {
		super();
		this.id = id;
		this.dni = dni;
		this.name = name;
		this.lastname = lastname;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", dni=" + dni + ", name=" + name + ", lastname=" + lastname + ", category="
				+ category + "]";
	}

}
