package com.rickyphewitt.seamless.services;

import com.rickyphewitt.seamless.data.Album;
import com.rickyphewitt.seamless.data.Artist;
import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.data.enums.IdSource;
import com.rickyphewitt.seamless.data.exceptions.ConnectionException;
import com.rickyphewitt.seamless.data.sources.WebApiSource;
import com.rickyphewitt.seamless.services.config.CachingConfig;
import com.rickyphewitt.seamless.services.sources.AsyncSourceService;
import com.rickyphewitt.seamless.services.sources.emby.EmbyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Service
public class Aggregator {

	private static Logger logger = LogManager.getLogger();

	//attributes
	// @ToDo split out artist/album/song storage from this class
	private ArrayList<AsyncSourceService> sources;
	
	@Autowired
	SourceConfigService sourceConfigService;
    
	@Autowired
	EmbyService embyService;
	
	public Aggregator() {}
	
	/**
	 * Attempts to log into all defined web sources
	 * 
	 * @throws ConnectionException
	 */
	public void login() throws ConnectionException {
		logger.info("Attempting to log into sources");
		sources = new ArrayList<AsyncSourceService>();
		for(IdSource source: sourceConfigService.getWebSources().keySet()) {
			for(WebApiSource sourceConfig: sourceConfigService.getWebSources().get(source)) {
				embyService.setSourceSettings(sourceConfig);
				embyService.login();
				sources.add(embyService);
			}
		}		
		

	}
	@Cacheable(value = CachingConfig.ARTIST_CACHE, unless ="#result.size() == 0")
	public List<Artist> getArtists() throws InterruptedException, ExecutionException {
		logger.info("Cache Miss! Requesting Artists from services");
		ArrayList<Artist> artists = new ArrayList<Artist>();
		List<CompletableFuture<List<Artist>>> completableFutures = new ArrayList<CompletableFuture<List<Artist>>>();
		for(AsyncSourceService source: this.sources) {
			completableFutures.add(source.getAsyncArtists());
		}
		
		CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).join();
		
		for(CompletableFuture<List<Artist>> completeFuture: completableFutures) {
			artists.addAll(completeFuture.get());
		}
		
		return artists;
	}
	
	@Cacheable(value = CachingConfig.ALBUM_CACHE, unless ="#result.size() == 0")
	public List<Album> getAlbumsByArtist(String artistId) throws InterruptedException, ExecutionException {
		logger.info("Cache Miss! Requesting Albums from services");
		ArrayList<Album> albums = new ArrayList<Album>();
		
		List<CompletableFuture<List<Album>>> completableFutures = new ArrayList<CompletableFuture<List<Album>>>();
		for(AsyncSourceService source: this.sources) {
			completableFutures.add(source.getAsyncAlbumsByArtist(artistId));
		}
		
		CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).join();
		
		for(CompletableFuture<List<Album>> completeFuture: completableFutures) {
			albums.addAll(completeFuture.get());
		}
		return albums;
	}
	
	@Cacheable(value = CachingConfig.SONG_CACHE, unless ="#result.size() == 0")
	public List<Song> getSongsInAlbum(String albumId) throws InterruptedException, ExecutionException {
		logger.info("Cache Miss! Requesting Songs from services");
		ArrayList<Song> songs = new ArrayList<Song>();
		
		List<CompletableFuture<List<Song>>> completableFutures = new ArrayList<CompletableFuture<List<Song>>>();
		for(AsyncSourceService source: this.sources) {
			completableFutures.add(source.getAsyncSongsInAlbum(albumId));
		}
		
		CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).join();
		for(CompletableFuture<List<Song>> completeFuture: completableFutures) {
			songs.addAll(completeFuture.get());
		}
		return songs;
	}
	
	@Cacheable(value = CachingConfig.RAW_SONG_CACHE, unless ="#result == null")
	public byte[] playSong(String songId) throws InterruptedException, ExecutionException {
		
		List<CompletableFuture<byte[]>> completableFutures = new ArrayList<CompletableFuture<byte[]>>();
		for(AsyncSourceService source: this.sources) {
			completableFutures.add(source.playAsyncSong(songId));
		}
		
		CompletableFuture.anyOf(completableFutures.toArray(new CompletableFuture[0])).join();
		return completableFutures.get(0).get();
	}

	@Cacheable(value = CachingConfig.IMAGE_URL_CACHE, unless ="#result.length() == 0")
	public String getPrimaryImageUrl(String entityId, String primaryImageId) throws ExecutionException, InterruptedException {
		List<CompletableFuture<String>> completableFutures = new ArrayList<CompletableFuture<String>>();
		for(AsyncSourceService source: this.sources) {
			completableFutures.add(source.getAsyncPrimaryImage(entityId, primaryImageId));
		}

		CompletableFuture.anyOf(completableFutures.toArray(new CompletableFuture[0])).join();
		return completableFutures.get(0).get();




	}

	@Cacheable(value = CachingConfig.IMAGE_CACHE, unless ="#result == null")
	public byte[] getImage(String url) throws ExecutionException, InterruptedException {
		List<CompletableFuture<byte[]>> completableFutures = new ArrayList<CompletableFuture<byte[]>>();
		for (AsyncSourceService source : this.sources) {
			completableFutures.add(source.getAsyncImage(url));
		}

		CompletableFuture.anyOf(completableFutures.toArray(new CompletableFuture[0])).join();
		return completableFutures.get(0).get();
	}


}
