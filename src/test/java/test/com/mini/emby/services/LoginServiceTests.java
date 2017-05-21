package test.com.mini.emby.services;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rickyphewitt.emby.api.data.AuthenticationResult;
import com.rickyphewitt.emby.api.data.PublicServerInfo;
import com.rickyphewitt.emby.api.data.User;
import com.rickyphewitt.emby.api.data.UserSet;
import com.rickyphewitt.emby.mini.music.constants.UrlConstants;
import com.rickyphewitt.emby.mini.music.services.ApiService;
import com.rickyphewitt.emby.mini.music.services.LoginService;
import com.rickyphewitt.emby.mini.music.services.ServerService;

import test.com.rickyphewitt.emby.mini.music.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class LoginServiceTests {

	static final String validHost = "http://emby:8096";
	static Random rand = new Random();
	
	@Spy
	ServerService serverService;
	
	@Mock
	ApiService apiService;
	
	@InjectMocks
	LoginService loginService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void testValidHostName() {
		
		// data setup
		String invalidHost = "blab";
		
		// validate urls
		Assert.assertTrue(LoginService.validateEmbyHostUrl(validHost));
		Assert.assertFalse(LoginService.validateEmbyHostUrl(invalidHost));
		
	}
	
	@Test
	public void testHappyPathConnect() {
		
		// data setup
		PublicServerInfo pubInfo = new PublicServerInfo();
		pubInfo.setId(String.valueOf(rand.nextInt()));
		
		// mock setup
		when(apiService.getPublicServerInfo(any(String.class))).thenReturn(pubInfo);
		
		// connect
		PublicServerInfo returnedPublicInfo = loginService.connect(validHost);
		
		assertPublicServerInfo(pubInfo, returnedPublicInfo);
	}
	
	@Test
	public void testSingleUser_NoPassword() throws Exception {
		
		// data setup
		AuthenticationResult authResult = new AuthenticationResult();
		authResult.setAccessToken(String.valueOf(rand.nextInt()));
		UserSet users = new UserSet();
		User user = new User();
		ArrayList userList = new ArrayList();
		userList.add(user);
		users.setItems(userList);
		
		// mock setup
		when(apiService.login(any(String.class), any(String.class))).thenReturn(authResult);
		// connect
		String returnUrl = loginService.login(users);
		Assert.assertTrue(UrlConstants.homeUrl.equals(returnUrl));
	}
	
	
	private void assertPublicServerInfo(PublicServerInfo expected, PublicServerInfo actual) {
		Assert.assertTrue(expected.getId().equals(actual.getId()));
	}
	
	
	
}
