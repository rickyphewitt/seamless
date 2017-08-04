package com.rickyphewitt.seamless.services;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.rickyphewitt.seamless.data.Album;
import com.rickyphewitt.seamless.data.Artist;


@Service
public class HomeService {
	
	@Autowired
	ApiService apiService;
	
	@Autowired
	ArtistService artistService;
	
	@Autowired
	AlbumService albumService;
	
	@Autowired
	FragmentService fragmentService;
	
	@Autowired
	Aggregator aggregatorService;
	
	Random rand = new Random();
	
	public String home(Model model, HttpServletResponse response) throws InterruptedException, ExecutionException {
		
		// get all artists
		artistService.loadArtists();
		
		List<Artist> artistData = artistService.getArtists();
		
		model.addAttribute("artists", artistData);
		model.addAttribute("appSidebarContent", "artists");
		Artist artist = artistData.get(0);
		albumService.loadAlbums(artist.getMediaId());
		Collection<Album> albums = albumService.getAlbums();
		model.addAttribute("albums", albums);
		model.addAttribute("appContentContent", "albums");
		
		return "home";
	}
	
}
