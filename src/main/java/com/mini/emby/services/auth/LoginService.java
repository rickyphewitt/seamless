package com.mini.emby.services.auth;


import org.springframework.stereotype.Service;

import com.mini.emby.services.http.HttpClient;
import com.mini.emby.services.logging.Logger;
import com.mini.emby.services.serializers.Seralizer;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.ApiEventListener;
import mediabrowser.apiinteraction.device.Device;
import mediabrowser.apiinteraction.device.IDevice;
import mediabrowser.apiinteraction.http.IAsyncHttpClient;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.serialization.IJsonSerializer;
@Service
public class LoginService {
    
	// Developers should create their own logger implementation
	ILogger logger = new Logger(this.getClass());

    
    // The underlying http stack. Developers can inject their own if desired
    IAsyncHttpClient httpClient = new HttpClient();

    // Android developers should use GsonJsonSerializer
    IJsonSerializer jsonSerializer = new Seralizer();

    // Android developers should use AndroidDevice
    IDevice device = new Device("deviceId", "deviceName");

    ApiClient apiClient = new ApiClient(httpClient, jsonSerializer, logger, "http://localhost:8096", "My app name", "app version 123", device, new ApiEventListener());
    
    apiClient.ensureWebSocket();
    
    
    
    
/*	apiClient.AuthenticateUserAsync("username", "password", new Response<AuthenticationResult>(){
	
	    @Override
	    public void onResponse(AuthenticationResult result) {
	        // Authentication succeeded
	    }
	
	    @Override
	    public void onError() {
	        // Authentication failed
	    }
	
	});*/
    
    
}
