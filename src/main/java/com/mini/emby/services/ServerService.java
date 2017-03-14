package com.mini.emby.services;

import org.springframework.stereotype.Service;

import com.mini.emby.data.Server;

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
