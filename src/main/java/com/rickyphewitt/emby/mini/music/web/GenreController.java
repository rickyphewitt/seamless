package com.rickyphewitt.emby.mini.music.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rickyphewitt.emby.mini.music.services.FragmentService;

@Controller
public class GenreController {

	@Autowired 
	FragmentService fragmentService;
	
	@RequestMapping("/genres")
	public String genres(Model model) {
		
		return fragmentService.getFragment("genres");
	}
}
