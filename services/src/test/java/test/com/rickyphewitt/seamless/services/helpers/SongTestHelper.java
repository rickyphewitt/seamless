package test.com.rickyphewitt.seamless.services.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.thymeleaf.util.StringUtils;

import com.rickyphewitt.emby.api.data.SongSet;
import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.data.enums.IdSource;

import junit.framework.Assert;

public class SongTestHelper {

	private static Random random = new Random();
	
	@Deprecated
	public static com.rickyphewitt.emby.api.data.SongSet setupSingleSongWithId() {
		String songId = String.valueOf(random.nextInt());
		com.rickyphewitt.emby.api.data.SongSet songs = new SongSet();
		com.rickyphewitt.emby.api.data.Song song = new com.rickyphewitt.emby.api.data.Song();
		song.setId(songId);
		ArrayList<com.rickyphewitt.emby.api.data.Song> songList = new ArrayList<com.rickyphewitt.emby.api.data.Song>();
		songList.add(song);
		songs.setItems(songList);
		return songs;
	}
	
	@Deprecated
	public static com.rickyphewitt.emby.api.data.Song createSong(String id, String name) {
		com.rickyphewitt.emby.api.data.Song song = new com.rickyphewitt.emby.api.data.Song();
		song.setId(id);
		song.setName(name);
		return song;
	}
	
	public static Song createRandomSong() {
		com.rickyphewitt.seamless.data.Song song = new com.rickyphewitt.seamless.data.Song();
		song.setId(random.nextLong());
		song.setName(StringUtils.randomAlphanumeric(10));
		song.setMediaId(StringUtils.randomAlphanumeric(10));
		song.setMediaIdSource(IdSource.NONE);
		song.setPrimaryImage("www.fakeserver.net/" + StringUtils.randomAlphanumeric(10) + ".jpg");
		return song;
	}
	
	public static Song createRandomSong(int trackNumber) {
		Song song = createRandomSong();
		song.setTrackNumber(trackNumber);
		return song;
	}
	
	
	public static List<Song> createRandomSongsInAlbum(int albumTracks, Set<Integer> tracksToExclude) {
		List<Song> albumSongs = new ArrayList<Song>();
		for(int i = 0; i < albumTracks; i++) {
			if(tracksToExclude != null && !tracksToExclude.contains(i)) {
				continue;
			} else {
				albumSongs.add(createRandomSong(i + 1));
			}
		}
		
		return albumSongs;
	}
	
	public static Map<Integer, Song> songsByTrackNumber(List<Song> songs) {
		HashMap<Integer, Song> songsByTrack = new HashMap<Integer, Song>();
		for(Song song: songs) {
			songsByTrack.put(song.getTrackNumber(), song);
		}
		
		return songsByTrack;
		
	}
	
	public static void assertEqual(Song expected, Song actual) {
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getAlbumId(), actual.getAlbumId());
		Assert.assertEquals(expected.getAlbumIdSource(), actual.getAlbumIdSource());
		Assert.assertEquals(expected.getArtistId(), actual.getArtistId());
		Assert.assertEquals(expected.getDuration(), actual.getDuration());
		Assert.assertEquals(expected.getMediaId(), actual.getMediaId());
		Assert.assertEquals(expected.getMediaIdSource(), actual.getMediaIdSource());
		Assert.assertEquals(expected.getName(), actual.getName());
		Assert.assertEquals(expected.getPrimaryImage(), actual.getPrimaryImage());
		Assert.assertEquals(expected.getTrackNumber(), actual.getTrackNumber());
		Assert.assertEquals(expected.getUniversalKey(), actual.getUniversalKey());
	}
	
	
	
}
