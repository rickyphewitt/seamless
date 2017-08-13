package com.rickyphewitt.seamless.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rickyphewitt.seamless.data.Song;



@Service
public class PlayQueueService {

	private Map<Integer, Song> playQueue;
	private HashSet<Integer> indexes;
	private Integer currentIndex;
	
	public PlayQueueService() {
		indexes = new HashSet<Integer>();
		currentIndex = 0;
	}
	

	public void next(){
		System.out.println("current index: " + currentIndex);
		currentIndex += 1;
		if(isOutOfBounds(currentIndex)) {
			wrapToBeginning();
		}
		
		System.out.println("current index after update: " + currentIndex);
	}
	
	public void prev(){
		currentIndex -= 1;
		if(isOutOfBounds(currentIndex)) {
			wrapToEnding();
		}
	}
	
	/**
	 * Determines if the incoming index in an 
	 * index out of bounds error
	 * 
	 * @param index
	 * @return index is out of bounds
	 */
	public boolean isOutOfBounds(Integer index) {
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
	public void setPlayQueue(List<Song> songs) {
		playQueue = new HashMap<Integer, Song>();
		for(int i = 0; i < songs.size(); i ++) {
			playQueue.put(i, songs.get(i));
		}
		setIndexes(playQueue.size());
		currentIndex = 0;
	}
	
	public void setPlayQueue(List<Song> songs, int playingItemNumber) {
		playQueue = new HashMap<Integer, Song>();
		for(int i = 0; i < songs.size(); i ++) {
			playQueue.put(i, songs.get(i));
		}
		setIndexes(playQueue.size());
		currentIndex = playingItemNumber;
	}


	public void setIndexes(int sizeOfIndexes) {
		indexes.clear();
		for(int i = 0; i < sizeOfIndexes; i++) {
			indexes.add(i);
		}
	}	
	
	public Map<Integer, Song> getPlayQueue() {
		return playQueue;
	}
	public void setPlayQueue(Map<Integer, Song> playQueue) {
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
