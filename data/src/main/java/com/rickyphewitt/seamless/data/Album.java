package com.rickyphewitt.seamless.data;

import com.rickyphewitt.seamless.data.enums.IdSource;

public class Album extends MediaItemBase {
	
	private String artistId;
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
	private IdSource artistIdSource;
	

}
