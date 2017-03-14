package com.mini.emby.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MusicController {

	@RequestMapping("/music")
	String music(Model model) {
		
		return "music";
	}
	
}
