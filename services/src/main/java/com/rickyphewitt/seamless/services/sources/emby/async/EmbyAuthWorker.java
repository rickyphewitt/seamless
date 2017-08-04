package com.rickyphewitt.seamless.services.sources.emby.async;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.stereotype.Component;

import com.rickyphewitt.seamless.data.Artist;
import com.rickyphewitt.seamless.data.sources.WebApiSource;

@Component
public class EmbyAuthWorker implements Callable<List<Artist>> {

	//private EmbyService embyService;
	
//	public EmbyAuthWorker() {
//		//this.embyService = new EmbyService(embyConfig);
//	}
//	
    @Override
    public List<Artist> call() throws Exception {
//    	System.out.println("thread logging in!");
//    	embyService.login();
//    	System.out.println("thread getting artists!");
//		List<Artist> artists = embyService.getArtists();
//    	System.out.println("thread returning!");
//    	return artists;
    	return null;
    }
    
    public void setConfig(WebApiSource embyConfig) {
//    	embyService.setSourceSettings(embyConfig);
    }
    
    
}
