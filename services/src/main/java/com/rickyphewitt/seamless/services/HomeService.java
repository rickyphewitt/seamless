package com.rickyphewitt.seamless.services;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.rickyphewitt.emby.api.data.Album;
import com.rickyphewitt.emby.api.data.Artist;
import com.rickyphewitt.emby.api.data.ArtistSet;


@Service
public class HomeService {

	@Autowired
	ApiService apiService;
	
	@Autowired
	ArtistService artistService;
	
	@Autowired
	AlbumService albumService;
	
	@Autowired
	DisplayOrganizerService displayOrganizerService;
	
	@Autowired
	ColumnDisplayService columnDisplayService;
	
	@Autowired
	FragmentService fragmentService;
	
	Random rand = new Random();
	
	public String home(Model model, HttpServletResponse response) {
		
		// Log into emby server
		apiService.login();
		
		// get all artists
		artistService.loadArtists();
		
		ArtistSet artists = artistService.getArtistSet();
		List<Artist> artistData = artists.getItems();
		
		model.addAttribute("artists", artistData);
		model.addAttribute("appSidebarContent", "artists");
		// get albums, will update to recently added
		Artist artist = artistData.get(0);
		albumService.loadAlbums(artist.getId());
		//System.out.println(albumService.get);
		Collection<Album> albums = albumService.getAlbums().values();
		model.addAttribute("albums", albums);
		model.addAttribute("appContentContent", "albums");
		
		return "home";
	}
	
}
