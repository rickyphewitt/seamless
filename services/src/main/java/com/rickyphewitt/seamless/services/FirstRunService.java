package com.rickyphewitt.seamless.services;

import com.rickyphewitt.seamless.data.Album;
import com.rickyphewitt.seamless.data.Artist;
import com.rickyphewitt.seamless.services.constants.FragmentConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;


@Service
public class FirstRunService {

	// attributes
	private static Logger logger = LogManager.getLogger();

	@Autowired
	FragmentService fragmentService;

	@Autowired
	SupportedSourcesService supportedSourcesService;

	
	public String firstRun(Model model) {
		logger.info("Found " + supportedSourcesService.getSources().size() + " supported source(s)");
		model.addAttribute("supportedSources", supportedSourcesService.getSources());
		model.addAttribute("appContentContent", "firstRunSources");

		return "firstRun";
	}
	
}
