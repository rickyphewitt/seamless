package com.rickyphewitt.seamless.web;

import com.rickyphewitt.seamless.data.Config;
import com.rickyphewitt.seamless.data.SimpleResponse;
import com.rickyphewitt.seamless.data.enums.IdSource;
import com.rickyphewitt.seamless.data.exceptions.ConfigException;
import com.rickyphewitt.seamless.data.sources.WebApiSource;
import com.rickyphewitt.seamless.services.FragmentService;
import com.rickyphewitt.seamless.services.SettingsService;
import com.rickyphewitt.seamless.services.SimpleErrorService;
import com.rickyphewitt.seamless.services.SourceConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SourcesConfigController {

	@Autowired 
	FragmentService fragmentService;

	@Autowired
	SourceConfigService sourceConfigService;

	@Autowired
	SimpleErrorService simpleErrorService;
	
	@GetMapping("api/v1/sources")
	public String sources(Model model) {
		return fragmentService.getFragment("addSource");
	}

	@PostMapping(value = "/api/v1/sources", produces = {"application/JSON"})
	public @ResponseBody
	SimpleResponse save(@ModelAttribute @Valid WebApiSource webApiSource, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return simpleErrorService.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
		} else {
			try {
				sourceConfigService.writeSource(webApiSource);
			} catch (ConfigException e) {
				return simpleErrorService.error(e.getMessage());
			}
		}
		return simpleErrorService.success();

	}

	// model mappings
	@ModelAttribute("source")
	public WebApiSource getEmptySource() {
		return new WebApiSource("Emby", IdSource.EMBY);
	}
}
