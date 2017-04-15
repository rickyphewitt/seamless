package com.rickyphewitt.emby.mini.music.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rickyphewitt.emby.mini.music.services.HomeService;

@Controller
public class HomeController {
	
	@Autowired
	HomeService homeService;
	
	@RequestMapping("/home")
	String music(Model model, HttpServletResponse response) {
		return homeService.home(model, response);
	}
}
