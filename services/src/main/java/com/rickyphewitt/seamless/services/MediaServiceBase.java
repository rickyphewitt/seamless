package com.rickyphewitt.seamless.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rickyphewitt.seamless.data.MediaItemBase;

public abstract class MediaServiceBase<MediaItem extends MediaItemBase> {

	
	
	/**
	 * Consolidates multiple media items into a single
	 * 
	 * @ToDo add priority based on source
	 * 
	 * @param mediaItems
	 * @return unique media items
	 */
	public List<MediaItem> consolidate(List<MediaItem> mediaItems) {
		HashMap<String, MediaItem> uniqueMediaItemsMap = new HashMap<String, MediaItem>();
		ArrayList<MediaItem> uniqueMediaItems = new ArrayList<MediaItem>();
		
		for(MediaItem item: mediaItems) {
			uniqueMediaItemsMap.put(item.getUniversalKey(), item);
		}
	
		for(String itemKey: uniqueMediaItemsMap.keySet()) {
			uniqueMediaItems.add(uniqueMediaItemsMap.get(itemKey));
		}
	
		return uniqueMediaItems;

	}
}
