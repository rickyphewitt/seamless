package com.mini.emby;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

@Service
public class AuthClient {

	public AuthClient(){}
	
	public AuthClient(String username, String pass) throws ClientProtocolException, IOException {
		
		String URL_SECURED_BY_BASIC_AUTHENTICATION = "emby:8096";
		
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials
		 = new UsernamePasswordCredentials(username, pass);
		provider.setCredentials(AuthScope.ANY, credentials);
		  
		HttpClient client = HttpClientBuilder.create()
		  .setDefaultCredentialsProvider(provider)
		  .build();
		 
		HttpResponse response = client.execute(
		  new HttpGet(URL_SECURED_BY_BASIC_AUTHENTICATION));
		int statusCode = response.getStatusLine()
		  .getStatusCode();
		  
		//assertThat(statusCode, equalTo(HttpStatus.OK.value()));
		
	}
	
	
	
}
