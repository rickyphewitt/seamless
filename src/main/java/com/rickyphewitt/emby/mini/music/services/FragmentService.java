package com.rickyphewitt.emby.mini.music.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rickyphewitt.emby.mini.music.constants.FragmentConstants;

@Service
public class FragmentService {

	private Map<String, String> fragments;
	
	public FragmentService() {
		fragments = new HashMap<String, String>();
		fragments.put(FragmentConstants.FRAGMENT_FOLDER, FragmentConstants.FRAGMENT_FOLDER);
		fragments.put(FragmentConstants.SECTION_INNER_CONTENT, FragmentConstants.SECTION_INNER_CONTENT);
		fragments.put(FragmentConstants.FRAGMENT_ARTISTS, FragmentConstants.FRAGMENT_ARTISTS);
		fragments.put(FragmentConstants.FRAGMENT_ALBUMS, FragmentConstants.FRAGMENT_ALBUMS);
		fragments.put(FragmentConstants.FRAGMENT_ALBUMS_BY_ARTIST, FragmentConstants.FRAGMENT_ALBUMS_BY_ARTIST);
		fragments.put(FragmentConstants.FRAGMENT_GENRES, FragmentConstants.FRAGMENT_GENRES);
		fragments.put(FragmentConstants.FRAGMENT_PLAYLISTS, FragmentConstants.FRAGMENT_PLAYLISTS);
		fragments.put(FragmentConstants.FRAGMENT_FAVORITES, FragmentConstants.FRAGMENT_FAVORITES);
		fragments.put(FragmentConstants.FRAGMENT_SETTINGS, FragmentConstants.FRAGMENT_SETTINGS);
	}

	
	public String getFragment(String nameOfFragment) {
		//fragments/albumsByArtist :: albumsByArtist
		String fragmentName = fragments.get(nameOfFragment);
		return FragmentConstants.FRAGMENT_FOLDER + "/" + fragmentName + " :: " + fragmentName; 
	}
	
	public Map<String, String> getFragments() {
		return fragments;
	}

	public void setFragments(Map<String, String> fragments) {
		this.fragments = fragments;
	}
	
}
