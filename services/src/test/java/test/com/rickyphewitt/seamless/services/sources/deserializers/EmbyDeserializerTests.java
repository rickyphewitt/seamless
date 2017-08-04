package test.com.rickyphewitt.seamless.services.sources.deserializers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rickyphewitt.emby.api.data.ArtistSet;
import com.rickyphewitt.seamless.data.Artist;
import com.rickyphewitt.seamless.data.enums.IdSource;
import com.rickyphewitt.seamless.services.sources.emby.deserializers.EmbyDeserializer;

import test.com.rickyphewitt.seamless.services.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class EmbyDeserializerTests {

	private static Random random = new Random();
	
	@Test
	public void happyPath() {
	
		// data setup
		int artistsToCreate = 2;
		ArtistSet artists = this.generateEmbyArtistSet(artistsToCreate);
		
		// call
		List<Artist> commonArtists = EmbyDeserializer.deserialize(artists);
		// verify
		HashMap<String, Artist> commonArtistMap = new HashMap<String, Artist>();
		for(Artist commonArtist: commonArtists) {
			commonArtistMap.put(commonArtist.getName(), commonArtist);
			
		}
		
		// assert
		for(com.rickyphewitt.emby.api.data.Artist embyArtist: artists.getItems()) {
			Assert.assertTrue(commonArtistMap.containsKey(embyArtist.getName()));
			Artist commonArtist = commonArtistMap.get(embyArtist.getName());
			Assert.assertEquals(commonArtist.getMediaIdSource(), IdSource.EMBY);
			Assert.assertEquals(commonArtist.getMediaId(), embyArtist.getId());
			
		}
	}
	
	private ArtistSet generateEmbyArtistSet(int artists) {
		ArtistSet embyArtists = new ArtistSet();
		ArrayList<com.rickyphewitt.emby.api.data.Artist> items = new ArrayList<com.rickyphewitt.emby.api.data.Artist>();
		for(int i = 0; i < artists; i++) {
			com.rickyphewitt.emby.api.data.Artist embyArtist = new com.rickyphewitt.emby.api.data.Artist();
			
			embyArtist.setId(String.valueOf(random.nextLong()));
			embyArtist.setName(String.valueOf(random.nextInt()));
			items.add(embyArtist);
			
		}
		
		embyArtists.setItems(items);
		
		return embyArtists;
	}
}
