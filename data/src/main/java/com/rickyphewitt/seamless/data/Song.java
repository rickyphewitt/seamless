package com.rickyphewitt.seamless.data;

import com.rickyphewitt.seamless.data.enums.IdSource;

public class Song extends MediaItemBase {
	
	private int trackNumber;
	private String albumId;
	private IdSource albumIdSource;
	private String artistId;
	private IdSource artistIdSource;
		
	public int getTrackNumber() {
		return trackNumber;
	}
	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}
	public String getAlbumId() {
		return albumId;
	}
	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}
	public IdSource getAlbumIdSource() {
		return albumIdSource;
	}
	public void setAlbumIdSource(IdSource albumIdSource) {
		this.albumIdSource = albumIdSource;
	}
	public String getArtistId() {
		return artistId;
	}
	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	public IdSource getArtistIdSource() {
		return artistIdSource;
	}
	public void setArtistIdSource(IdSource artistIdSource) {
		this.artistIdSource = artistIdSource;
	}
}
