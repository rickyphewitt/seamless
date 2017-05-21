package com.rickyphewitt.emby.mini.music.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rickyphewitt.emby.api.data.Album;
import com.rickyphewitt.emby.api.data.SongSet;
import com.rickyphewitt.emby.mini.music.services.AlbumService;
import com.rickyphewitt.emby.mini.music.services.ApiService;
import com.rickyphewitt.emby.mini.music.services.ArtistService;
import com.rickyphewitt.emby.mini.music.services.DisplayOrganizerService;
import com.rickyphewitt.emby.mini.music.services.FragmentService;

@Controller
public class AlbumController {

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
	
	@RequestMapping("/albums")
	public String albums(Model model) {
		
		return fragmentService.getFragment("albums");
	}
	
	@RequestMapping("/album/{id}")
	public String album(@PathVariable("id") String id, Model model) {
		Album album = albumService.getAlbums().get(id);
		SongSet songs = loginService.getSongsFromAlbum(id);
		model.addAttribute("album", album);
		model.addAttribute("songs", songs.getItems());
		return fragmentService.getFragment("album");
		
		
	}
}
