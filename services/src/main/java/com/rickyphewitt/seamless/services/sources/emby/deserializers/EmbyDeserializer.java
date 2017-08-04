package com.rickyphewitt.seamless.services.sources.emby.deserializers;

import java.util.ArrayList;
import java.util.List;

import com.rickyphewitt.emby.api.data.AlbumSet;
import com.rickyphewitt.emby.api.data.ArtistSet;
import com.rickyphewitt.emby.api.data.SongSet;
import com.rickyphewitt.seamless.data.Album;
import com.rickyphewitt.seamless.data.Artist;
import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.data.enums.IdSource;

public class EmbyDeserializer {


	public static List<Artist> deserialize(ArtistSet embyArtists) {
		ArrayList<Artist> artists = new ArrayList<Artist>();
		
		for(com.rickyphewitt.emby.api.data.Artist embyArtist: embyArtists.getItems()) {
			artists.add(EmbyDeserializer.deserialize(embyArtist));
		}
		
		return artists;
	}
	
	public static Artist deserialize(com.rickyphewitt.emby.api.data.Artist embyArtist) {
		Artist commonArtist = new Artist();
		
		commonArtist.setMediaId(embyArtist.getId());
		commonArtist.setMediaIdSource(IdSource.EMBY);
		commonArtist.setName(embyArtist.getName());
		commonArtist.setDuration(embyArtist.getRunTimeTicks().toString());
		
		return commonArtist;
		
	}
	
	public static List<Album> deserialize(AlbumSet embyAlbums) {
		ArrayList<Album> albums = new ArrayList<Album>();
		
		for(com.rickyphewitt.emby.api.data.Album embyAlbum: embyAlbums.getItems()) {
			albums.add(EmbyDeserializer.deserialize(embyAlbum));
		}
		
		return albums;
	}
	
	public static Album deserialize(com.rickyphewitt.emby.api.data.Album embyAlbum) {
		Album commonArtist = new Album();
		
		commonArtist.setMediaId(embyAlbum.getId());
		commonArtist.setMediaIdSource(IdSource.EMBY);
		commonArtist.setName(embyAlbum.getName());
		commonArtist.setDuration(embyAlbum.getRunTimeTicks().toString());
		
		return commonArtist;
		
	}
	
	public static ArrayList<Song> deserialize(SongSet embySongs) {
		ArrayList<Song> songs = new ArrayList<Song>();
		
		for(com.rickyphewitt.emby.api.data.Song embySong: embySongs.getItems()) {
			songs.add(EmbyDeserializer.deserialize(embySong));
		}
		
		return songs;
	}
	
	public static Song deserialize(com.rickyphewitt.emby.api.data.Song embySong) {
		Song commonSong = new Song();
		commonSong.setMediaId(embySong.getId());
		commonSong.setMediaIdSource(IdSource.EMBY);
		commonSong.setName(embySong.getName());
		commonSong.setDuration(embySong.getRunTimeTicks().toString());
		commonSong.setTrackNumber(embySong.getTrackNumber());
		return commonSong;
		
	}
	
}
