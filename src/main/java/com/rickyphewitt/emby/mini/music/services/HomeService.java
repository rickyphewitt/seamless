package com.rickyphewitt.emby.mini.music.services;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.rickyphewitt.emby.api.data.ArtistSet;
import com.rickyphewitt.emby.api.data.Item;

import junit.framework.Assert;

@Service
public class HomeService {

	@Autowired
	LoginService loginService;
	
	@Autowired
	DisplayOrganizerService displayOrganizerService;
	
	@Autowired
	ColumnDisplayService columnDisplayService;
	
	public String home(Model model, HttpServletResponse response) {
		
		// Log into emby server
		loginService.login();
//	
		// get all artists
		ArtistSet artists = loginService.getArtists();
		Assert.assertTrue(artists.getItems().size() > 0);
		
		HashMap<String, ArrayList<Item>> sortedArtists = displayOrganizerService.organizeItems(artists.getItems());
		
		columnDisplayService.setNumberOfColumns(6);
		columnDisplayService.calcLinesPerColumn(displayOrganizerService.getSortedKeys().size() + artists.getItems().size());
		
		
		
		
		
//		
//		// get albums by artist
//		AlbumSet albums = loginService.getAlbumsByArtist(artists.getItems().get(0).getId());
//		Assert.assertTrue(albums.getItems().size() > 0);
//		
//		// get songs by album
//		SongSet songs = loginService.getSongsFromAlbum(albums.getItems().get(0).getId());
//		Assert.assertTrue(songs.getItems().size() > 0);
//		
//		// get single song
//		byte[] songFile = loginService.getSong(songs.getItems().get(0).getId());
//		Assert.assertNotNull(songFile);
		
		
//		
//		 try {
//	            
////			 	File file = new File(System.getenv("OPENSHIFT_DATA_DIR")+"audio.m4a");
////	            InputStream input = new FileInputStream(file);
//
//	            response.setContentLength((int) songFile.length);
//	            System.out.println("JAVA: (int) file.length()==> "+(int) songFile.length);
//	            response.setContentType("audio/mp3");
//	            //System.out.println("JAVA: new MimetypesFileTypeMap().getContentType(file)==> "+new MimetypesFileTypeMap().getContentType(file));
//
//	            OutputStream output = response.getOutputStream();
//	           // byte[] bytes = new byte[BUFFER_LENGTH];
//	            //int read = 0;
//	            //while ((read = input.read(songFile, 0, 1)) != -1) {
//	                output.write(songFile);
//	                output.flush();
//	            //}
//
//	            //input.close();
//	            output.close();
//
//	        } catch (Exception e) {
//	            e.printStackTrace(System.out);
//	        }
		model.addAttribute("sortedArtistKeys", displayOrganizerService.getSortedKeys());
		model.addAttribute("sortedArtistMap", sortedArtists);
		return "home";
	}
	
}
