package test.com.rickyphewitt.seamless.services.helpers;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert;
import org.thymeleaf.util.StringUtils;

import com.rickyphewitt.seamless.data.Artist;
import com.rickyphewitt.seamless.data.enums.IdSource;


public class ArtistTestHelper {

	private static Random random = new Random();
	
	public static Artist generateRandomArtist() {
		Artist artist = new Artist();
		artist.setId(random.nextLong());
		artist.setName(StringUtils.randomAlphanumeric(10));
		artist.setMediaId(StringUtils.randomAlphanumeric(10));
		artist.setMediaIdSource(IdSource.NONE);
		artist.setPrimaryImage("www.fakeserver.net/" + StringUtils.randomAlphanumeric(10) + ".jpg");
		return artist;
	}
	
	public static ArrayList<Artist> generateRandomArtists(int artistsToGenerate) {
		ArrayList<Artist> artists = new ArrayList<Artist>();
		for(int i = 0; i < artistsToGenerate; i++) {
			artists.add(ArtistTestHelper.generateRandomArtist());
		}
		return artists;
	}
	
	public static void assertEqual(Artist expected, Artist actual) {
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getName(), actual.getName());
		Assert.assertEquals(expected.getMediaId(), actual.getMediaId());
		Assert.assertEquals(expected.getMediaIdSource(), actual.getMediaIdSource());
		Assert.assertEquals(expected.getPrimaryImage(), actual.getPrimaryImage());
	}
}
