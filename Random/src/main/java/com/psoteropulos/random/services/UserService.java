package com.psoteropulos.random.services;

import com.psoteropulos.random.models.LoginUser;
import com.psoteropulos.random.models.User;
import com.psoteropulos.random.repositories.UserRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public User register(User newUser, BindingResult result) {
		Optional<User> possibleUser = userRepo.findByEmail(newUser.getEmail());
		if (possibleUser.isPresent()) {
			result.rejectValue("email", "Matches", "An account with that email address is already registered. Please log in.");
		}
		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "Confirm Password must match Password.");
		}
		if (result.hasErrors()) {
			return null;
		}
		String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashedPassword);
		return userRepo.save(newUser);
	}

	public User login(LoginUser newLogin, BindingResult result) {
		Optional<User> possibleUser = userRepo.findByEmail(newLogin.getEmail());
		if (!possibleUser.isPresent()) {
			result.rejectValue("email", "Matches", "An account with this email does not exist. Please register.");
			return null;
		}
		User foundUser = possibleUser.get();
		if (!BCrypt.checkpw(newLogin.getPassword(), foundUser.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid login credentials.");
		}
		if (result.hasErrors()) {
			return null;
		}
		return foundUser;
	}

	public User findById(Long id) {
		Optional<User> possibleUser = userRepo.findById(id);
		if (possibleUser.isPresent()) {
			return possibleUser.get();
		}
		return null;
	}

	public User update(User updatedUser) {
		return userRepo.save(updatedUser);
	}

	public void delete(Long id) {
		userRepo.deleteById(id);
	}
}
