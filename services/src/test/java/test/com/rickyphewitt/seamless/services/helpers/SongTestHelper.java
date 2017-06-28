package test.com.rickyphewitt.seamless.services.helpers;

import java.util.ArrayList;
import java.util.Random;

import com.rickyphewitt.emby.api.data.Song;
import com.rickyphewitt.emby.api.data.SongSet;

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
	
}
