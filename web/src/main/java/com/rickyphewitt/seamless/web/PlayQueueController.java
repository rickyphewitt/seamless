package com.rickyphewitt.seamless.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rickyphewitt.seamless.services.PlayQueueService;

@Controller
public class PlayQueueController {
	
	@Autowired
	PlayQueueService playQueueService;
	
	@RequestMapping(value="/queue")
	public String getQueue(Model model) {
		model.addAttribute("queue", playQueueService.getPlayQueue());
		return "playQueue";
	}
}
