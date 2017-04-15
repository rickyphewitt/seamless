package com.rickyphewitt.emby.mini.music.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rickyphewitt.emby.api.data.AlbumSet;
import com.rickyphewitt.emby.mini.music.services.DisplayOrganizerService;
import com.rickyphewitt.emby.mini.music.services.LoginService;

@Controller
public class ArtistController {
	@Autowired
	LoginService loginService;
	
	@Autowired
	DisplayOrganizerService displayOrganizerService;
	
	
	@RequestMapping("/artists/{id}")
	public String artist(@PathVariable("id") String id, Model model) {
		//loginService.login();
		AlbumSet albums = loginService.getAlbumsByArtist(id);
		model.addAttribute("albums", albums.getItems());
		return "artist";
	}
}
