package com.rickyphewitt.seamless.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rickyphewitt.emby.api.data.AuthenticationResult;
import com.rickyphewitt.emby.api.data.PublicServerInfo;
import com.rickyphewitt.emby.api.data.User;
import com.rickyphewitt.emby.api.data.UserSet;
import com.rickyphewitt.seamless.data.Server;
import com.rickyphewitt.seamless.services.constants.UrlConstants;

@Service
public class LoginService {
	
	@Autowired
	ApiService apiService;
		
	@Autowired
	ServerService serverService;
	
	@Autowired
	FragmentService fragmentService;
	
	public void login(User user, String password) throws Exception {
		AuthenticationResult authResult = apiService.login(user.getName(), password);
		if(!isAuthed(authResult)) {
			throw new Exception("Failed To Log in!");
		}
	}
	
	
	public void loginUserPasswordNotSet(User user) throws Exception {
		login(user, serverService.getServer().getPassword());
	}
	
	
	public String login(UserSet users) throws Exception {
		String returnUrl = UrlConstants.loginUrl;
		if(isSinglePublicUser(users)) {
			loginUserPasswordNotSet(users.getItems().get(0));
			returnUrl = UrlConstants.homeUrl;
		}		
		return returnUrl;
	}
	
	public Server getServer() {
		serverService = new ServerService(apiService.getEmbyUrl(),
				apiService.getUsername(), apiService.getPassword());
		
		return serverService.getServer();
	}
	
	public PublicServerInfo connect(String embyUrl) {
		updateUrlOnChanged(embyUrl);

		PublicServerInfo pubInfo = apiService.getPublicServerInfo(embyUrl);
		serverService.setPublicServerInfo(pubInfo);
		return pubInfo;
	}
	
	public UserSet getPublicUsers() {
		return apiService.getPublicUsers();
	}

	public boolean isSinglePublicUser(UserSet users) {
		boolean singleUser = false;
		if(users.getItems().size() == 1) {
			singleUser = true;
		}
		
		return singleUser;
		
	}
	
	
	public static boolean validateEmbyHostUrl(String url) {
		boolean isValid = false;
		if(url != null && url != "" && url.length() > 5) {
			isValid = true;
		}
		
		return isValid;
	}
	
	
	private boolean isAuthed(AuthenticationResult authResult) {
		boolean isAuthed = false;
		if(authResult.getAccessToken() != "" ){
				isAuthed = true;
		}
		
		return isAuthed;
	}
	
	
	private void updateUrlOnChanged(String embyUrl) {
		if(!embyUrl.equals(serverService.getServer().getUrl())) {
			serverService.getServer().setUrl(embyUrl);
		}
	}
	
	
}
