package com.rickyphewitt.seamless.web;

import com.rickyphewitt.seamless.data.exceptions.ConfigNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rickyphewitt.seamless.data.exceptions.ConnectionException;
import com.rickyphewitt.seamless.services.LoginService;

@Controller
public class IndexController {

	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/")
	String index(Model model) throws Exception {
		String returnUrl = "";
		
		try {
			loginService.login();
			returnUrl = "redirect:home";
		} catch(ConfigNotFoundException e) {
			logger.info("Redirecting to first run");
			return "redirect:firstRun";
		} catch(ConnectionException e) {
			logger.info("unable to login!: " + e.toString());
			return "redirect:login";
		} catch(Exception e) {
			logger.info("Caught unhandled exception: " + e.getMessage());
			return "redirect:/";
		}
		
		return returnUrl;
	}
	
}