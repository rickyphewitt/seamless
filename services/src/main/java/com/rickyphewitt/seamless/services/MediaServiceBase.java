package com.rickyphewitt.seamless.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rickyphewitt.seamless.data.MediaItemBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class MediaServiceBase<MediaItem extends MediaItemBase> {

	private static Logger logger = LogManager.getLogger();
	
	
	/**
	 * Consolidates multiple media items into a single
	 * 
	 * @ToDo add priority based on source
	 * 
	 * @param mediaItems
	 * @return unique media items
	 */
	public List<MediaItem> consolidate(List<MediaItem> mediaItems) {
		logger.info("Consolidating " + mediaItems.size() + " items");
		HashMap<String, MediaItem> uniqueMediaItemsMap = new HashMap<String, MediaItem>();
		ArrayList<MediaItem> uniqueMediaItems = new ArrayList<MediaItem>();
		
		for(MediaItem item: mediaItems) {
			uniqueMediaItemsMap.put(item.getUniversalKey(), item);
		}
	
		for(String itemKey: uniqueMediaItemsMap.keySet()) {
			uniqueMediaItems.add(uniqueMediaItemsMap.get(itemKey));
		}

		logger.info("Consolidated into " + uniqueMediaItems.size() + " items");
		return uniqueMediaItems;

	}
}
