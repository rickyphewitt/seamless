package com.mini.emby.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mini.emby.lables.Lables;
import com.mini.emby.services.ServerService;

@Controller
public class LoginController {

	@Autowired
	private ServerService serverService;
	
	@RequestMapping("/login")
	String index(Model model) {
		
		model.addAttribute("server", serverService.getServer());
		model.addAttribute("stayLoggedIn", Lables.STAY_LOGGED_IN);
		
		return "login";
	}
}
