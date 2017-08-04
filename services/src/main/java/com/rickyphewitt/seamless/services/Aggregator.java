package com.rickyphewitt.seamless.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rickyphewitt.seamless.data.Album;
import com.rickyphewitt.seamless.data.Artist;
import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.data.enums.IdSource;
import com.rickyphewitt.seamless.data.exceptions.ConnectionException;
import com.rickyphewitt.seamless.data.sources.WebApiSource;
import com.rickyphewitt.seamless.services.sources.AsyncSourceService;
import com.rickyphewitt.seamless.services.sources.emby.EmbyService;


@Service
public class Aggregator {
	
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
		sources = new ArrayList<AsyncSourceService>();
		for(IdSource source: sourceConfigService.getWebSources().keySet()) {
			for(WebApiSource sourceConfig: sourceConfigService.getWebSources().get(source)) {
				embyService.setSourceSettings(sourceConfig);
				embyService.login();
				sources.add(embyService);
			}
		}		
		

	}
		
	public ArrayList<Artist> getArtists() throws InterruptedException, ExecutionException {
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
	
	public ArrayList<Album> getAlbumsByArtist(String artistId) throws InterruptedException, ExecutionException {
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
	
	public ArrayList<Song> getSongsInAlbum(String albumId) throws InterruptedException, ExecutionException {
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
	
	public byte[] playSong(String songId) throws InterruptedException, ExecutionException {
		
		List<CompletableFuture<byte[]>> completableFutures = new ArrayList<CompletableFuture<byte[]>>();
		for(AsyncSourceService source: this.sources) {
			completableFutures.add(source.playAsyncSong(songId));
		}
		
		CompletableFuture.anyOf(completableFutures.toArray(new CompletableFuture[0])).join();
		return completableFutures.get(0).get();
	}
	
	
}
