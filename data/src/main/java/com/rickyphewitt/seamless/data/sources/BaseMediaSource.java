package com.rickyphewitt.seamless.data.sources;

import com.rickyphewitt.seamless.data.enums.IdSource;

public abstract class BaseMediaSource {

	// Attributes
	String name;
	IdSource source;
	
	// Constructor
	public BaseMediaSource(){}
	
	public BaseMediaSource(String name, IdSource source) {
		this.name = name;
		this.source = source;
	}
	
	
	// Getters/Setters
	public String getName() {
		return name;
	}

	public IdSource getSource() {
		return source;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSource(IdSource source) {
		this.source = source;
	}
	
}
