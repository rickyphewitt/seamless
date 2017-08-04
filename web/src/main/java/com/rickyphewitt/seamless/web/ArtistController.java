package com.rickyphewitt.seamless.web;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rickyphewitt.seamless.data.Artist;
import com.rickyphewitt.seamless.services.AlbumService;
import com.rickyphewitt.seamless.services.ApiService;
import com.rickyphewitt.seamless.services.ArtistService;
import com.rickyphewitt.seamless.services.DisplayOrganizerService;
import com.rickyphewitt.seamless.services.FragmentService;

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
	public String artist(@PathVariable("id") String id, Model model) throws InterruptedException, ExecutionException {
		artistService.setCurrentArtistId(id);
		Artist artist = artistService.getArtistsMap().get(id);
		albumService.loadAlbums(id);
		model.addAttribute("artist", artist);
		model.addAttribute("albums", albumService.getAlbums());
		return fragmentService.getFragment("albums");
	}
}
