package test.com.rickyphewitt.seamless.services;


import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.rickyphewitt.seamless.data.exceptions.ConfigNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.thymeleaf.util.StringUtils;

import com.rickyphewitt.seamless.data.Album;
import com.rickyphewitt.seamless.data.Artist;
import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.data.enums.IdSource;
import com.rickyphewitt.seamless.data.exceptions.ConnectionException;
import com.rickyphewitt.seamless.data.sources.WebApiSource;
import com.rickyphewitt.seamless.services.Aggregator;
import com.rickyphewitt.seamless.services.SourceConfigService;
import com.rickyphewitt.seamless.services.sources.emby.EmbyService;

import test.com.rickyphewitt.seamless.services.config.TestConfig;
import test.com.rickyphewitt.seamless.services.helpers.ArtistTestHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class AggregatorTests {
	
	@InjectMocks
	Aggregator aggregator;
	
	@Mock
	SourceConfigService sourceConfigService;
	
	@Mock
	EmbyService embyService;

	@Test
	public void happyPath_loginMultipleEmbyServer() throws ConnectionException, ConfigNotFoundException {
		// Data setup
		int recordsToCreate = 2;
		
		ArrayList<WebApiSource> embySources = new ArrayList<WebApiSource>();
		for(int i = 0; i < recordsToCreate; i++) {
			embySources.add(this.generateWebApiSource("emby" + i, IdSource.EMBY));
		}
		
		HashMap<IdSource, List<WebApiSource>> sourceConfigSources = new HashMap<IdSource, List<WebApiSource>>();
		sourceConfigSources.put(IdSource.EMBY, embySources);
		
		// Mocks
		when(sourceConfigService.getWebSources()).thenReturn(sourceConfigSources);
		
		// run
		aggregator.login();
	
		// verify
		verify(embyService, times(recordsToCreate)).login();
		
	}
	
	@Test
	public void happyPath_getArtistsMultipleEmbyServer() throws ConnectionException, InterruptedException, ExecutionException, ConfigNotFoundException {
		// Data setup
		int recordsToCreate = 2;
		
		ArrayList<WebApiSource> embySources = new ArrayList<WebApiSource>();
		ArrayList<Artist> artists = ArtistTestHelper.generateRandomArtists(recordsToCreate);
		for(int i = 0; i < recordsToCreate; i++) {
			embySources.add(this.generateWebApiSource("emby" + i, IdSource.EMBY));
			
		}
		CompletableFuture<List<Artist>> compFuture = CompletableFuture.completedFuture(artists);
		
		HashMap<IdSource, List<WebApiSource>> sourceConfigSources = new HashMap<IdSource, List<WebApiSource>>();
		sourceConfigSources.put(IdSource.EMBY, embySources);
		
		// Mocks
		when(sourceConfigService.getWebSources()).thenReturn(sourceConfigSources);
		when(embyService.getAsyncArtists()).thenReturn(compFuture);
		
		// run
		aggregator.login();
		List<Artist> combinedArtists = aggregator.getArtists();
	
		// verify
		verify(embyService, times(recordsToCreate)).getAsyncArtists();
		Assert.assertEquals(recordsToCreate * 2, combinedArtists.size());

	}
	
	@Test
	public void happyPath_getAlbumsMultipleEmbyServer() throws ConnectionException, InterruptedException, ExecutionException, ConfigNotFoundException {
		// Data setup
		int recordsToCreate = 2;
		String artistId = StringUtils.randomAlphanumeric(10);
		
		ArrayList<WebApiSource> embySources = new ArrayList<WebApiSource>();
		ArrayList<Album> albums = new ArrayList<Album>();
		for(int i = 0; i < recordsToCreate; i++) {
			embySources.add(this.generateWebApiSource("emby" + i, IdSource.EMBY));
			albums.add(generateAlbum(artistId));
		}
		CompletableFuture<List<Album>> compFuture = CompletableFuture.completedFuture(albums);
		
		HashMap<IdSource, List<WebApiSource>> sourceConfigSources = new HashMap<IdSource, List<WebApiSource>>();
		sourceConfigSources.put(IdSource.EMBY, embySources);
		
		// Mocks
		when(sourceConfigService.getWebSources()).thenReturn(sourceConfigSources);
		when(embyService.getAsyncAlbumsByArtist(artistId)).thenReturn(compFuture);
		
		// run
		aggregator.login();
		aggregator.getAlbumsByArtist(artistId);
	
		// verify
		verify(embyService, times(recordsToCreate)).getAsyncAlbumsByArtist(artistId);

	}
	
	@Test
	public void happyPath_getSongsInAlbumMultipleEmbyServer() throws ConnectionException, InterruptedException, ExecutionException, ConfigNotFoundException {
		// Data setup
		int recordsToCreate = 2;
		String artistId = StringUtils.randomAlphanumeric(10);
		String albumId = StringUtils.randomAlphanumeric(10);
		
		ArrayList<WebApiSource> embySources = new ArrayList<WebApiSource>();
		ArrayList<Song> songs = new ArrayList<Song>();
		for(int i = 0; i < recordsToCreate; i++) {
			embySources.add(this.generateWebApiSource("emby" + i, IdSource.EMBY));
			songs.add(generateSong(artistId, albumId));
		}
		CompletableFuture<List<Song>> compFuture = CompletableFuture.completedFuture(songs);
		
		HashMap<IdSource, List<WebApiSource>> sourceConfigSources = new HashMap<IdSource, List<WebApiSource>>();
		sourceConfigSources.put(IdSource.EMBY, embySources);
		
		// Mocks
		when(sourceConfigService.getWebSources()).thenReturn(sourceConfigSources);
		when(embyService.getAsyncSongsInAlbum(albumId)).thenReturn(compFuture);
		
		// run
		aggregator.login();
		aggregator.getSongsInAlbum(albumId);
	
		// verify
		verify(embyService, times(recordsToCreate)).getAsyncSongsInAlbum(albumId);

	}
	
//	@Test//Get Byte Array of songs!
//	public void happyPath_getSongsFiles() throws ConnectionException, InterruptedException, ExecutionException {
//		// Data setup
//		int recordsToCreate = 2;
//		String artistId = StringUtils.randomAlphanumeric(10);
//		String albumId = StringUtils.randomAlphanumeric(10);
//		List<CompletableFuture<Byte[]>> compFuture = new ArrayList<CompletableFuture<Byte[]>>();
//		ArrayList<WebApiSource> embySources = new ArrayList<WebApiSource>();
//		HashMap<String, Byte[]> songs = new HashMap<String, Byte[]>();
//		for(int i = 0; i < recordsToCreate; i++) {
//			embySources.add(this.generateWebApiSource("emby" + i, IdSource.EMBY));
//			Byte[] newSongFile = new Byte[random.nextInt()];
//			songs.put(StringUtils.randomAlphanumeric(10), newSongFile);
//			
//		}
//		
//		HashMap<IdSource, List<WebApiSource>> sourceConfigSources = new HashMap<IdSource, List<WebApiSource>>();
//		sourceConfigSources.put(IdSource.EMBY, embySources);
//		
//		// Mocks
//		when(sourceConfigService.getWebSources()).thenReturn(sourceConfigSources);
//		for(String songId: songs.keySet()) {
//			when(embyService.playAsyncSong(songId))
//				.thenReturn(CompletableFuture.completedFuture(songs.get(songId)));
//		}
//		
//		// run
//		aggregator.login();
//		for(String songId: songs.keySet()) {
//			aggregator.playSong(songId);
//		}
//		
//		// verify
//		verify(embyService, times(recordsToCreate)).playAsyncSong(any(String.class));
//
//	}
	


	private WebApiSource generateWebApiSource(String Name, IdSource idSource) {
		WebApiSource webApiSource = new WebApiSource(Name, idSource);
		webApiSource.setPassword("randompasswordhere");
		webApiSource.setName("testName");
		webApiSource.setUrl("http://emby:8096");
		
		return webApiSource;
	}
	
	private Album generateAlbum(String artistId) {
		Album album = new Album();
		album.setName(StringUtils.randomAlphanumeric(10));
		album.setArtistId(artistId);
		album.setArtistIdSource(IdSource.NONE);
		return album;
	}
	

	private Song generateSong(String artistId, String albumId) {
		Song song = new Song();
		song.setName(StringUtils.randomAlphanumeric(10));
		song.setArtistId(artistId);
		song.setArtistIdSource(IdSource.NONE);
		song.setAlbumId(albumId);
		song.setAlbumIdSource(IdSource.NONE);
		
		return song;
	}
	
	
}

