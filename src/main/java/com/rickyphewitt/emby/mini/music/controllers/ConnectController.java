package com.rickyphewitt.emby.mini.music.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConnectController {
	
	@RequestMapping("/connect")
	String connect(Model model) throws InterruptedException {
		//do stuff
		 Thread.sleep(1000);
		
		return "redirect:music";
	}
}
