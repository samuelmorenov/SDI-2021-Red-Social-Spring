package com.uniovi.validators;

import com.uniovi.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class LoginFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {

		//User user = (User) target;
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.empty");
//		if (user.getEmail().length() < 5 || user.getEmail().length() > 24) {
//			errors.rejectValue("email", "Error.signup.email.length");
//		}
		
//		if (usersService.getUserByEmail(user.getEmail()) != null) { //TODO cambiar a == y el mensaje
//			errors.rejectValue("email", "Error.signup.email.duplicate");
//		}

		// if (usersService.getUserByEmail(user.getEmail()) == null) {
		// errors.rejectValue("email", "Error.login");
		// }

		// if (!usersService.ge.equals(user.getPassword())) {
		// errors.rejectValue("passwordConfirm",
		// "Error.signup.passwordConfirm.noexist");
		// }
	}
}