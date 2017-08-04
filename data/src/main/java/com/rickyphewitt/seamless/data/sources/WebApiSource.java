package com.rickyphewitt.seamless.data.sources;

import org.springframework.stereotype.Component;

import com.rickyphewitt.seamless.data.enums.IdSource;

public class WebApiSource extends BaseMediaSource {

	private String username;
	private String password;
	private String url;
	private int maxRetryAttempts;
	
	public WebApiSource(String name, IdSource source) {
		super(name, source);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getMaxRetryAttempts() {
		return this.maxRetryAttempts;
	}

	public void setMaxRetryAttempts(int maxRetryAttempts) {
		this.maxRetryAttempts = maxRetryAttempts;
	}
	
}
