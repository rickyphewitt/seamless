package test.com.rickyphewitt.seamless.services;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Random;

import com.rickyphewitt.seamless.data.Config;
import com.rickyphewitt.seamless.data.exceptions.ConfigNotFoundException;
import com.rickyphewitt.seamless.data.exceptions.ConnectionException;
import com.rickyphewitt.seamless.services.Aggregator;
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
import com.rickyphewitt.seamless.services.ApiService;
import com.rickyphewitt.seamless.services.LoginService;
import com.rickyphewitt.seamless.services.ServerService;
import com.rickyphewitt.seamless.services.constants.UrlConstants;

import test.com.rickyphewitt.seamless.services.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class LoginServiceTests {

	static final String validHost = "http://emby:8096";
	static Random rand = new Random();
	
	@InjectMocks
	LoginService loginService;

	@Spy
	ServerService serverService;

	@Mock
	ApiService apiService;

	@Mock
	Aggregator aggregatorService;
	
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

	@Test(expected = ConfigNotFoundException.class)
	public void testNoConfigs() throws ConnectionException, ConfigNotFoundException {
		doThrow(ConfigNotFoundException.class).when(aggregatorService).login();
		loginService.login();
	}

	private void assertPublicServerInfo(PublicServerInfo expected, PublicServerInfo actual) {
		Assert.assertTrue(expected.getId().equals(actual.getId()));
	}
	
	
	
}
