package com.rickyphewitt.seamless.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rickyphewitt.emby.api.data.Item;

@Service
public class DisplayOrganizerService {

	// Attributes
	Set<String> sortedKeys;
	
	public HashMap<String, ArrayList<Item>> organizeItems(List<? extends Item> items) {
		sortedKeys = new TreeSet<String>();
		
		HashMap<String, ArrayList<Item>> organizedItems = new HashMap<String, ArrayList<Item>>();
		
		
		for(Item item: items) {
			
			String key = getKey(item.getName());
			sortedKeys.add(key);
			
			if(!organizedItems.containsKey(key)) {
				ArrayList<Item> newItems = new ArrayList<Item>();
				newItems.add(item);
				organizedItems.put(key, newItems);
			} else {
				// Items exists, get current list and add to it
				organizedItems.get(String.valueOf(item.getName().charAt(0))).add(item);
			}
		}

		return organizedItems;
	}
	
	private String getKey(String fullValue) {
		return String.valueOf(fullValue.charAt(0));
	}

	// Getters/Setters
	public Set<String> getSortedKeys() {
		return sortedKeys;
	}	
}
