package com.rickyphewitt.seamless.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rickyphewitt.seamless.data.exceptions.ConnectionException;
import com.rickyphewitt.seamless.services.LoginService;

@Controller
public class IndexController {
/*	@Autowired
	private EmbyService embyService;*/
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/")
	String index(Model model) throws Exception {
		String returnUrl = "";
		
		try {
			loginService.login();
			returnUrl = "redirect:home";
		} catch(ConnectionException e) {
			System.out.println("unable to login!: " + e.toString());
			return "redirect:login";
		}
		
		return returnUrl;
	}
	
}