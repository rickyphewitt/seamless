package test.com.rickyphewitt.seamless.services.sources;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.thymeleaf.util.StringUtils;

import com.rickyphewitt.emby.api.data.AuthenticationResult;
import com.rickyphewitt.emby.api.data.User;
import com.rickyphewitt.emby.api.data.UserSet;
import com.rickyphewitt.emby.api.services.clients.ApiV1Client;
import com.rickyphewitt.seamless.data.enums.IdSource;
import com.rickyphewitt.seamless.data.exceptions.ConnectionException;
import com.rickyphewitt.seamless.data.sources.WebApiSource;
import com.rickyphewitt.seamless.services.sources.emby.EmbyService;

import test.com.rickyphewitt.seamless.services.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class EmbyServiceTests {

	static final String validHost = "http://emby:8096";
	static Random random = new Random();
	
	//@InjectMocks
	//EmbyService embyService;
	
	@Mock
	ApiV1Client embyClient;

	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void testHappyPathConnect() throws ConnectionException {
		
//		// data setup
//		EmbyService embyService = new EmbyService();
//		UserSet users = this.generateSampleUsers(1);
//		AuthenticationResult authResult = this.generateAuthResult(true);
//		WebApiSource embySource = generateEmbySource();
//		embyService.setSourceSettings(embySource);
//		// mock setup
//		when(embyClient.getPublicUsers())
//			.thenReturn(users);
//		when(embyClient.authenticateByName(any(String.class), any(String.class)))
//			.thenReturn(authResult);
//		
//		// connect
//		embyService.login();	
	}
	
	// helpers
	private UserSet generateSampleUsers(int quantity) {
		UserSet usersSet = new UserSet();
		List<User> userItems = new ArrayList<User>();
		for(int i = 0; i < quantity; i++) {
			User u = new User();
			u.setName(StringUtils.randomAlphanumeric(10));	
			userItems.add(u);
		
		}
		
		usersSet.setItems(userItems);
		return usersSet;
	}
	
	private AuthenticationResult generateAuthResult(boolean authSuccess) {
		AuthenticationResult authResult = new AuthenticationResult();
		if(authSuccess) {
			authResult.setAccessToken(String.valueOf(random.nextInt(20)));
		}
		
		return authResult;
	}
	
	private WebApiSource generateEmbySource() {
		WebApiSource webApiSource = new WebApiSource("Emby", IdSource.EMBY);
		webApiSource.setPassword(null);
		webApiSource.setName("testName");
		webApiSource.setUrl("http://emby:8096");
		
		return webApiSource;
	}
	
//	@Test
//	public void testSingleUser_NoPassword() throws Exception {
//		
//		// data setup
//		AuthenticationResult authResult = new AuthenticationResult();
//		authResult.setAccessToken(String.valueOf(rand.nextInt()));
//		UserSet users = new UserSet();
//		User user = new User();
//		ArrayList userList = new ArrayList();
//		userList.add(user);
//		users.setItems(userList);
//		
//		// mock setup
//		when(apiService.login(any(String.class), any(String.class))).thenReturn(authResult);
//		// connect
//		String returnUrl = loginService.login(users);
//		Assert.assertTrue(UrlConstants.homeUrl.equals(returnUrl));
//	}
//	
//	
//	private void assertPublicServerInfo(PublicServerInfo expected, PublicServerInfo actual) {
//		Assert.assertTrue(expected.getId().equals(actual.getId()));
//	}
}
