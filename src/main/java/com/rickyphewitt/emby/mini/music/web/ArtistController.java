package com.rickyphewitt.emby.mini.music.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rickyphewitt.emby.api.data.AlbumSet;
import com.rickyphewitt.emby.api.data.Artist;
import com.rickyphewitt.emby.mini.music.services.AlbumService;
import com.rickyphewitt.emby.mini.music.services.ArtistService;
import com.rickyphewitt.emby.mini.music.services.DisplayOrganizerService;
import com.rickyphewitt.emby.mini.music.services.FragmentService;
import com.rickyphewitt.emby.mini.music.services.ApiService;

@Controller
public class ArtistController {

	@Autowired
	ArtistService artistService;
	
	@Autowired
	AlbumService albumService;
	
	@Autowired
	ApiService loginService;
	
	@Autowired
	DisplayOrganizerService displayOrganizerService;
	
	@Autowired 
	FragmentService fragmentService;
	
	@RequestMapping("/artists/{id}")
	public String artist(@PathVariable("id") String id, Model model) {

		Artist artist = artistService.getArtists().get(id);
		albumService.loadAlbums(id);
		model.addAttribute("artist", artist);
		model.addAttribute("albums", albumService.getAlbumSet().getItems());
		return fragmentService.getFragment("albumsByArtist");
	}
}
