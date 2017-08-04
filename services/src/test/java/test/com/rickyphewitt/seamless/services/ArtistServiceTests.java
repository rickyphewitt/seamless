package test.com.rickyphewitt.seamless.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rickyphewitt.seamless.data.Artist;
import com.rickyphewitt.seamless.services.Aggregator;
import com.rickyphewitt.seamless.services.ArtistService;

import test.com.rickyphewitt.seamless.services.config.TestConfig;
import test.com.rickyphewitt.seamless.services.helpers.ArtistTestHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ArtistServiceTests {

	@InjectMocks
	ArtistService artistService;
	
	@Mock
	Aggregator aggregatorService;
	
	@Test
	public void loadArtistsTest_happyPath() throws InterruptedException, ExecutionException {
		
		// data
		ArrayList<Artist> artists = ArtistTestHelper.generateRandomArtists(4);
		
		
		//mocks
		when(aggregatorService.getArtists()).thenReturn(artists);
		
		// run
		artistService.loadArtists();
		
		// verify
		for(Artist artist: artists) {
			Assert.assertTrue(artistService.getArtistsMap().containsKey(artist.getMediaId()));
			Artist actualArtist = artistService.getArtistsMap().get(artist.getMediaId());
			ArtistTestHelper.assertEqual(artist, actualArtist);
		}
				
	}
	
	@Test
	public void consolidate_duplicateEntities() {
		
		// data setup
		int toGenerate = 4;
		ArrayList<Artist> artists = ArtistTestHelper.generateRandomArtists(toGenerate);
		ArrayList<Artist> dupeArtists = new ArrayList<Artist>();
		
		for (int i = 0; i < 4; i++) {
			Artist artist = new Artist();
			BeanUtils.copyProperties(artists.get(i), artist);
			dupeArtists.add(artist);
			
		}
		
		artists.addAll(dupeArtists);
		
		// call
		ArrayList<Artist> consolidatedArtists = (ArrayList<Artist>) artistService.consolidate(artists);
		
		
		//verify
		Assert.assertTrue(consolidatedArtists.size() == 4);
		
	}
	
	
}
