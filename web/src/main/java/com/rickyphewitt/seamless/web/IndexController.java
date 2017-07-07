package com.rickyphewitt.seamless.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rickyphewitt.emby.api.data.PublicServerInfo;
import com.rickyphewitt.emby.api.data.UserSet;
import com.rickyphewitt.seamless.services.LoginService;
import com.rickyphewitt.seamless.services.ServerService;

@Controller
public class IndexController {
	
	@Autowired
	private ServerService serverService;
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/")
	String index(Model model) throws Exception {
		String returnUrl = "";
		
		// attempt to connect to server
		PublicServerInfo pubInfo = loginService.connect("http://localhost:8096");
		System.out.println(pubInfo.getLocalAddress());
		
		// get public users
		UserSet users = loginService.getPublicUsers();
		if(loginService.isSinglePublicUser(users)) {
			loginService.loginUserPasswordNotSet(users.getItems().get(0));
			returnUrl = "redirect:home";
		} else {
			//return users to display
			return "redirect:login";
		}
		
		return returnUrl;
//		if(serverService.isLoggedIn()) {
//			//goTo = "redirect:music";
//			//for now attempt to log in
//			
//		}
//		return goTo;
	}
	
}