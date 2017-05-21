package com.rickyphewitt.emby.mini.music.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rickyphewitt.emby.api.data.PublicServerInfo;
import com.rickyphewitt.emby.api.data.UserSet;
import com.rickyphewitt.emby.mini.music.lables.Lables;
import com.rickyphewitt.emby.mini.music.services.LoginService;
import com.rickyphewitt.emby.mini.music.services.ServerService;

@Controller
public class LoginController {

	@Autowired
	private ServerService serverService;
	
	@Autowired
	private LoginService loginService;
	
	
	@RequestMapping(value="connect", method=RequestMethod.GET)
	String connect(Model model) {
		return "connect";
	}

	@RequestMapping(value="connect", method=RequestMethod.POST)
	String connect(Model model, @RequestParam String url) throws Exception {
		String returnUrl = "connect";
		
		// simple validation for now
		if(!LoginService.validateEmbyHostUrl(url)) {
			return returnUrl;
		}
		
		// attempt to connect to server
		PublicServerInfo pubInfo = loginService.connect(url);
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
	}
	
	/*loginService.connect(embyUrl)*/
	
	@RequestMapping("/login")
	String index(Model model) {
		
		model.addAttribute("server", serverService.getServer());
		model.addAttribute("stayLoggedIn", Lables.STAY_LOGGED_IN);
		
		return "login";
	}
}
