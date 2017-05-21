package com.rickyphewitt.emby.mini.music.services;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.stereotype.Service;

import com.rickyphewitt.emby.api.data.Song;
import com.rickyphewitt.emby.api.data.SongSet;

@Service
public class PlayQueueService {

	private ArrayList<Song> playQueue;
	private HashSet<Integer> indexes;
	private Integer currentIndex;
	
	public PlayQueueService() {
		playQueue = new ArrayList<Song>();
		indexes = new HashSet<Integer>();
		currentIndex = 0;
	}
	

	public void next(){
		currentIndex += 1;
		if(isOutOfBounds(currentIndex)) {
			wrapToBeginning();
		}
	}
	
	public void prev(){
		currentIndex -= 1;
		if(isOutOfBounds(currentIndex)) {
			wrapToEnding();
		}
	}
	
	private boolean isOutOfBounds(Integer index) {
		boolean isOutOfBounds = false;
		if(index < 0 || index >= indexes.size()) {
			isOutOfBounds = true;
		}
		return isOutOfBounds;
	}
	
	private void wrapToBeginning() {
		currentIndex = 0;
	}
	
	private void wrapToEnding() {
		currentIndex = indexes.size() -1;
	}
	public void shuffle(){}
	public void loop(){}
	public void loopOne(){}
	public void clear(){}
	public void add(){}
	public void remove(){}
	public void like(){}
	
	
	
	// Getters/Setters
	public void setPlayQueue(SongSet songs) {
		playQueue.clear();
		for(Song s: songs.getItems()) {
			playQueue.add(s);
		}
		setIndexes(playQueue.size());
	}
	
	public void setPlayQueue(SongSet songs, int playingItemNumber) {
		playQueue.clear();
		for(Song s: songs.getItems()) {
			playQueue.add(s);
		}
		setIndexes(playQueue.size());
		currentIndex = playingItemNumber;
	}
	
	public void add(Song song) {
		playQueue.add(song);
	}
	
	public void remove(int index) {
		playQueue.remove(index);
		
	}

	public void setIndexes(int sizeOfIndexes) {
		indexes.clear();
		for(int i = 0; i < sizeOfIndexes; i++) {
			indexes.add(i);
		}
	}	
	
	public ArrayList<Song> getPlayQueue() {
		return playQueue;
	}
	public void setPlayQueue(ArrayList<Song> playQueue) {
		this.playQueue = playQueue;
		setIndexes(playQueue.size());
	}
	
	
	public HashSet<Integer> getIndexes() {
		return indexes;
	}

	public void setIndexes(HashSet<Integer> indexes) {
		this.indexes = indexes;
	}

	public Integer getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}
	
}
