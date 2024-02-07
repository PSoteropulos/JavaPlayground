package com.psoteropulos.random.controllers;

import com.psoteropulos.random.models.LoginUser;
import com.psoteropulos.random.models.User;
import com.psoteropulos.random.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

	@Autowired
	UserService userService;


	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "logreg.jsp";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser,
	                       BindingResult result, Model model, HttpSession session) {
		userService.register(newUser, result);
		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "logreg.jsp";
		}
		session.setAttribute("userID", newUser.getId());
		return "redirect:/home";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
	                    BindingResult result, Model model, HttpSession session) {
		User user = userService.login(newLogin, result);
		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "logreg.jsp";
		}
		session.setAttribute("userID", user.getId());
		return "redirect:/home";
	}

	@GetMapping("/users/{id}")
	public String viewOneCommenter(@PathVariable("id") Long commenterId, Model model, HttpSession session) {
		Long userID = (Long) session.getAttribute("userID");
		if (userID == null) {
			return "redirect:/";
		}
		User commenter = userService.findById(commenterId);
		if (commenter == null) {
			return "redirect:/home";
		}
		User loggedUser = userService.findById(userID);
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("commenter", commenter);
		return "userGames.jsp";
	}

	@PostMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
