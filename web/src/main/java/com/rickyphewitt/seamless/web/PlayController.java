package com.rickyphewitt.seamless.web;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.data.exceptions.TrackDoesNotExistException;
import com.rickyphewitt.seamless.services.PlayQueueService;
import com.rickyphewitt.seamless.services.PlayService;

@Controller
public class PlayController {

	@Autowired
	PlayService playService;
	
	@Autowired
	PlayQueueService playQueueService;
	
	@RequestMapping(value="/play/album/{id}", produces = MediaType.ALL_VALUE)
	public @ResponseBody byte[] play(@PathVariable("id") String id, Model model) throws InterruptedException, ExecutionException, TrackDoesNotExistException {
		return playService.playAlbum(id, 1);
	}

	@RequestMapping(value="/play/album/{id}/{trackNumber}", produces = MediaType.ALL_VALUE)
	public @ResponseBody byte[] play(@PathVariable("id") String id,
			@PathVariable("trackNumber") int trackNumber, Model model) throws InterruptedException, ExecutionException, TrackDoesNotExistException {
		return playService.playAlbum(id, trackNumber);
	}
	
	@RequestMapping(value="/play/queue/song/{id}", produces = MediaType.ALL_VALUE)
	public @ResponseBody byte[] playQueueSong(@PathVariable("id") String id, Model model) throws InterruptedException, ExecutionException {
		return playService.playQueueSong(id);
	}
	
	@RequestMapping(value="/play/queue/next", produces = MediaType.ALL_VALUE)
	public  @ResponseBody String getQueueNext() {
		playQueueService.next();
		Map<Integer, Song> playQueue = playQueueService.getPlayQueue();
		return playQueue.get(playQueueService.getCurrentIndex()).getMediaId();
	}
	
	@RequestMapping(value="/play/queue/prev", produces = MediaType.ALL_VALUE)
	public  @ResponseBody String getQueuePrev() {
		playQueueService.prev();
		return playQueueService.getPlayQueue().get(playQueueService.getCurrentIndex()).getMediaId();
	}
	
	@RequestMapping(value="/play/queue/playing", produces = MediaType.ALL_VALUE)
	public  @ResponseBody String getQueuePlaying() {
		return playQueueService.getPlayQueue().get(playQueueService.getCurrentIndex()).getMediaId();
	}
	
}
