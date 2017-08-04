package com.rickyphewitt.seamless.data;

import java.util.Date;
import java.util.List;

import com.rickyphewitt.seamless.data.enums.IdSource;

public abstract class MediaItemBase {

	private Long id;
	private String universalKey;
	private String mediaId;
	private IdSource mediaIdSource;
	private String name;
	private String primaryImage;
	private List<String> images;
	private Date date;
	private String duration;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUniversalKey() {
		return universalKey;
	}
	
	private void setUniversalKey(String universalKey) {
		//remove non alpha numeric chars
		universalKey = universalKey.replaceAll("[^a-zA-Z0-9]", "");
		//remove spaces
		universalKey = universalKey.replaceAll("\\s+","");
		this.universalKey = universalKey.toLowerCase();
		
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public IdSource getMediaIdSource() {
		return mediaIdSource;
	}
	public void setMediaIdSource(IdSource mediaIdSource) {
		this.mediaIdSource = mediaIdSource;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		if(this.name != null) {
			this.setUniversalKey(name);
		}
	}
	public String getPrimaryImage() {
		return primaryImage;
	}
	public void setPrimaryImage(String primaryImage) {
		this.primaryImage = primaryImage;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	
	
	
	
}
