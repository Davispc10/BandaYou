package com.algaworks.bandayou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class BandaYouController {
	@RequestMapping("")
	public String home(){
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Usuário e/ou senha inválidos");
		if (logout != null)
			model.addAttribute("message", "Deslogado com sucesso, Até mais....");
		return "login";
	}
}
