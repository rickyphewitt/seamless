package com.rickyphewitt.seamless.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rickyphewitt.emby.api.data.Album;
import com.rickyphewitt.emby.api.data.SongSet;
import com.rickyphewitt.seamless.services.AlbumService;
import com.rickyphewitt.seamless.services.ApiService;
import com.rickyphewitt.seamless.services.ArtistService;
import com.rickyphewitt.seamless.services.DisplayOrganizerService;
import com.rickyphewitt.seamless.services.FragmentService;

@Controller
public class AlbumController {

	@Autowired
	ArtistService artistService;
	
	@Autowired
	AlbumService albumService;
	
	@Autowired
	ApiService loginService;
	
	@Autowired 
	FragmentService fragmentService;
	
	@RequestMapping("/albums")
	public String albums(Model model) {
		
		return fragmentService.getFragment("albums");
	}
	
	@RequestMapping("/album/{id}")
	public String album(@PathVariable("id") String id, Model model) {
		albumService.setCurrentAlbumId(id);
		Album album = albumService.getAlbums().get(id);
		SongSet songs = loginService.getSongsFromAlbum(id);
		model.addAttribute("artist", artistService.getArtists().get(artistService.getCurrentArtistId()));
		model.addAttribute("album", album);
		model.addAttribute("songs", songs.getItems());
		return fragmentService.getFragment("album");
		
		
	}
}
