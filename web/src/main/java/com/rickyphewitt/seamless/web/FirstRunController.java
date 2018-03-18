package com.rickyphewitt.seamless.web;

import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.data.exceptions.TrackDoesNotExistException;
import com.rickyphewitt.seamless.services.FirstRunService;
import com.rickyphewitt.seamless.services.PlayQueueService;
import com.rickyphewitt.seamless.services.PlayService;
import com.rickyphewitt.seamless.services.SupportedSourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("firstRun")
public class FirstRunController {

	@Autowired
	private FirstRunService firstRunService;

	@RequestMapping("")
	public String firstRun (Model model) {

		return firstRunService.firstRun(model);
	}
	
}
