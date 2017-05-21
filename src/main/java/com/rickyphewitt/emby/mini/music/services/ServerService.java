package com.rickyphewitt.emby.mini.music.services;

import org.springframework.stereotype.Service;

import com.rickyphewitt.emby.api.data.PublicServerInfo;
import com.rickyphewitt.emby.mini.music.data.Server;

@Service
public class ServerService {

	private Server server;
	private PublicServerInfo publicServerInfo;
	
	public ServerService(){}
	
	public ServerService(String url, String username, String password) {
		server = new Server();
		server.setUrl(url);
		server.setUsername(username);
		server.setPassword(password);
	}
	
	
	public Server getServer() {
		return new Server();
	}
	
	
	public PublicServerInfo getPublicServerInfo() {
		return publicServerInfo;
	}

	public void setPublicServerInfo(PublicServerInfo publicServerInfo) {
		this.publicServerInfo = publicServerInfo;
	}

	public Boolean isLoggedIn() {
		return this.getServer().getConnected();
	}
	
}
