package com.rickyphewitt.emby.mini.music.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rickyphewitt.emby.mini.music.services.PlayQueueService;
import com.rickyphewitt.emby.mini.music.services.PlayService;

@Controller
public class PlayController {

	@Autowired
	PlayService playService;
	
	@Autowired
	PlayQueueService playQueueService;
	
	@RequestMapping(value="/play/album/{id}", produces = MediaType.ALL_VALUE)
	public @ResponseBody byte[] play(@PathVariable("id") String id, Model model) {
		return playService.playAlbum(id, 1);
	}
	
	@RequestMapping(value="/play/queue/song/{id}", produces = MediaType.ALL_VALUE)
	public @ResponseBody byte[] playQueueSong(@PathVariable("id") String id, Model model) {
		return playService.playQueueSong(id);
	}
	
	@RequestMapping(value="/play/queue/next", produces = MediaType.ALL_VALUE)
	public  @ResponseBody String getQueueNext() {
		playQueueService.next();
		return playQueueService.getPlayQueue().get(playQueueService.getCurrentIndex()).getId();
	}
	
	@RequestMapping(value="/play/queue/prev", produces = MediaType.ALL_VALUE)
	public  @ResponseBody String getQueuePrev() {
		playQueueService.prev();
		return playQueueService.getPlayQueue().get(playQueueService.getCurrentIndex()).getId();
	}
	
}