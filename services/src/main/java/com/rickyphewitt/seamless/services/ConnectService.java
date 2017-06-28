package com.rickyphewitt.seamless.services;

import org.springframework.stereotype.Service;

@Service
public class ConnectService {
	/*
	@Autowired
	ApiService apiService;
	
	@Autowired
	LoginService loginService;
	
	@Autowired 
	ServerService serverService;
		
	@Autowired
	FragmentService fragmentService;
	

	public Server getServer() {
		serverService = new ServerService(apiService.getEmbyUrl(),
				apiService.getUsername(), apiService.getPassword());
		
		return serverService.getServer();
	}
	
	public String connect(String embyUrl) {
		updateUrlOnChanged(embyUrl);
		PublicServerInfo publicServerInfo = apiService.getPublicServerInfo(embyUrl);
		serverService.setPublicServerInfo(publicServerInfo);
		
		if(publicServerInfo != null) {
			// get public uses
			UserSet users = apiService.getPublicUsers();
		
			if(users.getItems().size() == 1) {
				User user = users.getItems().get(0);
				if(!user.isHasPassword()) {
					
				}
			}
		}
	}

	
	
	private 
	
	private void updateUrlOnChanged(String embyUrl) {
		if(!embyUrl.equals(serverService.getServer().getUrl())) {
			serverService.getServer().setUrl(embyUrl);
		}
	}
	*/
	
	//	
//	
//	public String home(Model model, HttpServletResponse response) {
//		
//		// Log into emby server
//		loginService.login();
//		
//		// get all artists
//		artistService.loadArtists();
//		
//		ArtistSet artists = artistService.getArtistSet();
//		Assert.assertTrue(artists.getItems().size() > 0);
//		
//		HashMap<String, ArrayList<Item>> sortedArtists = displayOrganizerService.organizeItems(artists.getItems());
//		
//		columnDisplayService.setNumberOfColumns(6);
//		columnDisplayService.calcLinesPerColumn(displayOrganizerService.getSortedKeys().size() + artists.getItems().size());
//		
//		
//		
//		
//		
//
//		model.addAttribute("sortedArtistKeys", displayOrganizerService.getSortedKeys());
//		model.addAttribute("sortedArtistMap", sortedArtists);
//		model.addAttribute("innerContent", "artists");
//		return "home";
//	}
	
}
