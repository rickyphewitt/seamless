package test.com.rickyphewitt.seamless.services.helpers;

import java.util.ArrayList;
import java.util.Random;

import org.thymeleaf.util.StringUtils;

import com.rickyphewitt.emby.api.data.Song;
import com.rickyphewitt.emby.api.data.SongSet;
import com.rickyphewitt.seamless.data.enums.IdSource;

public class SongTestHelper {

	private static Random random = new Random();
	
	public static SongSet setupSingleSongWithId() {
		String songId = String.valueOf(random.nextInt());
		SongSet songs = new SongSet();
		Song song = new Song();
		song.setId(songId);
		ArrayList<Song> songList = new ArrayList<Song>();
		songList.add(song);
		songs.setItems(songList);
		return songs;
	}
	
	
	public static Song createSong(String id, String name) {
		Song song = new Song();
		song.setId(id);
		song.setName(name);
		return song;
	}
	
	public static com.rickyphewitt.seamless.data.Song createRandomSong() {
		com.rickyphewitt.seamless.data.Song song = new com.rickyphewitt.seamless.data.Song();
		song.setId(random.nextLong());
		song.setName(StringUtils.randomAlphanumeric(10));
		song.setMediaId(StringUtils.randomAlphanumeric(10));
		song.setMediaIdSource(IdSource.NONE);
		song.setPrimaryImage("www.fakeserver.net/" + StringUtils.randomAlphanumeric(10) + ".jpg");
		return song;
	}
	
}
