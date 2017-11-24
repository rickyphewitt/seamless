package test.com.rickyphewitt.seamless.services;


import com.rickyphewitt.seamless.data.Album;
import com.rickyphewitt.seamless.data.enums.IdSource;
import com.rickyphewitt.seamless.data.exceptions.ConnectionException;
import com.rickyphewitt.seamless.services.Aggregator;
import com.rickyphewitt.seamless.services.AlbumService;
import com.rickyphewitt.seamless.services.ImageService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.com.rickyphewitt.seamless.services.config.TestConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static ch.qos.logback.core.encoder.ByteArrayUtil.hexStringToByteArray;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ImageServiceTests {
	
	@InjectMocks
	ImageService imageService;
	
	@Mock
	Aggregator aggregatorService;

	@Mock
	AlbumService albumService;

	@Test
	public void happyPath_loadImage() throws ConnectionException, InterruptedException, ExecutionException {
		
		// data setup
		String itemId = org.apache.commons.lang3.RandomStringUtils.randomAlphabetic(10);
		String primaryImageId = org.apache.commons.lang3.RandomStringUtils.randomAlphabetic(10);
		String randomImageUrl = org.apache.commons.lang3.RandomStringUtils.randomAlphabetic(50);
		Album album = new Album();
		album.setPrimaryImage(randomImageUrl);
		HashMap<String, Album> albumMap = new HashMap<String, Album>();
		albumMap.put(itemId, album);

		byte[] bytes = hexStringToByteArray("e04fd020ea3a6910a2d808002b30309d");

		//mocks
		when(albumService.getAlbumsMap()).thenReturn(albumMap);
		when(aggregatorService.getPrimaryImageUrl(itemId, primaryImageId)).thenReturn(randomImageUrl);
		when(aggregatorService.getImage(any(String.class))).thenReturn(bytes);

		
		//run
		byte[] image = imageService.getImage(itemId, IdSource.EMBY);
		
		 //verify
		Assert.assertNotNull(image);
	}
}

