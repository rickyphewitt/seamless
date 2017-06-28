package com.rickyphewitt.seamless.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rickyphewitt.seamless.services.ServerService;

@Controller
public class IndexController {
	
	@Autowired
	private ServerService serverService;
	
	@RequestMapping("/")
	String index(Model model) {
		String goTo = "redirect:connect";
		if(serverService.isLoggedIn()) {
			goTo = "redirect:music";
		}
		return goTo;
	}
	
}