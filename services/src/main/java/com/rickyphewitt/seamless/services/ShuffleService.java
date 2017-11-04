package com.rickyphewitt.seamless.services;

import com.rickyphewitt.seamless.data.Song;

import java.util.Collections;
import java.util.List;

public class ShuffleService {

	// currently nothing crazy about the shuffle
	public static void shuffle(List<Song> songs) {
		Collections.shuffle(songs);
	}


	
}
