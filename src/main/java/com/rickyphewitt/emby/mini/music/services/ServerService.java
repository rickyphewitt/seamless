package com.rickyphewitt.emby.mini.music.services;

import org.springframework.stereotype.Service;

import com.rickyphewitt.emby.mini.music.data.Server;

@Service
public class ServerService {

	public ServerService(){}
	
	public Server getServer() {
		return new Server();
	}
	
	public Boolean isLoggedIn() {
		return this.getServer().getConnected();
	}
	
}
