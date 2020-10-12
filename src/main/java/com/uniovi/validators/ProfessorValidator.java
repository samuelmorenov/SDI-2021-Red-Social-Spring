package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Professor;

@Component
public class ProfessorValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Professor.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {

//		Professor proffesor = (Professor) target;
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
//		if (proffesor.getDni().length() < 5 || proffesor.getDni().length() > 24) {
//			errors.rejectValue("dni", "Error.signup.dni.length");
//		}
//		if (proffesor.getName().length() < 5 || proffesor.getName().length() > 24) {
//			errors.rejectValue("name", "Error.signup.name.length");
//		}
//		if (proffesor.getLastname().length() < 5 || proffesor.getLastname().length() > 24) {
//			errors.rejectValue("lastName", "Error.signup.lastName.length");
//		}
//		if (proffesor.getCategory().length() < 5 || proffesor.getCategory().length() > 24) {
//			errors.rejectValue("password", "Error.signup.password.length");
//		}
	}
}