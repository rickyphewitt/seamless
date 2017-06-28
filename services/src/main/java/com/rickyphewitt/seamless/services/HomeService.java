package com.rickyphewitt.seamless.services;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.rickyphewitt.emby.api.data.ArtistSet;
import com.rickyphewitt.emby.api.data.Item;


@Service
public class HomeService {

	@Autowired
	ApiService apiService;
	
	@Autowired
	ArtistService artistService;
	
	@Autowired
	DisplayOrganizerService displayOrganizerService;
	
	@Autowired
	ColumnDisplayService columnDisplayService;
	
	@Autowired
	FragmentService fragmentService;
	
	public String home(Model model, HttpServletResponse response) {
		
		// Log into emby server
		//apiService.login();
		
		// get all artists
		artistService.loadArtists();
		
		ArtistSet artists = artistService.getArtistSet();
		//Assert.assertTrue(artists.getItems().size() > 0);
		
		HashMap<String, ArrayList<Item>> sortedArtists = displayOrganizerService.organizeItems(artists.getItems());
		
		columnDisplayService.setNumberOfColumns(6);
		columnDisplayService.calcLinesPerColumn(displayOrganizerService.getSortedKeys().size() + artists.getItems().size());
		
		
		
		
		

		model.addAttribute("sortedArtistKeys", displayOrganizerService.getSortedKeys());
		model.addAttribute("sortedArtistMap", sortedArtists);
		model.addAttribute("innerContent", "artists");
		return "home";
	}
	
}
